package com.chornarin.site.full_stack.helper;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
    int status, 
    String error,
    String message,
    Instant timestamp,
    List<FieldError> errors
) {

    public ErrorResponse(
        int status, String error, String message
    ){
        this(status, error, message, Instant.now(), List.of());
    }

}
