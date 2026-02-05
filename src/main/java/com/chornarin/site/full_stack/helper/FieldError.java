package com.chornarin.site.full_stack.helper;


public record FieldError(
    String error, 
    String messages,
    Object rejectedValue
) {
}
