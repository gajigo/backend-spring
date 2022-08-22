package br.com.uniamerica.gajigo.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
    }

    public String createUser() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("name", "eduardo");
        user.put("email", "eduardo@gmail.com");
        user.put("username", "eduardo.sm");
        user.put("password", "123");

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
    }

    @Test
    @DirtiesContext
    public void testInsert() throws Exception {
        post(path, createUser()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext
    public void testUpdate() throws Exception {

        post(path, createUser()).andExpect(status().is2xxSuccessful());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("name", "eduardo");
        user.put("email", "eduardo@gmail.com");
        user.put("username", "eduardo.sm");
        user.put("password", "123");
        user.put("removed", false);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        put(path,1L, json).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext
    public void testDisable() throws Exception {
        post(path, createUser()).andExpect(status().is2xxSuccessful());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("removed", true);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        disable(path, 1L, json).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext
    public void testFindById() throws Exception {
        post(path, createUser()).andExpect(status().is2xxSuccessful());
        getById(path, 1L);
    }

    @Test
    @DirtiesContext
    public void testFindAll() throws Exception {
        post(path, createUser()).andExpect(status().is2xxSuccessful());
        get(path).andExpect(status().is2xxSuccessful());
    }
}
