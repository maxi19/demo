package com.challenge.demo.error;

public class ExisteUsuarioException extends  Exception{

    public ExisteUsuarioException (String message, Throwable throwable){
        super(message, throwable);
    }

    public ExisteUsuarioException (String message){
        super(message);
    }

}
