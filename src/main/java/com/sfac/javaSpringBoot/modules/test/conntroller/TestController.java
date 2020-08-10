package com.sfac.javaSpringBoot.modules.test.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    /*http://localhost:8080/test/testdesc*/
    @GetMapping("/testdesc")
    @ResponseBody
    public String testDemo(){
        return "Tis is te st module desc";
    }
}
