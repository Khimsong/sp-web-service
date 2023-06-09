package com.example.demo.exception;

public class NoSuchElementFoundException extends RuntimeException{
    private String msg;
    public NoSuchElementFoundException(String msg){
        super(msg);
    }

}
