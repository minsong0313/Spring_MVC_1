package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    //1. HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 직접 응답 메시지 전달
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    //2. ResponseEntity는 HttpEntity를 상속받음, HttpEntity는 HTTP 메시지의 헤더, 바디 정보를 가지고 있음
    // ResponseEntity는 여기에 더해서 HTTP 응답 코드 설정 가능
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //3. @ResponseBody 사용하면 view 사용하지 않고, HTTP 메시지 컨버터를 통해서 HTTP 메시지를 직접 입력 가능
    // ResponseEntity도 동일한 방식으로 동작함
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    //4. ResponseEntity를 반환, HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어서 반환
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);

    }

    //5. ResponseEntity는 HTTP 응답 코드를 설정할 수 있음, @ResponseBody를 사용하면 이런 것 설정하기 까다로움
    //   @ResponseStatus 애노테이션으로 응답 코드 설정 가능, 응답 코드 동적으로 변경 불가능
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }

    /** 메서드별로 @ResponseBody 붙이는게 번거로우면 클래스 레벨에 붙이면 됨, 클래스 내부 모든 메서드에 적용됨
     * 클래스에 레벨에 붙는 @Controller와  @ResponseBody를 합친 것이 @RestController!!
     */





}
