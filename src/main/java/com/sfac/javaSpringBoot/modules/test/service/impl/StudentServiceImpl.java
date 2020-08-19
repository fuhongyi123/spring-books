package com.sfac.javaSpringBoot.modules.test.service.impl;

import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import com.sfac.javaSpringBoot.modules.test.repository.StudendtRepository;
import com.sfac.javaSpringBoot.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudendtRepository studendtRepository;
    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());  //设置时间
       studendtRepository.saveAndFlush(student);
         return  new Result<Student>(Result.ResultStatus.SUCCESS.status,"insertStudent success",student);
    }


    @Override
    @Transactional
    public Student getStudentByStudentId(int studentId) {
        return studendtRepository.findById(studentId).get();
    }

    @Override
    @Transactional
    public Page<Student> getStudentBySearchvo(SearchVo searchVo) {
        String orderBy = StringUtils.isBlank(searchVo.getOrderBy()) ?
                "studentId" : searchVo.getOrderBy();
        Sort.Direction direction = StringUtils.isBlank(searchVo.getSort()) ||
                searchVo.getSort().equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;

       /* Sort.Direction direction="desc".equalsIgnoreCase(searchVo.getSort())?Sort.Direction.DESC:Sort.Direction.ASC;*/
        Sort sort=new Sort(direction, StringUtils.isBlank(searchVo.getOrderBy())?"studentId":searchVo.getOrderBy());

        Pageable pageable=PageRequest.of(searchVo.getCurrentPage()-1,searchVo.getPageSize(),sort);

        Student student=new Student();
        student.setStudentName(searchVo.getKeyWord());
        ExampleMatcher matcher=ExampleMatcher.matching()
                .withMatcher("studentName",match->match.contains())
                .withIgnorePaths("studentId");
        Example<Student> example=Example.of(student,matcher);


       return studendtRepository.findAll(example,pageable);
      //return null;
    }
}
