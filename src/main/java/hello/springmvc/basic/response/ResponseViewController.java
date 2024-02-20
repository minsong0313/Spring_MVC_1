package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data","hello!");
        return "response/hello"; //@ResponseBody가 붙으면 뷰가 아닌 문자가 그대로 출력됨
    }

    @RequestMapping("/response/hello")//컨트롤러의 경로와 뷰의 논리적 이름이 똑같으면 뷰 불러옴/ 권장 하지 않는 방법
    public void responseViewV3(Model model) {
        model.addAttribute("data","hello!");
    }

}
