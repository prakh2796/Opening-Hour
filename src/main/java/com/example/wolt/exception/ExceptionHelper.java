package com.example.wolt.exception;

import com.example.wolt.dto.response.ErrorResponse;
import com.example.wolt.dto.response.ServiceResponse;
import com.example.wolt.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        logger.info("MethodArgumentNotValidException with exception: {}", exception.getMessage());
        return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING + ". " + Constants.DAY_MSSING), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, JsonProcessingException.class})
    public ResponseEntity<ServiceResponse> handleJsonProcessingException(Exception exception) {
        logger.info("JsonProcessingException with exception: {}", exception.getMessage());
        return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceResponse> handleException(Exception exception) {
        logger.info("Internal Server Error: {}", exception.getStackTrace());
        return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
