package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.AttendanceMode;
import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.EventStatus;
import br.com.uniamerica.gajigo.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
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

        post(path, json).andExpect(status().is2xxSuccessful());

        json = "{\n" +
                "    \"removed\": true\n" +
                "}";
        disable(path, 1L, json).andExpect(status().is2xxSuccessful());
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

        post(path, json).andExpect(status().is2xxSuccessful());

        get(path).andExpect(status().is2xxSuccessful());
    }
}
