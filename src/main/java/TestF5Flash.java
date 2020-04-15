import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestF5Flash extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String orderName = request.getParameter("orderName");
        System.out.println("收到订单:" + orderName +"这里将进行数据库运算");
        try {
            //让当前的线程睡眠3秒钟，模拟网络延迟而导致表单重复提交的现象
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html; charset=utf-8");
        response.sendRedirect("/success.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
