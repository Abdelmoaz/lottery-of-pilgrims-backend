package com.example.lotteryofpilgrims.errorsAndExceptions;

/**
 * Created by Ahmed on 9/28/2021.
 */
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
