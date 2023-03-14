package com.example.Evoter.voter.exception;

public class VoterException extends  Throwable{
    private String message;

    public VoterException(String message) {
        super(message);

    }
}
