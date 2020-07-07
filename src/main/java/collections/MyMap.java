package collections;

import org.testng.annotations.Test;

import java.util.HashMap;

public class MyMap {
    @Test
    public void testHashMapPut(){
        long start = System.currentTimeMillis()/1000;
        HashMap<Integer,String> map = new HashMap<Integer,String>();
        // HashMap<String,String> map = new HashMap<String,String>(); 有什么区别

        String key="";
        for (int i = 0; i <5_000_000; i++) {

            map.put(i ,"world");
        }
        long end = System.currentTimeMillis()/1000;
        System.out.println(map.size());
        System.out.format("耗时%s s",end-start);
    }

}
