package com.example.firstproject.services;

import com.example.firstproject.model.Question;
import com.example.firstproject.model.User;
import com.example.firstproject.repository.QuestionRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.exceptions.UserValidationException;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkEmail(String email)
    {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserName(String userName)
    {
        if (userName == null || userName.isEmpty() || userName.length() <= 2)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword(String password)
    {
        String regexPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{7,}";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
        {
            return false;
        }
        return true;
    }

    @Override
    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    @Override
    public List<Question> listAllQuestions()
    {
        return questionRepository.findAll();
    }

    @Override
    public User loginUser(String email, String password) throws UserValidationException
    {
        if (email == null || !checkEmail(email))
        {
            throw new UserValidationException("Invalid E-Mail!");
        }
        if (password == null || !checkPassword(password))
        {
            throw new UserValidationException("Password Validation Error!");
        }

        User dbUser = userRepository.findByEmail(email);
        if (dbUser == null)
        {
            throw new UserValidationException("User Not Found!");
        }
        else if (!passwordEncoder.matches(password, dbUser.getPassword()))
        {
            throw new UserValidationException("Invalid Password!");
        }

        return dbUser;
    }

    @Override
    public User createUser(User user)
    {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setAge(user.getAge());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return newUser;
    }

    @Override
    public String validateUserRegistration(@NotNull User user)
    {
        StringBuilder sb = new StringBuilder();
        if (!checkUserName(user.getUserName()))
        {
            sb.append(" Username Not Valid! \n");
        }
        if (!checkPassword(user.getPassword()))
        {
            sb.append(" Password Not Valid! \n");
        }
        if (!checkEmail(user.getEmail()))
        {
            sb.append(" Email Not Valid!\n");
        }
        else
        {
            User newUser = userRepository.findByEmail(user.getEmail());
            if (newUser != null)
            {
                sb.append("Email Already Exist!\n");
            }
        }
        return sb.toString();
    }
}
