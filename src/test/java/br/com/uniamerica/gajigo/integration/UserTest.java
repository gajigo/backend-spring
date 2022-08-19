package br.com.uniamerica.gajigo.integration;


import org.junit.jupiter.api.Test;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
    }

    @Test
    public void testInsert() throws Exception {
        post(path, createdUser()).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdate() throws Exception {
        post(path, createdUser()).andExpect(status().is2xxSuccessful());

        String json = "{\n" +
                "    \"name\": \"Novo Nome\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"removed\": false,\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        put(path,1L, json).andExpect(status().is2xxSuccessful());
    }
    @Test
    public void testDisable() throws Exception {
        post(path, createdUser()).andExpect(status().is2xxSuccessful());

        String json = "{\n" +
                "    \"removed\": true\n" +
                "}";
        disable(path, 1L, json).andExpect(status().is2xxSuccessful());
    }

    public String createdUser() {
        return "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"eduardo@gmail.com\",\n" +
                "    \"username\": \"eduardo.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
    }

    @Test
    public void testFindById() throws Exception {
        post(path, createdUser()).andExpect(status().is2xxSuccessful());
        getById(path, 1L);
    }
    @Test
    public void testFindAll() throws Exception {
        post(path, createdUser()).andExpect(status().is2xxSuccessful());
        get(path).andExpect(status().is2xxSuccessful());
    }
}
