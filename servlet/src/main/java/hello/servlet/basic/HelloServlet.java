package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // /hello로 오면 HelloServlet 클래스가 실행됨. name과 urlpatterns 이름 겹치면 안됨
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //25-26: 헤더정보
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); //요즘 인코딩은 utf-8 사용
        response.getWriter().write("hello " + username);


    }
}
