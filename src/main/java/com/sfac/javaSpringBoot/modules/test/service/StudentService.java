package com.sfac.javaSpringBoot.modules.test.service;

import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    Result<Student> insertStudent(Student student);

    Student getStudentByStudentId(int studentId);

    Page<Student> getStudentBySearchvo(SearchVo searchVo);
}
