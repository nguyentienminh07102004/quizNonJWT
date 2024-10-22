package com.quiz.ExceptionHandlerCustomer;

public class DataInvalidException extends RuntimeException {
    public DataInvalidException(String message) {
        super(message);
    }
}
