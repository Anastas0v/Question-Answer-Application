package com.example.firstproject.exceptions;

public class UserValidationException extends Exception
{
    public UserValidationException(String message)
    {
        super(message);
    }
}