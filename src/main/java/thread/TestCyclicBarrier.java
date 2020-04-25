package thread;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    @Test
    public void test(){
        for (int i = 1; i <= 6; i++) {
            final int tempInt = i;
            new Thread(()->{
                System.out.println(tempInt+"朋友到了");
            },String.valueOf(i)).start();
        }
      }

    @Test
    public void replaceTest(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6,()->{System.out.println("朋友全部到了,才开始吃饭");});
        for (int i = 1; i <= 6; i++) {
            final int tempInt = i;
            new Thread(()->{
                System.out.println(tempInt+"朋友到了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }
    }

}
