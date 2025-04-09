package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //bu anotasyon bu klasin datayi erisimi saglayan bir klas oldugunu gösterir ve alinan hatalarda aciklama görmemizi saglar
public class StudentRepository implements IRepository<Student,Long> {

    //not: anotasyonlar aslinda bir interfacedir
    @Autowired
    private SessionFactory sessionFactory; //burADA db ile baglanti kurmak icin hibernateden session üretmek icin
            //daha önce oluturdugumuz sessionFactory bean i  @Autowired ile feilde  enjekte ettik

    private Session session;


    //1-c:tablodan tüm satırları getirme
    @Override
    public List<Student> findAll() {
        session=sessionFactory.openSession();
        List<Student> studentList=session.createQuery("FROM Student",Student.class).getResultList();
        session.close();
        return studentList;
    }

    //2-a
    @Override
    public void saveOrUpdate(Student student) {
        session=sessionFactory.openSession();
        Transaction tx =session.beginTransaction(); //degisiklik yapacagimiz icin transaction baslattik
        session.saveOrUpdate(student);
        tx.commit();
        session.close();
    }



    //3-c
    @Override
    public Optional<Student> findById(Long id) {
        session=sessionFactory.openSession();
        Student student=session.get(Student.class,id);
        //get metodu id si verilen öğrenci yoksa null döndürür

        Optional<Student> optional=Optional.ofNullable(student);//Attention!!!
        session.close();
        return optional;
    }

    //4-c
    @Override
    public void delete(Student entity) {
        session=sessionFactory.openSession();
        Transaction tx =session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }
}