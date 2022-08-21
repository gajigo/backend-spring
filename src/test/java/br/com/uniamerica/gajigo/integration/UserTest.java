package br.com.uniamerica.gajigo.integration;


import org.junit.jupiter.api.Test;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserTest extends AbstractIntegrationTest {
    public UserTest() {
        super("users");
    }

    @Test
    public void testInsert() throws Exception {
        String createUser = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testInsertuser@gmail.com\",\n" +
                "    \"username\": \"testInsertuser.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        post(path, createUser).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdate() throws Exception {
        String createUser = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testUpdateuser@gmail.com\",\n" +
                "    \"username\": \"testUpdateuser.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        post(path, createUser).andExpect(status().is2xxSuccessful());

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
        String createUser = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testDisableuser@gmail.com\",\n" +
                "    \"username\": \"testDisable.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        post(path, createUser).andExpect(status().is2xxSuccessful());

        String json = "{\n" +
                "    \"removed\": true\n" +
                "}";
        disable(path, 1L, json).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testFindById() throws Exception {
        String createUser = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testFindById@gmail.com\",\n" +
                "    \"username\": \"testFindById.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        post(path, createUser).andExpect(status().is2xxSuccessful());
        getById(path, 1L);
    }
    @Test
    public void testFindAll() throws Exception {
        String createUser = "\n" +
                "{\n" +
                "    \"name\": \"eduardo\",\n" +
                "    \"email\":\"testFindAll@gmail.com\",\n" +
                "    \"username\": \"testFindAll.sm\",\n" +
                "    \"password\": \"123\"\n" +
                "}";
        post(path, createUser).andExpect(status().is2xxSuccessful());
        get(path).andExpect(status().is2xxSuccessful());
    }
}
