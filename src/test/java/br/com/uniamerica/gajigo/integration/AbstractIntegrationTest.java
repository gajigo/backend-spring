package br.com.uniamerica.gajigo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Profile("test")
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = "ADMIN")
public abstract class AbstractIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private int port = 8080;
    private String root = "http://localhost:" + port + "/api/";

    private String resource;

    @Getter
    private String path;

    public AbstractIntegrationTest(String resource) {
        this.resource = resource;
        this.path = root + resource;
    }

    @Test
    public void testResource() throws Exception {
        get(path).andExpect(status().isOk());
    }

    @Test
    public void testEmptyPost() throws Exception {
        post(path, "{}").andExpect(status().isBadRequest());
    }

    ResultActions get(String path) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.get(path).accept("application/json"))
                .andDo(print());
    }

    ResultActions getById(String path, Long id) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.get(path + "/" + id).accept("application/json"))
                .andDo(print());
    }

    ResultActions post(String path, String json) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType("application/json")
                        .content(json))
                .andDo(print());
    }

    ResultActions put(String path, Long id, String json) throws Exception {
        return this.mockMvc
                .perform(MockMvcRequestBuilders.put(path + "/" + id)
                        .contentType("application/json")
                        .content(json))
                .andDo(print());
    }

    ResultActions delete(String path, Long id) throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.delete(path + "/" + id)
                        .accept("application/json"))
                        .andDo(print());
    }

    ResultActions disable(String path, Long id) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode object = mapper.createObjectNode();
        object.put("active", false);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

        return this.mockMvc.perform(MockMvcRequestBuilders.patch(path + "/" + id)
                        .contentType("application/json")
                        .content(json))
                .andDo(print());
    }
}
