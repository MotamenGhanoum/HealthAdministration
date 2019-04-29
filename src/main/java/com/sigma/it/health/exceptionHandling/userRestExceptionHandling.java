package com.sigma.it.health.exceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class userRestExceptionHandling {


    @ExceptionHandler
    public ResponseEntity<userErrorResponse> handleException(userNotFoundException exc){
        userErrorResponse error=new userErrorResponse();
        error.setStatue(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTime(System.currentTimeMillis());
        return  new ResponseEntity<>(error , HttpStatus.NOT_FOUND);

    }


    // TO CATCH ALL EXEPTIONS

    @ExceptionHandler
    public ResponseEntity<userErrorResponse>handleExeption(Exception exc){

        userErrorResponse error=new userErrorResponse();
        error.setStatue(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTime(System.currentTimeMillis());
        return  new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);

    }
}
