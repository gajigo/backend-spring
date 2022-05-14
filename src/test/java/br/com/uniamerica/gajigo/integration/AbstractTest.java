package br.com.uniamerica.gajigo.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public abstract class AbstractTest {
    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    private String root = "http://localhost:" + port + "/api/";
    private String resource;
    private String path;

    public AbstractTest(String resource) {
        this.resource = resource;
        this.path = root + resource;
    }

    private ResultActions tryLoading(String path) throws Exception {
        return this.mockMvc.perform(get(path).accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testResourceCollectionLoads() throws Exception {
        tryLoading(path);
    }

    @Test
    public abstract void testPost() throws Exception;

    @Test
    public void testSingleResourceLoads() throws Exception {
        tryLoading(path + "/1");
    }

    @Test
    public abstract void testPut() throws Exception;

    @Test
    public abstract void testPatch() throws Exception;

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete(path + "/1"))
                .andExpect(status().isOk());
    }
}
