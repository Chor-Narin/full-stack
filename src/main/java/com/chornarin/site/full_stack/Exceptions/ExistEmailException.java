package com.chornarin.site.full_stack.Exceptions;

public class ExistEmailException extends RuntimeException  {

    public ExistEmailException(String message) {
        super(message + "already existed in the system");
    }

}
