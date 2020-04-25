package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {


    private static String getConstraints(Constrains con){
        String constraints = "";
        if ( !con.allowNull()){
            constraints += " not null ";
        }if ( con.primaryKey()){
            constraints += " PRIMARY KEY ";
        }if ( con.unique()){
            constraints += " UNIQUE ";
        }
        return constraints;
    }
    public static void main(String[] args) throws ClassNotFoundException {
        args = new String[1];
        args[0] = "annotation.Member";
        if ( args.length<1){
            System.out.println("args:annotated classes");
            System.exit(0);
        }

        for (String className:args){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if ( dbTable ==null){
                System.out.format("No dbTable annotations in class %s",className);
                continue;
            }
            String tableName = dbTable.name();
            if ( tableName.length()<1){
                tableName = cl.getName().toUpperCase();
                List<String> columnDefs = new ArrayList<String>();
                for ( Field field:cl.getDeclaredFields()){
                    String columnName = null;
                    Annotation[] anns = field.getDeclaredAnnotations();
                    if ( anns.length <1){
                        continue;
                    }
                    if ( anns[0] instanceof  SQLInteger){
                        SQLInteger sInt = (SQLInteger) anns[0];
                        if ( sInt.name().length()<1){
                            columnName = field.getName().toUpperCase();
                        }else{
                            columnName = sInt.name();
                        }
                        columnDefs.add(String.format("%s  INT %s",columnName,getConstraints(sInt.constrains())));
                    }

                    if ( anns[0] instanceof  SQLString){
                        SQLString sqlString = (SQLString) anns[0];
                        if ( sqlString.name().length()<1){
                            columnName = field.getName().toUpperCase();
                        }else{
                            columnName = sqlString.name();
                        }
                        columnDefs.add(String.format("%s  VARCHAR( %s",columnName,sqlString.constrains()));
                    }

                    StringBuilder createCommand = new StringBuilder(
                            " CREATE TABLE " +tableName +" ("
                    );
                    for ( String columnDef :columnDefs){
                        createCommand.append("\n "+columnDef+" ,");
                        String tableCreate = createCommand.substring(0,createCommand.length()-1)+");";
                        System.out.printf("Table createtion SQL for %s is \n %s",className,tableCreate);
                    }
                }
            }

        }
    }
}
