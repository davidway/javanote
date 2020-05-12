package collection;

import org.testng.annotations.Test;

import java.util.*;

public class HashMapTest {
    @Test
    public  void testHashMap() {
       HashMap hashMap = new HashMap();
       hashMap.put(null,"test");

    }
    @Test
    public void testHashSet(){
        HashSet hashSet = new HashSet();
        hashSet.add("2");
        hashSet.add("2");
        hashSet.remove("2");
        System.out.println(hashSet);
    }

    private static void printMap(Map map) {
        Iterator iterator = map.entrySet().iterator();

        while ( iterator.hasNext()){
            Map.Entry e = (Map.Entry) iterator.next();
            System.out.println(e);
        }

    }

}