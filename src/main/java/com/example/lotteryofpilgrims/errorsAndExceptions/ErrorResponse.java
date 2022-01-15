package com.example.lotteryofpilgrims.errorsAndExceptions;


/**
 * Created by Ahmed on 9/28/2021.
 */
public class ErrorResponse {
    private int status;
    private String message;
    private long time;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, long time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
