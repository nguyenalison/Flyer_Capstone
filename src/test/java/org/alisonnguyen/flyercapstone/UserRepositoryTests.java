package org.alisonnguyen.flyercapstone;

import org.alisonnguyen.flyercapstone.model.User;
import org.alisonnguyen.flyercapstone.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail(){
        String username = "awin";

        User usernameExists = userRepository.findUserByUserName(username);

        Assertions.assertEquals(username, usernameExists.getUserName());
    }
}
