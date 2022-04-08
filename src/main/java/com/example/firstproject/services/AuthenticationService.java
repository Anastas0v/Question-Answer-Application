package com.example.firstproject.services;

import com.example.firstproject.model.Question;
import com.example.firstproject.model.User;
import com.example.firstproject.exceptions.UserValidationException;

import java.util.List;

public interface AuthenticationService
{
    boolean checkEmail(String email);
    boolean checkUserName(String userName);
    boolean checkPassword(String password);
    String validateUserRegistration(User user);
    void saveUser(User user);
    User createUser(User user);

    User loginUser(String email, String password) throws UserValidationException;

    List<Question> listAllQuestions();
}
