package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Interval;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventTest extends AbstractIntegrationTest {
    public EventTest() {
        super("events");
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

    public String createEvent() throws JsonProcessingException {
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
        String caminho = "http://localhost:8080/api/users";

        post(caminho, createUser()).andExpect(status().isCreated());
        post(path, createEvent()).andExpect(status().isCreated());

    }

    @Test
    @DirtiesContext
    public void testFindById() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().isCreated());
        post(path, createEvent()).andExpect(status().isCreated());
        getById(path, 1L).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testFindAll() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().isCreated());
        post(path, createEvent()).andExpect(status().isCreated());
        get(path).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testUpdate() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().isCreated());
        post(path, createEvent()).andExpect(status().isCreated());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode event = mapper.createObjectNode();
        ObjectNode interval = mapper.createObjectNode();

        interval.put("startDate", "2023-07-15T00:00:00");
        interval.put("endDate", "2023-07-25T00:00:00");

        event.put("name", "Novo nome");
        event.put("removed", false);
        event.put("attendanceMode", "Online");
        event.put("interval", interval);
        event.put("owner", "http://localhost:8080/api/users/1");

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);
        put(path, 1L, json).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testDelete() throws Exception{
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().isCreated());
        post(path, createEvent()).andExpect(status().isCreated());
        delete(path, 1L).andExpect(status().isNoContent());
    }

    @Test
    @DirtiesContext
    public void testDisable() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().isCreated());
        post(path, createEvent()).andExpect(status().isCreated());
        disable(path, 1L).andExpect(status().isOk());
    }
}
