package collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class ConcurrentIssueDemo1 {
    public static void main(String[] args) {
        Integer.valueOf("s");
        final Map<Integer, String> map = new ConcurrentHashMap<>(); //修改了这一行，运行了很多次都可行

        final Integer targetKey = 0b1111_1111_1111_1111; // 65 535
        final String targetValue = "v";
        //65535=1
        map.put(targetKey, targetValue);
        //开启一个线程，在 range是循环，放入0=someValue .....65536=someValue
        new Thread(() -> {
            IntStream.range(0, targetKey).forEach(key -> map.put(key, "someValue"));
        }).start();

        //同时主线程不断的读 v 是否等于 map.get(65535);
        while (true) {
            if (!targetValue.equals(map.get(targetKey))) {
                throw new RuntimeException("HashMap is not thread safe.");
            }
        }
    }
}