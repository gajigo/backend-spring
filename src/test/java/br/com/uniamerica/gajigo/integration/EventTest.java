package br.com.uniamerica.gajigo.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventTest extends AbstractIntegrationTest {
    public EventTest() {
        super("events");
    }
    UserTest userTest = new UserTest();
    String userPath = "/api/users";


    public String create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode event = mapper.createObjectNode();
        ObjectNode interval = mapper.createObjectNode();

        interval.put("startDate", "2023-07-15T00:00:00");
        interval.put("endDate", "2023-07-25T00:00:00");
        event.put("name", "nome do evento");
        event.put("attendanceMode", "Online");
        event.put("interval", interval);
        event.put("owner", "http://localhost:8080/api/users/1");

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);
    }

    @Test
    @DirtiesContext
    public void testInsert() throws Exception  {
        post(userPath, userTest.create());
        post(getPath(), create()).andExpect(status().isCreated());
    }

    @Test
    @DirtiesContext
    public void testFindById() throws Exception {
        post(userPath, userTest.create());
        post(getPath(),create());
        getById(this.getPath(), 1L).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testFindAll() throws Exception {
        post(userPath, userTest.create());
        post(getPath(),create());
        get(this.getPath()).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testUpdate() throws Exception {
        post(userPath, userTest.create());
        post(getPath(),create());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode event = mapper.createObjectNode();
        ObjectNode interval = mapper.createObjectNode();

        interval.put("startDate", "2023-07-15T00:00:00");
        interval.put("endDate", "2023-07-25T00:00:00");
        event.put("name", "Novo nome");
        event.put("active", true);
        event.put("attendanceMode", "Online");
        event.put("interval", interval);
        event.put("owner", "http://localhost:8080/api/users/1");
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);

        put(this.getPath(), 1L, json).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testDelete() throws Exception {
        post(userPath, userTest.create());
        post(getPath(),create());
        delete(this.getPath(), 1L).andExpect(status().isNoContent());
    }

    @Test
    @DirtiesContext
    public void testDisable() throws Exception {
        post(userPath, userTest.create());
        post(getPath(),create());
        disable(this.getPath(), 1L).andExpect(status().isOk());
    }
}
