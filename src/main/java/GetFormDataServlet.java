import com.alibaba.fastjson.JSONObject;
import dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class GetFormDataServlet   extends HttpServlet {
    private static final long serialVersionUID = -884689940866074733L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        /**
         * 接收json
         */
        BufferedReader reader = request.getReader();
        String jsonString = reader.readLine();
        System.out.println(jsonString);
        reader.close();

        /**
         * 返回json
         */
        JSONObject json = JSONObject.parseObject(jsonString);
        UserDto userDto = JSONObject.parseObject(jsonString,UserDto.class);
        PrintWriter out = response.getWriter();
        out.write(userDto.toString());
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
