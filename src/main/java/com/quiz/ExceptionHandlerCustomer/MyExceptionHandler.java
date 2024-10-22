package com.quiz.ExceptionHandlerCustomer;

import com.quiz.Model.Response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = DataInvalidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public APIResponse DataInvalidExceptionHandler(DataInvalidException exception) {
        return APIResponse.builder()
                .message(exception.getMessage())
                .response(exception)
                .build();
    }
}
