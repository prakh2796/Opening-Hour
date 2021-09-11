package com.example.wolt.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceResponse {
    private Date timeStamp = new Date();
    private String status;
    private String message;
    private Object data;

    public ServiceResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ServiceResponse(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
