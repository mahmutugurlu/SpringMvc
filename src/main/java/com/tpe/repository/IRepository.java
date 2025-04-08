package com.tpe.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T,ID> {

    List<T> findAll();
    void saveOrUpdate(T entity);
    void delete(T entity);

    Optional<T> findById(ID id);// Optional NullPointerException almamak için
    //null yerine boş bir optional objesi döndürülür
    //Dikkat!null çıkabilir.
}