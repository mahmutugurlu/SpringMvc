package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exceptable.StudentNotFoundException;

import com.tpe.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//iş katmanı
public class StudentService {

    @Autowired //field enjektion kullandik
    private IRepository<Student,Long> repository;

    //1-b
    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    //2-b : öğrenci ekleme/veya güncelleme
    public void saveOrUpdateStudent(Student student){
        repository.saveOrUpdate(student);
    }

    //3-b
   public Student findStudentById(Long identity) {//id:99
        Student found= repository.findById(identity).
                orElseThrow(()->new StudentNotFoundException("Student not found by id: "+identity));
       //eger found a deger atanamazsa  orElseThrow hata firlatacak

        //supplier interface :sadece get metodu var
        //doğrudan metodun parametresinde bu interface lambda exp. ile implemente edilir

        //findById metodu geriye döndürdüğü
        // optional içinde student varsa found değişkenine set eder
        //optional objesi boşsa orElseThrow custom exception fırlatabilir
        //get(): eger bos deger dönerse NoSuchElementException firlatir
        return found;
    }


}