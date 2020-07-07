package jvm;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public Object instance = null;
    private static final int _1M= 1024*1024;
    private byte[] bigSize = new byte[2*_1M];


    /**
     * -xms 20M
     * -xmx 20m
     * -xmn 10m
     * java 堆为20m
     * 10M给新生代
     * 10M给老年代
     * -XX：survivorRatio=8 eden与一个survivor区是8：1
     */
    @Test
    public void testPretenureSizeThreshold(){
            byte[] allocation;
            allocation = new byte[4*1024*1024]; //4MB
    }
    @Test
    public void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2*1024*1024];
        allocation2 = new byte[2*1024*1024];
        allocation3 = new byte[2*1024*1024];
        allocation4 = new byte[2*1024*1024];
    }
    public static Main SAVE_HOOK = null;
    public void isAlive(){
        System.out.println("yes,i am staill alive");
    }
    @Override
    protected void finalize () throws Throwable{
        super.finalize();
        System.out.println("finalize method executed!");
        Main.SAVE_HOOK=this;
    }
    @Test
    public void testGCSave() throws Exception{
        SAVE_HOOK = new Main();
        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(500);
        if ( SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println(" no a am dead :(");
        }
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if ( SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
        System.out.println(" no a am dead :(");
        }
    }

    @Test
    public void testGc(){
            Main a = new Main();
            Main b = new Main();
            a.instance = b;
            b.instance = a;
            a = null;
            b = null;
    }
    @Test
    public void testPermGen(){
        List<String> list = new ArrayList<String>();
        int i=0;
        while ( true){
            list.add(String.valueOf(i++).intern());
        }
    }
    @Test
    public void testMultiThread(){
        JavaStackOOM oom = new JavaStackOOM();
        oom.stackLeakByThread();
    }
    class JavaStackOOM{
        private void dontStop(){
            while(true){}
        }
        public void stackLeakByThread(){
            while(true){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dontStop();
                    }
                });
                thread.start();
            }
        }


    }
    static class OOMObject{

    }
    @Test
    public  void testArgs() {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }

    @Test
    public  void testRecursion() {

        try{
            recursion();
        }catch(Throwable e){
            System.out.format("deep of calling %s",count);
            e.printStackTrace();
        }
       ;
    }
    private static int count=0;
    public void recursion(){
        count++;
        recursion();
    }
}
