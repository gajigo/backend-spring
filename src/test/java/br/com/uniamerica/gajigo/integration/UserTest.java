package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
    }
    @Test
    public void testDisableUser() throws Exception {
        User user = new User("eduardo123", "minhasenha123", "eduardo de souza magalhaes");
        user.setEmail("eduardo@gmail.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        post(path, json);

        user.setRemoved(true);
        put(path, json);
    }
}
