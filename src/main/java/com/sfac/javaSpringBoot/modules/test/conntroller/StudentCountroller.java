package com.sfac.javaSpringBoot.modules.test.conntroller;




import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import com.sfac.javaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentCountroller {

    @Autowired
    private StudentService studentService;
    /*
    * 127.0.0.1/api/student---post
    *{"studentName":"java","studentCard":{"cardId":"1"}}
    * */
    @PostMapping(value = "/student" ,consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }

    /*
    * 127.0.0.1/api/studentId/1 --get
    * {"studentId":"1"}
    * */
    @GetMapping("/studentId/{studentId}")
    public Student getstudentByStudentId(@PathVariable int studentId){
     return studentService.getStudentByStudentId(studentId);
    }

    /*
    * 127.0.0.1/api/students
    * {"currentPage":"1","pageSize":"5","orderBy":"studentName","keyWord":"java","sort":"desc"}
    * */
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> getStudntBySearchVo(@RequestBody SearchVo searchVo){
        return  studentService.getStudentBySearchvo(searchVo);
    }
}
