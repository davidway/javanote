import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    public static void main(String[] args)
    {
        List<String> list=new ArrayList<>();
        list.add("我是程序汪");
        list.add("抖音");
        list.add("微信");
        for(Iterator<String> it = list.iterator(); it.hasNext();)
        {
            String s = it.next();
            System.out.println(it.next());
            if(s.equals("抖音")){
                int index = list.indexOf(s);
                list.remove(index);//翻车写法
                //it.remove();//正确写法
            }
        }
    }
}
