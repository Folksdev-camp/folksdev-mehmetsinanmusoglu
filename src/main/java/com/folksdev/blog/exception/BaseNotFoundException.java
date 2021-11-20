package com.folksdev.blog.exception;

public class BaseNotFoundException  extends RuntimeException{
    public BaseNotFoundException(String message){
        super(message);
    }
}
