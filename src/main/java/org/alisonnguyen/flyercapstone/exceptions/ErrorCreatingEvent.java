package org.alisonnguyen.flyercapstone.exceptions;

public class ErrorCreatingEvent extends RuntimeException {
    public ErrorCreatingEvent(String message){
        super(message);
    }
}
