package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller//requestler bu classta karşılanacak ve ilgili metodlara maplenecek, bean üretilmesini de saglar
@RequestMapping("/students")//http:localhost:8080/SpringMvc/students/..... /students ile baslayan istekleri bu clasa yönlendirir
//class : tüm metodlar için geçerli olur
//method: sadece ilgili metod için geçerli olur, bu anotasyon methot seviyesinde de kullanilir, ayri ayri istek varsa metod seviyesinde kullanilir
public class StudentController {

    //NOT: Controllerdan sadece ModelAndView veya String(sayfanın ismi - yeniden yönlendirme)
    // data tipini döndürür!!!

    //listAll:http:localhost:8080/SpringMvc/students
    //student form:http:localhost:8080/SpringMvc/students/new
    //save(submit): http:localhost:8080/SpringMvc/students/saveStudent
    //delete:http:localhost:8080/SpringMvc/students/delete/2
    //update:http:localhost:8080/SpringMvc/students/update?id=2

    //http request: url + body + HTTP Method

    @Autowired
    private StudentService service;


    //örnek
    //http:localhost:8080/SpringMvc/students/hi + GET--okuma
    //@RequestMapping("/students")--class seviyesinde gerekli değil
    @GetMapping("/hi")
    public ModelAndView sayHi(){
        //response hazırlanır
        ModelAndView mav=new ModelAndView(); //ModelAndView ile sayfanin ismini ve cevap olarak gönderecgimiz modele göndririz
        mav.setViewName("hi");
        mav.addObject("message","Hi, ");
        mav.addObject("messagebody","I'm a Student Management App");
        return mav;
    }

    //viewresolver: /WEB-INF/views/hi.jsp dosyasını view katmanından bulur ve mav içinde
    //gönderdiğimiz datayı sayfaya bind eder ve ardından sayfa clienta sunulur.

    //1-tüm öğrencileri listeleme-
    //http:localhost:8080/SpringMvc/students + GET
    @GetMapping //bu anotaycon ile mtod bazinda veri alma islemi yapariz
    public ModelAndView getAllStudents(){
        List<Student> allStudent=service.findAllStudents();
        ModelAndView mav=new ModelAndView();
        mav.setViewName("students");
        mav.addObject("studentList",allStudent);
        return mav;
    }
    //2-add student linki
    //request  : http://localhost:8080/SpringMvc/students/new + GET islemi
    //response : boş formu göstermek
    @GetMapping("/new")
    public String sendForm(@ModelAttribute("student") Student student){
        //student: firstName, lastName, grade, id=null
        return "studentForm";
    }

    //@ModelAttribute anotasyonu view katmanı ile controller arasında
    //modelın (datalarin) transferini sağlar

    //işlem sonunda : studentın firstName, lastName ve grade fieldları set edilmiş halde hazır

    //2-a: saveStudent
    //request : save(submit): http:localhost:8080/SpringMvc/students/saveStudent + POST + form(student)
    //response: tabloya yeni öğrenci eklenecek ve tüm öğrencilerin listesi
    //response : tüm öğrencileri listeleme isteği daha önce karşılandı, yeniden yönlendirme yapılacak
    //redirect: http:localhost:8080/SpringMvc/students + GET
    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult){

        //valid anotasyonu yapilan kisitlamalari kontrol eder, parametreden önce yazilir
        // valid ile doğrulama yapılırken hata varsa bindingResult objesinde tutulur
        //hata varsa yeniden formu göstererek hataların düzeltilmesini ve de mesajların
        //görüntülenmesini sağlayabiliriz.
        if (bindingResult.hasErrors()){
            return "studentForm";
        }
        service.saveOrUpdateStudent(student);
        return "redirect:/students"; //http:localhost:8080/SpringMvc/students + GET /redirect keyworduyle yeniden
        //yönlendirilme yapilir, dogrudan url adresine yapilir

    }

    //3-öğrenciyi güncelleme
    //request  : http:localhost:8080/SpringMvc/students/update?id=2 + GET
    //response : update edilecek öğrencinin mevcut bilgileri ile formu gösterme
    //id si verilen öğrenciyi bulmamız gerekiyor
    @GetMapping("/update")
    public ModelAndView sendUpdateForm(@RequestParam("id") Long identity){
        Student foundStudent=service.findStudentById(identity);

        ModelAndView mav=new ModelAndView();
        mav.setViewName("studentForm");
        mav.addObject("student",foundStudent); //student degiskenine foundStudent degeri ver diyoruz
        return mav;
    }

    //@RequestParam anotasyonu parametre olarak verilen fieldeki degiskenin degerini yaninda yazan degiskene
    //set eder


    //4-
    //request : http://localhost:8080/SpringMvc/students/delete/3


    //istemciden bilgi(data) nasıl alınır
    //1-form/body(JSON)
    //2-query param : /query?id=3&name=Ali
    //3-path param  : /student/3/Ali


















}
