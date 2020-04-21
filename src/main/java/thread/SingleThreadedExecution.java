package thread;

public class SingleThreadedExecution {
    public static void main(String[] args) {
        System.out.println("Testing gate,hit ctrl+c to exit");
        Gate gate = new Gate();
        new UserThread(gate,"Alice","Alaska").start();
        new UserThread(gate,"Bobby","Brazil").start();
        new UserThread(gate,"Chris","Canada").start();
    }
}

class Gate{
    private int counter=0;
    private String name="Nobody";;
    private String address="nowhere";
    public   void pass(String name,String address){
        this.counter++;
        this.name = name;
        this.address = address;
        check();
    }
    @Override
    public String toString(){
       return  String.format("No.%s:%s,%s",counter,name,address);
    }

    private void check() {
        if ( name.charAt(0)!=address.charAt(0)){
            System.out.println("***********BROKEN***********"+toString());
        }
    }


}

class UserThread extends  Thread{
    private final Gate gate;
    private final String myname;
    private final String myaddress;

    public UserThread(Gate gate,String myname,String address){
        this.gate = gate;
        this.myname = myname;
        this.myaddress = address;
    }
    @Override
    public void run(){
        System.out.println(myname+":BEGIN");
        while(true){
            gate.pass(myname,myaddress);
        }
    }
}