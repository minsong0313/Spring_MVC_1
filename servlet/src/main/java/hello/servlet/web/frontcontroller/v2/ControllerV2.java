package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {

    //기존에는 void였고 컨트롤러가 알아서 다 forward로 이동했는데, MyView를 만들어서 넘기는 것으로 인터페이스 설계
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
