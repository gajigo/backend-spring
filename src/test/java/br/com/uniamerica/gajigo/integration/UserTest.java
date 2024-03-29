package br.com.uniamerica.gajigo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import org.springframework.security.test.context.support.WithMockUser;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.JsonProcessingException;

@WithMockUser(username = "admin", roles = "ADMIN")
public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
    }

    @Test
    public void findAllUsers() throws Exception {
        get(getPath()).andExpect(status().is2xxSuccessful());
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

        post(getPath(), json).andExpect(status().isCreated());
        get(getPath()).andExpect(content().string(containsString("John Doe")));

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

        post(getPath(), json).andExpect(status().isCreated());
        get(getPath()).andExpect(content().string(containsString("John Doe")));

        delete("/api/users/", 1L)
                .andExpect(status().isNoContent());

    }

    public String createUser() throws JsonProcessingException {

    public String create() throws Exception {
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
        post(this.getPath(), this.create()).andExpect(status().isCreated());
    }

    @Test
    @DirtiesContext
    public void testUpdate() throws Exception {
        post(this.getPath(), this.create());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("name", "eduardo");
        user.put("email", "eduardo@gmail.com");
        user.put("username", "eduardo.sm");
        user.put("password", "123");
        user.put("active", true);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        put(this.getPath(), 1L, json).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testDisable() throws Exception {
        MvcResult result = post(this.getPath(), this.create())
                .andExpect(status().isCreated())
                .andReturn();
        disable(getLinkToSelf(result.getResponse().getContentAsString())).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testFindById() throws Exception {
        post(this.getPath(), this.create());
        getById(this.getPath(), 1L).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testFindAll() throws Exception {
        post(this.getPath(), this.create());
        get(this.getPath()).andExpect(status().isOk());
    }
}
