package br.com.uniamerica.gajigo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
    }

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
