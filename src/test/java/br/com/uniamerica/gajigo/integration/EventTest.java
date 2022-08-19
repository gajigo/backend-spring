package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.AttendanceMode;
import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.EventStatus;
import br.com.uniamerica.gajigo.entity.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class EventTest extends AbstractIntegrationTest {
    public EventTest() {
        super("events");
    }


    @Test
    public void testInsert() throws Exception  {
        String json = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";

        String caminho = "http://localhost:8080/api/users";
        post(caminho, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"attendanceMode\": \"Online\",\n" +
                "    \"interval\": {\n" +
                "        \"startDate\": \"2023-07-15T00:00:00\",\n" +
                "        \"endDate\": \"2023-07-25T00:00:00\"\n" +
                "    },\n" +
                "    \"owner\": \"http://localhost:8080/api/users/1\"\n" +
                "}";
        post(path, json).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void testFindById() throws Exception {
        String json = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";

        String caminho = "http://localhost:8080/api/users";
        post(caminho, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"attendanceMode\": \"Online\",\n" +
                "    \"interval\": {\n" +
                "        \"startDate\": \"2023-07-15T00:00:00\",\n" +
                "        \"endDate\": \"2023-07-25T00:00:00\"\n" +
                "    },\n" +
                "    \"owner\": \"http://localhost:8080/api/users/1\"\n" +
                "}";
        post(path, json).andExpect(status().is2xxSuccessful());

        getById(path, 1L).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void testFindAll() throws Exception {
        String json = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";

        String caminho = "http://localhost:8080/api/users";
        post(caminho, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"attendanceMode\": \"Online\",\n" +
                "    \"interval\": {\n" +
                "        \"startDate\": \"2023-07-15T00:00:00\",\n" +
                "        \"endDate\": \"2023-07-25T00:00:00\"\n" +
                "    },\n" +
                "    \"owner\": \"http://localhost:8080/api/users/1\"\n" +
                "}";
        post(path, json).andExpect(status().is2xxSuccessful());

        get(path).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdate() throws Exception {
        String json = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";

        String caminho = "http://localhost:8080/api/users";
        post(caminho, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"attendanceMode\": \"Online\",\n" +
                "    \"interval\": {\n" +
                "        \"startDate\": \"2023-07-15T00:00:00\",\n" +
                "        \"endDate\": \"2023-07-25T00:00:00\"\n" +
                "    },\n" +
                "    \"owner\": \"http://localhost:8080/api/users/1\"\n" +
                "}";
        post(path, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"name\": \"Lucas\",\n" +
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
    public void testDelete() throws Exception{
        String json = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";

        String caminho = "http://localhost:8080/api/users";
        post(caminho, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"attendanceMode\": \"Online\",\n" +
                "    \"interval\": {\n" +
                "        \"startDate\": \"2023-07-15T00:00:00\",\n" +
                "        \"endDate\": \"2023-07-25T00:00:00\"\n" +
                "    },\n" +
                "    \"owner\": \"http://localhost:8080/api/users/1\"\n" +
                "}";
        post(path, json).andExpect(status().is2xxSuccessful());

        delete(path, 1L).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testDisable() throws Exception {
        String json = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";

        String caminho = "http://localhost:8080/api/users";
        post(caminho, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"attendanceMode\": \"Online\",\n" +
                "    \"interval\": {\n" +
                "        \"startDate\": \"2023-07-15T00:00:00\",\n" +
                "        \"endDate\": \"2023-07-25T00:00:00\"\n" +
                "    },\n" +
                "    \"owner\": \"http://localhost:8080/api/users/1\"\n" +
                "}";
        post(path, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"removed\": true\n" +
                "}";
        disable(path, 1L, json).andExpect(status().is2xxSuccessful());;
    }
}
