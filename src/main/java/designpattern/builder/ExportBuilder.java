package designpattern.builder;

public abstract class ExportBuilder {
    String id;
    String date;
    String tableName;
    String data;
    String person;


    public ExportBuilder(String id, String date, String tableName, String data, String person) {
        this.id = id;
        this.date = date;
        this.tableName = tableName;
        this.data = data;
        this.person = person;
    }

    public abstract void BuildHead();
    public abstract void BuildBody();
    public abstract void BuildFoot();
}
