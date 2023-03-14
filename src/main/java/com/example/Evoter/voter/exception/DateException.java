package com.example.Evoter.voter.exception;

public class DateException extends Throwable{
    private String message;
    public DateException(String message) {
        super(message);
    }
}
