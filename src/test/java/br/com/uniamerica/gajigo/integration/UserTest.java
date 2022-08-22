package br.com.uniamerica.gajigo.integration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
    }

    @Test
    public void findAllUsers() throws Exception {
        get(path).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void postUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
                ObjectNode user = mapper.createObjectNode();
        user.put("name", "John Doe");
        user.put("email", "john.doe@example.com");
        user.put("username", "JohnDoe");
        user.put("password", "john.doe");

        String json = mapper.writeValueAsString(user);

        post(path,json).andExpect(status().isCreated());
        get(path).andExpect(content().string(containsString("John Doe")));

    }

    @Test
    public void createAndDeleteUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("name", "John Doe");
        user.put("email", "john.doe@example.com");
        user.put("username", "JohnDoe");
        user.put("password", "john.doe");

        String json = mapper.writeValueAsString(user);

        post(path,json).andExpect(status().isCreated());
        get(path).andExpect(content().string(containsString("John Doe")));

        mockMvc.perform( MockMvcRequestBuilders.delete("/api/users/{id}",1))
                .andExpect(status().is2xxSuccessful());

    }

}
