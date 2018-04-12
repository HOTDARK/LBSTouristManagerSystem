package com.xie.tsa;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
@ResponseBody
public class TestAction {

    @RequestMapping("/hello.action")
    public String testMethod(){

        return "hello Test!";
    }
}
