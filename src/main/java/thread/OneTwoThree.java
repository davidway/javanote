package thread;

public class OneTwoThree {
    public void first(Runnable printFirst){
        printFirst.run();
    }
    public void second(Runnable printSecond){
        printSecond.run();
    }
    public void third(Runnable printThird){
        printThird.run();
    }

    public static void main(String[] args) {

    }
}

class One implements  Runnable{

    @Override
    public void run() {
        System.out.println("one");
    }
}


class Two implements  Runnable {

    @Override
    public void run() {
        System.out.println("two");
    }
}


class Three implements  Runnable{

    @Override
    public void run() {
        System.out.println("three");
    }
}