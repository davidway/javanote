package collections;

import org.testng.annotations.Test;

import java.util.ArrayList;

public class MyList {
    @Test
    public void testArrayList(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(null);
        arrayList.add(null);

        System.out.println(arrayList);
    }
}
