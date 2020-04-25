package annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordUtils {
    @UseCase(id=47,descrption = "测试一下注解")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }
    @UseCase(id=48)
    public String encryptPassword(String password){
            return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id=48,descrption = "3~测试一下注解")
    public boolean checkForNewPassword(List<String> prevPasswords, String password){
        return !prevPasswords.contains(password);
    }

    public static void trackUseCase(List<Integer> useCases,Class<?> cl){
        for ( Method m :cl.getDeclaredMethods()){
            UseCase useCase  = m.getAnnotation(UseCase.class); //返回注解对象
            if ( useCase!=null){
                System.out.format("Found usecase %s,%s",useCase.id(),useCase.descrption());
                useCases.remove(new Integer(useCase.id()));
            }
            for ( int i :useCases){

                System.out.println("warning missing use case-"+i);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases,47,48,49,50);
        trackUseCase(useCases,PasswordUtils.class);
    }
}
