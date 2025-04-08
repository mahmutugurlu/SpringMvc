package com.tpe.exceptable;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String s) {
        super(s);
    }
}