package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller-->dynamic web uygulamalarında requestlerin karşılanması için kullanılır
@RestController//bu classta restful serviceler geliştirilecek
//@ResponseBody:bu classtaki metodların geriye dönüş değerlerini Jackson kütüphanesini
//kullanarak otomatik olarak JSON formatına dönüştürür

public class StudentRestController {

    @Autowired
    private StudentService service;

    //1-tüm öğrencileri listeleme
    //request: http://localhost:8080/SpringMvc/api/allstudents + GET
    //response : tüm öğrenciler

    @RequestMapping("/api/allstudents")
    @GetMapping
    public List<Student> allStudents(){
        return service.findAllStudents();
    }
    //client : frontend  <-JSON->  backend:Rest API



    //practice
    //2-idsi verilen öğrenciyi görüntüleme
    //request: http://localhost:8080/SpringMvc/student/5 + GET
    //response : idsi verilen öğrencinin tüm bilgileri
}








