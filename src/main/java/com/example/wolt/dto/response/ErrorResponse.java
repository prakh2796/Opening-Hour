package com.example.wolt.dto.response;

public class ErrorResponse extends ServiceResponse {
    public ErrorResponse(String message){
        super("failure", message, null);
    }
}
