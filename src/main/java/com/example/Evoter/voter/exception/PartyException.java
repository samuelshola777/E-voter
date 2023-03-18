package com.example.Evoter.voter.exception;

public class PartyException extends Exception{
    private String message;

    public PartyException(String message) {
        super(message);
    }
}
