package com.sfac.javaSpringBoot.modules.test.conntroller;

import com.sfac.javaSpringBoot.modules.test.entity.City;
import com.sfac.javaSpringBoot.modules.test.entity.Country;
import com.sfac.javaSpringBoot.modules.test.service.CityService;
import com.sfac.javaSpringBoot.modules.test.service.CountryService;
import com.sfac.javaSpringBoot.modules.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/test")
public class TestController {
    public final static Logger LOGGER=LoggerFactory.getLogger(TestController.class);
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

  @Autowired
  private CityService cityService;
  @Autowired
  private CountryService countryService;

  @GetMapping("/logTest")
  @ResponseBody
  public String logTest(){
      LOGGER.trace("This is trace log");
      LOGGER.debug("This is debug log");
      LOGGER.info("This is info log");
      LOGGER.warn("This is warn log");
      LOGGER.error("This is error log");
      return "This is log test11111";
  }
//文件下载

    @GetMapping("/file")
  public ResponseEntity<Resource> downloadFile(@RequestParam String fileName){
      Resource resource= null;
      try {
          resource = new UrlResource(Paths.get("C:\\keil\\"+fileName).toUri());
          return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,"application/octet-stream").
                                      header(HttpHeaders.CONTENT_DISPOSITION,String.format("attachment; fileName=\"%s\"", resource.getFilename()))
                                   .body(resource);
      } catch (MalformedURLException e) {
          e.printStackTrace();
      }
     return null;
  }


  //多个文件上传
    @PostMapping(value = "/files",consumes = "multipart/form-data")
    public String updaoFiles(MultipartFile[] files,RedirectAttributes redirectAttributes){
                boolean empt=true;
        for (MultipartFile file:files){
              if (file.isEmpty()){
                  continue;
              }

            try {
                String filePath="C:\\keil\\"+file.getOriginalFilename();
                File file1=new File(filePath);
                file.transferTo(file1);
                empt=false;

                if (empt){
                    redirectAttributes.addFlashAttribute("message","请选择文件");
                }else {
                    redirectAttributes.addFlashAttribute("message","上传成功");
                    return "redirect:/test/index";
                }
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message","发送失败");
            }
        }
        return "redirect:/test/index";
    }

  /*
  * 127.0.0.1/test/file---post
  * */

  //单个文件上传
  @PostMapping(value = "/file",consumes = "multipart/form-data")
  public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes){

        if (file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","请选择文件");
            return "redirect:/test/index";
        }

      try {
          String filepath="C:\\keil\\"+file.getOriginalFilename();
          File file1=new File(filepath);
          file.transferTo(file1);
          redirectAttributes.addFlashAttribute("message","上传成功");
      } catch (IOException e) {
          e.printStackTrace();
          redirectAttributes.addFlashAttribute("message","上传失败");
      }

      return "redirect:/test/index";
  }



/*
* 127.0.0.1/test/index
* */
  @GetMapping("/index")
  public String testIndexPage(ModelMap modelMap) {
      int countryId=522;
      List<City> cities=cityService.getCitysByCountryId(countryId);
      cities = cities.stream().limit(10).collect(Collectors.toList());
      Country country = countryService.getCountryByCountryId(countryId);

      modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
      modelMap.addAttribute("checked", true);
      modelMap.addAttribute("currentNumber", 99);
      modelMap.addAttribute("changeType", "checkbox");
      modelMap.addAttribute("baiduUrl", "/test/log");
      modelMap.addAttribute("city", cities.get(0));
      modelMap.addAttribute("shopLogo",
              "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
      modelMap.addAttribute("shopLogo",
              "/upload/1111.png");
      modelMap.addAttribute("country", country);
      modelMap.addAttribute("cities", cities);
      modelMap.addAttribute("updateCityUri", "/api/city");
      modelMap.addAttribute("thymeleafTitle","Thymeleaf Text");
    // modelMap.addAttribute("template","test/index");


      //返回外层的碎片组装器
      return "index";
  }

    /*http://localhost:8080/test/logTest*/
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

    /*127.0.0.1/test/testdesc?paramKey=fuck*/
    @GetMapping("/testdesc")
    @ResponseBody
    //拦截器
    public String testDesc(HttpServletRequest request, @RequestParam(value = "paramKey") String paramValue){
         String paramValue2=  request.getParameter("paramKey");

        return "Tis is te st module desc"              +paramValue2+"=="+paramValue;
    }
}
