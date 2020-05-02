package com.pradipta.polly.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldName));
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue, Throwable cause) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldName), cause);
    }
}
