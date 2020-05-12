package leetcode.a1115;

import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
        static final Semaphore fooFinish = new Semaphore(1);
    static final Semaphore barFinish = new Semaphore(0);


    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            barFinish.acquire();
            printFoo.run();
            fooFinish.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooFinish.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            barFinish.release();
        }
    }
}

class PrintThread implements  Runnable{
        String s ;
    PrintThread(String s){
            this.s =s;
        }
    @Override
    public void run() {
        System.out.print(s);
    }
}
class PrintFoo implements  Runnable{

    int n;

    public PrintFoo(int n) {
        this.n =n ;
    }

    @Override
    public void run() {
        try {
            new FooBar(n).foo(new PrintThread("foo"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintBar implements  Runnable{

        int n;
    public PrintBar(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        try {
            new FooBar(n).foo(new PrintThread("bar"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class Leetcode1115 {
    public static void main(String[] args) {
        int n=5;
    new Thread(new PrintFoo(n)).start();
    new Thread(new PrintBar(n)).start();

    }
}
