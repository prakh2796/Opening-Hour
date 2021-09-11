package com.example.wolt.exception;

import com.example.wolt.dto.response.ErrorResponse;
import com.example.wolt.dto.response.ServiceResponse;
import com.example.wolt.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHelper {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponse> handleException(MethodArgumentNotValidException exception) {
        logger.info("Invalid JSON String with exception: {}", exception.toString());
        return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING), HttpStatus.BAD_REQUEST);
    }
}
