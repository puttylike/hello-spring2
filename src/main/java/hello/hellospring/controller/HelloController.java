package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 문자열로 보내기
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 이 문자가 그대로 http body에 담겨져 내려감
    }


    // 객체로 보내기
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // {"name": "들어온 인지값"} 처럼 json 형태로 반환
    }

    // getter setter를 가진 클래스 생성하기
    // 변수는 private으로 접근할 수 있는 getter setter는 public으로 해서 getter setter 통해서 접근함
    // property 접근 방식이라고도 함
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}