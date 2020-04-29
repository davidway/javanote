package lambda;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Optional.ofNullable;

public class LambTest {
    public static void main(String[] args) {
        List<String> words=new ArrayList<>();
        words.add("test");
        words.add("word");
        words.add("haha");
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });
        Collections.sort(words,(o1,o2)->Integer.compare(o1.length(),o2.length()));
        System.out.println(words.toString());

        String nullString=null;
        String emptyString="";
        String defaultString="hehe";
        String testNullPointer = ofNullable(nullString).orElse(defaultString);
        System.out.println("testNullPointer = " + testNullPointer);
         testNullPointer = ofNullable(emptyString).orElse(defaultString);
        System.out.println("testNullPointer = " + testNullPointer);
    }
}
