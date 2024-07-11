package com.booking.airline.exeption;

public class LoginException extends Exception{
    public LoginException (String errorMessage){
        super(errorMessage);
    }
}
