package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
    }

    @MockBean
    private UserRepository repository;

    @Test
    public void findAllUsers() throws Exception {
        User user = new User("username", "password", "name", "fullstack dev", "email@email.com", true);
        List<User> userList = List.of(user);
        when(repository.findAll()).thenReturn(userList);

    }
}
