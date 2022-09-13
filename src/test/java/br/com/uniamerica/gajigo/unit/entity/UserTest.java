package br.com.uniamerica.gajigo.unit.entity;

import br.com.uniamerica.gajigo.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}
