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

        post(caminho, createUser()).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());

    }
    @Test
    @DirtiesContext
    public void testFindById() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        getById(path, 1L).andExpect(status().is2xxSuccessful());
    }
    @Test
    @DirtiesContext
    public void testFindAll() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        get(path).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext
    public void testUpdate() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        String json = "{\n" +
                "    \"name\": \"Novo Nome\",\n" +
                "    \"removed\": false,\n" +
                "    \"attendanceMode\": \"Online\",\n" +
                "    \"interval\": {\n" +
                "        \"startDate\": \"2023-07-15T00:00:00\",\n" +
                "        \"endDate\": \"2023-07-25T00:00:00\"\n" +
                "    },\n" +
                "    \"owner\": \"http://localhost:8080/api/users/1\"\n" +
                "}";
        put(path, 1L, json).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext
    public void testDelete() throws Exception{
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        delete(path, 1L).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DirtiesContext
    public void testDisable() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        String json = "{\n" +
                "    \"removed\": true\n" +
                "}";
        disable(path, 1L, json).andExpect(status().is2xxSuccessful());
    }
}
