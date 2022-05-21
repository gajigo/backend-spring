package br.com.uniamerica.gajigo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Profile("test")
@ActiveProfiles("test")
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private int port = 8080;
    private String root = "http://localhost:" + port + "/api/";

    private String resource;
    private String path;

    public AbstractIntegrationTest(String resource) {
        this.resource = resource;
        this.path = root + resource;
    }

    @Test
    public void testResource() throws Exception {
        tryLoading(path).andExpect(status().isOk());
    }

    ResultActions tryLoading(String path) throws Exception {
        return this.mockMvc.perform(get(path).accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }
}
