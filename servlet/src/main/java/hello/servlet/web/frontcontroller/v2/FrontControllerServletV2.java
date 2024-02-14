package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 작동 순서 예) 1./front-controller/v2/members/new-form -> 아래 서블릿이 호출됨
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    //서블릿이 생성될 때 값을 넣어두게 됨, 각 패턴이 오면 미리 저장해둔 값을 꺼내서 쓸 수 있음
    public FrontControllerServletV2() {

        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 2.컨트롤러에서 해당 url의 컨트롤러(MemberFormControllerV2)를 찾아옴
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) { //예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 3.프로세스 호출함
        // 4.MemberFormControllerV2 클래스의 new MyView가 넘겨옴(new MyView("/WEB-INF/views/new-form.jsp");)
        MyView view = controller.process(request, response);
        view.render(request, response);
        // 5.넘어온 주소값의 랜더를 호출함 -> MyView에 있는 render() 실행, jsp에서 실제 html 결과가 웹브라우저에 출력

    }
}
