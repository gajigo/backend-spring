package br.com.uniamerica.gajigo.integration;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventTest extends AbstractIntegrationTest {
    public EventTest() {
        super("events");
    }


    public String createUser() {
        return "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
    }

    public String createEvent() {
        return "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"attendanceMode\": \"Online\",\n" +
                "    \"interval\": {\n" +
                "        \"startDate\": \"2023-07-15T00:00:00\",\n" +
                "        \"endDate\": \"2023-07-25T00:00:00\"\n" +
                "    },\n" +
                "    \"owner\": \"http://localhost:8080/api/users/1\"\n" +
                "}";
    }
    @Test
    public void testInsert() throws Exception  {
        String createuser =  "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"inserttest@gmail.com\",\n" +
                "    \"username\": \"testInsert.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createuser).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void testFindById() throws Exception {
        String createuser =  "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testfindbyid@gmail.com\",\n" +
                "    \"username\": \"testFindById.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createuser).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        getById(path, 1L).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void testFindAll() throws Exception {
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createUser()).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        get(path).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdate() throws Exception {
        String createuser =  "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testeupdate@gmail.com\",\n" +
                "    \"username\": \"testUpdate.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createuser).andExpect(status().is2xxSuccessful());
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
    public void testDelete() throws Exception{
        String createuser =  "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testDelete@gmail.com\",\n" +
                "    \"username\": \"testDelete.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createuser).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        delete(path, 1L).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testDisable() throws Exception {
        String createuser =  "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testDisable@gmail.com\",\n" +
                "    \"username\": \"testDisable.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        String caminho = "http://localhost:8080/api/users";
        post(caminho, createuser).andExpect(status().is2xxSuccessful());
        post(path, createEvent()).andExpect(status().is2xxSuccessful());
        String json = "{\n" +
                "    \"removed\": true\n" +
                "}";
        disable(path, 1L, json).andExpect(status().is2xxSuccessful());
    }
}
