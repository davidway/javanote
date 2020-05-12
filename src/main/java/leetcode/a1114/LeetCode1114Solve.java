package leetcode.a1114;


import java.util.concurrent.Semaphore;

class Foo {
    static final Semaphore firstFinish = new Semaphore(1);
    static final  Semaphore secondFinish = new Semaphore(1);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstFinish.acquire();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstFinish.release();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondFinish.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondFinish.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();

    }
}
class PrintWorkThread implements  Runnable{
    String s ;
    PrintWorkThread(String s){
        this.s=s;
    }
    @Override
    public void run() {
        System.out.println(s);

    }
}
class OneThread implements  Runnable{

    @Override
    public void run() {
        try {
            new Foo().first(new PrintWorkThread("one"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class TwoThread implements  Runnable{

    @Override
    public void run() {
        try {
            new Foo().second(new PrintWorkThread("two"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ThreeThread implements  Runnable{

    @Override
    public void run() {
        try {
            new Foo().third(new PrintWorkThread("three"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class LeetCode1114Solve{
    public static void main(String[] args) {
        int[] array = {2,1,3};
        for( int i=0 ; i<array.length ; i++){
            if ( array[i]==1){
                new Thread(new OneThread()).start();
            }else if ( array[i]==2){
                new Thread(new TwoThread()).start();
            }else if ( array[i]==3){
                new Thread(new ThreeThread()).start();
            }
        }
    }
}