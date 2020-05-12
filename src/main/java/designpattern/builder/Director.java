package designpattern.builder;

public class Director {
    /*public Director(TxtBuilder tb) {
    }

    public Director(ExportXML ex) {
    }

    public void Build() {
    }*/
    private ExportBuilder eb;
    public Director(ExportBuilder eb){
        this.eb = eb;
    }
    public void Build(){
        eb.BuildHead();
        eb.BuildBody();
        eb.BuildFoot();
    }
}
