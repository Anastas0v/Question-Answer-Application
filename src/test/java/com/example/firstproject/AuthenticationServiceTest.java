package com.example.firstproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import com.example.firstproject.model.User;
import com.example.firstproject.services.AuthenticationServiceImpl;
import com.example.firstproject.exceptions.UserValidationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationServiceTest
{
    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Test
    public void checkEmailTests()
    {
        String invalidEmail = "david.invalid.com";
        String invalidEmail1 = "david.invalid";
        String validMail = "david.valid@gmail.com";
        assertThat(authenticationServiceImpl.checkEmail(invalidEmail)).isEqualTo(false);
        assertThat(authenticationServiceImpl.checkEmail(invalidEmail1)).isEqualTo(false);
        assertThat(authenticationServiceImpl.checkEmail(validMail)).isEqualTo(true);
    }

    @Test
    public void validateUserRegistrationValidInputs()
    {
        User user = new User();
        user.setEmail("david@gmail.com");
        user.setUserName("David");
        user.setPassword("David123!@#");
        user.setAge(22);

        String result = authenticationServiceImpl.validateUserRegistration(user);
        assertThat(result).isEqualTo("");
    }

    @Test
    public void validateUserRegistrationInvalidPassword()
    {
        User user = new User();
        user.setEmail("david@gmail.com");
        user.setUserName("David");
        user.setPassword("david1111");
        user.setAge(22);

        String result = authenticationServiceImpl.validateUserRegistration(user);
        assertThat(result).isEqualTo(" Password Not Valid! \n");
    }

    @Test
    public void validateUserRegistrationInvalidEmail()
    {
        User user = new User();
        user.setEmail("david.invalid.com");
        user.setUserName("David");
        user.setPassword("David123!@#");
        user.setAge(22);

        String result = authenticationServiceImpl.validateUserRegistration(user);
        assertThat(result).isEqualTo(" Email Not Valid!\n");
    }

    @Test
    public void validateUserRegistrationInvalidUsername()
    {
        User user = new User();
        user.setEmail("david@gmail.com");
        user.setUserName("D");
        user.setPassword("David123!@#");
        user.setAge(22);

        String result = authenticationServiceImpl.validateUserRegistration(user);
        assertThat(result).isEqualTo(" Username Not Valid! \n");
    }

    @Test
    public void loginUserInvalidEmail()
    {
        User user = new User();
        user.setEmail("david.invalid.com");
        user.setUserName("David");
        user.setPassword("David123!@#");
        user.setAge(22);

        UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
            authenticationServiceImpl.loginUser(user.getEmail(), user.getPassword());
        });

        assertThat(userValidationException.getMessage()).isEqualTo("Invalid E-Mail!");
    }

    @Test
    public void loginUserInvalidPassword()
    {
        User user = new User();
        user.setEmail("david.valid@gmail.com");
        user.setUserName("David");
        user.setPassword("David12");
        user.setAge(22);

        UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
            authenticationServiceImpl.loginUser(user.getEmail(), user.getPassword());
        });
        assertThat(userValidationException.getMessage()).isEqualTo("Password Validation Error!");
    }

    @Test
    public void loginUserNotFound()
    {
        User user = new User();
        user.setEmail("david.valid@gmail.com");
        user.setUserName("David");
        user.setPassword("David123!@#");
        user.setAge(22);

        UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
            authenticationServiceImpl.loginUser(user.getEmail(), user.getPassword());
        });
        assertThat(userValidationException.getMessage()).isEqualTo("User Not Found!");
    }

    @Test
    public void loginUserPasswordNotMatch()
    {
        User user = new User();
        user.setEmail("david.anastasov1@gmail.com");
        user.setUserName("David");
        user.setPassword("David123!@#");
        user.setAge(22);

        UserValidationException userValidationException = assertThrows(UserValidationException.class, () -> {
            authenticationServiceImpl.loginUser(user.getEmail(), user.getPassword());
        });
        assertThat(userValidationException.getMessage()).isEqualTo("Invalid Password!");
    }

    @Test
    public void loginUserSuccess() throws UserValidationException
    {
        User expected = new User();
        expected.setEmail("david.anastasov1@gmail.com");
        expected.setUserName("David");
        expected.setPassword("David123!");
        expected.setAge(22);

        User actual = authenticationServiceImpl.loginUser(expected.getEmail(), expected.getPassword());
        assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
    }
}