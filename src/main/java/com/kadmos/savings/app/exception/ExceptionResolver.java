package com.kadmos.savings.app.exception;

import com.kadmos.savings.app.model.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.Date;

@ControllerAdvice
public class ExceptionResolver {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionResolver.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SavingsBaseException.class)
    public RestResponse<Object> resolveErrors(Exception e) {
        LOG.error("resolved exception: {}", e.getMessage());
        RestResponse<Object> response = new RestResponse<>();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTimestamp(new Timestamp(new Date().getTime()));
        return response;
    }
}
