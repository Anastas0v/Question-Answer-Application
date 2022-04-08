package com.example.firstproject;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.example.firstproject.model.User;
import com.example.firstproject.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
public class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser()
    {
        User user = new User();
        user.setEmail("email1234@gmail.com");
        user.setPassword("Password123!@");
        user.setAge(22);
        user.setUserName("User1");

        User savedUser = userRepository.save(user);

        User existUser = userRepository.findByEmail("email1234@gmail.com");

        assertThat(savedUser.getId()).isEqualTo(existUser.getId());
    }
}
