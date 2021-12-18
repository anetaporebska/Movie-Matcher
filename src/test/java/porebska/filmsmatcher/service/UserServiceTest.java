package porebska.filmsmatcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import porebska.filmsmatcher.model.User;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void addUser() {
        User user1 = User.builder()
                .name("test4")
                .login("test4")
                .password("test-passwd")
                .roles("ADMIN")
                .active(true)
                .build();
        userService.addUser(user1);
    }

    @Test
    void addPreference() {
    }
}