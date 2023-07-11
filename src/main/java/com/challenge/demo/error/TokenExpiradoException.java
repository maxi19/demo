package com.challenge.demo.error;

public class TokenExpiradoException extends  Exception{
    public TokenExpiradoException(String message, Throwable throwable){
        super(message, throwable);
    }

    public TokenExpiradoException(String message){
        super(message);
    }
}
