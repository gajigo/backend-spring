package br.com.uniamerica.gajigo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public abstract class AbstractTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    protected String root = "http://localhost:" + port + "/api/";

    ResultActions tryLoading(String path) throws Exception {
        return this.mockMvc.perform(get(path).accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    ResultActions postObject(String json, String path) throws Exception {
        return mockMvc.perform(post(path)
                .accept("application/json")
                .contentType("application/json")
                .content(json)
        ).andDo(print());
    }

    ResultActions putObject(String json, String path) throws Exception {
        return mockMvc.perform(put(path)
                .contentType("application/json")
                .content(json)
        ).andDo(print());
    }

    ResultActions patchObject(String json, String path) throws Exception {
        return mockMvc.perform(patch(path)
                .contentType("application/json")
                .content(json)
        ).andDo(print());
    }

}
