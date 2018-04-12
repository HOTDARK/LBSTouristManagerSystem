package com.xie.tsa.action.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test/")
@Controller
public class Test {

    @RequestMapping("/test")
    public String test(){

        return "test.jsp";
    }
    @RequestMapping("/workerTest")
    public String testWorker(){

        return "workerTest.jsp";
    }
}
