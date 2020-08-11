package com.sfac.javaSpringBoot.modules.test.conntroller;

import com.sfac.javaSpringBoot.modules.test.vo.ApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
  @Value("${server.port}")
   private int port;
  @Value("${com.name}")
  private String name;
  @Value("${com.age}")
  private int age;
  @Value("${com.desc}")
  private String desc;
  @Value("${com.random}")
  private String random;

  @Autowired
  private ApplicationTest applicationTest;
    /*http://localhost:8080/test/config*/
      @GetMapping("/config")
      @ResponseBody
    public String  configTest(){
          StringBuffer sb=new StringBuffer();

        sb.append(port).append("-----------")
                .append(name).append("-------------")
                .append(age).append("---------")
                .append(desc).append("--------")
                .append(random).append("-----").append("<br>");
        sb.append(applicationTest.getPort()).append("-----")
                .append(applicationTest.getName()).append("------")
                .append(applicationTest.getAge()).append("------")
                .append(applicationTest.getDesc()).append("----")
                .append(applicationTest.getRandom()).append("----");
        return sb.toString();
    }





















    /*http://localhost:8080/test/testdesc*/
    @GetMapping("/testdesc")
    @ResponseBody
    public String testDemo(){
        return "Tis is te st module desc";
    }
}
