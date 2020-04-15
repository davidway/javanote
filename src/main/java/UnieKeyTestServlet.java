import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UnieKeyTestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test";
            request.setCharacterEncoding("UTF-8");
            conn = DriverManager.getConnection(url,"root","root");
            String sql = "insert into user(username) values(?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            String username=request.getParameter("username");
            try {
                //让当前的线程睡眠3秒钟，模拟网络延迟而导致表单重复提交的现象
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ps.setString(1,username);
            try{
                ps.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
