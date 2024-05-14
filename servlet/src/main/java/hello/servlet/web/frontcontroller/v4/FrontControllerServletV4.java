package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    //서블릿이 생성될 때 값을 넣어두게 됨, 각 패턴이 오면 미리 저장해둔 값을 꺼내서 쓸 수 있음
    public FrontControllerServletV4() {

        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) { //예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap
        Map<String, String> paramMap = createParamMap(request); //ctrl+alt+m
        Map<String, Object> model = new HashMap<>(); //추가(v3에서)
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);

        view.render(model, request, response);
    }

    //컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경해서 MyView로 반환
    private MyView viewResolver(String viewName) { //물리 이름을 만들어줌
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        //paramMap: 요청 파라미터의 정보를 담는 Map의 이름(username, age 정보를 담아줌)
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
