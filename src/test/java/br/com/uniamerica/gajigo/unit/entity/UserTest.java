package br.com.uniamerica.gajigo.unit.entity;

import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.UserRepository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest {
    @Test
    public void UserTest() {
        User user = new User("username","password","name","fullstack dev","email@email.com",true);
        assertEquals("name", user.getName());
        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertNotNull(user.getEmail());
        assertTrue(!user.getUsername().isEmpty() & !user.getPassword().isEmpty() & !user.getEmail().isEmpty());
        assertTrue(user.isAdmin());

    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repository;

    @Test
    public void findAllUsers() throws Exception {
        User user = new User("username", "password", "name", "fullstack dev", "email@email.com", true);
        List<User> userList = List.of(user);
        when(repository.findAll()).thenReturn(userList);
        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("username")));
    }
}
