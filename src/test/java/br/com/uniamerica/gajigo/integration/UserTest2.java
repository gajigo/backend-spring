package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "admin", roles = "ADMIN")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserRepository.class)
public class UserTest2 {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repository;

    @Test
    public void findAllUsers() throws Exception {

        User user = new User("username", "password", "name", "fullstack dev", "email@email.com", true);
        List<User> usersList = List.of(user);
        when(repository.findAll()).thenReturn(usersList);
        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("username")));

    }
}
