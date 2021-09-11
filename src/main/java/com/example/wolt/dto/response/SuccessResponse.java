package com.example.wolt.dto.response;

import com.example.wolt.utils.Constants;

public class SuccessResponse extends ServiceResponse {

    public SuccessResponse(Object data) {
        super("success", Constants.OPERATION_SUCCESS, data);
    }
}
