package thread;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 被保护，被首位，暂停
 */
public class GuardedSuspension {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue,"Alice",3141592L).start();
        new ServerThread(requestQueue,"Bobby",6535897L).start();
    }
}

class Request{
    private final String name;
    public Request (String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return "[ REQUEST " +name+" ]";
    }
}
class ClientThread extends Thread{
    private final Random random;
    private final RequestQueue requestQueue;
    public ClientThread(RequestQueue requestQueue,String name,long seed){
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run(){
        for (int i = 0; i < 1000; i++) {
            Request request = new Request("No."+i);
            System.out.println(Thread.currentThread().getName()+" requests"+request);
            requestQueue.putRequest(request);
        }try{
            Thread.sleep(random.nextInt(1000));
        }catch(InterruptedException e){

        }
    }
}
class ServerThread extends Thread{
    private  final Random random;
    private final RequestQueue requestQueue;
    public ServerThread(RequestQueue requestQueue,String name,long seed){
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run(){
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName()+" handles"+request);
            try{
                Thread.sleep(random.nextInt(1000));
            }catch(InterruptedException e){

            }
        }
    }
}
class RequestQueue{
    private final Queue<Request> queue = new LinkedList<Request>();
    public synchronized  Request getRequest(){
        while ( queue.peek()==null){
            try{
                wait();
            }catch(InterruptedException e){

            }
        }
        return queue.remove();
    }

    public synchronized  void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }
}