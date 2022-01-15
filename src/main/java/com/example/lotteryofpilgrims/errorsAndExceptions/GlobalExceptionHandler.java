package com.example.lotteryofpilgrims.errorsAndExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Ahmed on 9/28/2021.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleObjectNotFoundException(ObjectNotFoundException exc) {

        /** 1: create errorResponse */
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value()
                , exc.getMessage()
                , System.currentTimeMillis());

        /** return errorResponse */
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAnyException(Exception exc) {

        /** 1: create errorResponse */
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value()
                , exc.getMessage()
                , System.currentTimeMillis());

        /** return errorResponse */
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
