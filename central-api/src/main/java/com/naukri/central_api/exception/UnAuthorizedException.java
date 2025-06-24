package com.naukri.central_api.exception;

public class UnAuthorizedException extends RuntimeException{

    public UnAuthorizedException(String message) {
        super(message);
    }

}
