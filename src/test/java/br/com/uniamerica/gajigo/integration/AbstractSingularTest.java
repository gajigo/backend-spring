package br.com.uniamerica.gajigo.integration;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractSingularTest extends AbstractTest {
    private String resource;
    private String path;

    public AbstractSingularTest(String resource) {
        this.resource = resource;
        this.path = root + resource;
    }

    ResultActions postObject(String json) throws Exception {
        return postObject(json, path);
    }

    ResultActions putObject(String json) throws Exception {
        return postObject(json, path);
    }

    ResultActions patchObject(String json) throws Exception {
        return postObject(json, path);
    }

    void simplePostAndDuplicateTest(String json) throws Exception {
        postObject(json).andExpect(status().isCreated());  // First object should be created
        postObject(json).andExpect(status().isConflict()); // Duplicate shouldn't
    }

    @Test
    public void testResourceCollectionLoads() throws Exception {
        tryLoading(path);
    }

    @AfterAll
    public void testSingleResourceLoads() throws Exception {
        tryLoading(path + "/1");
    }

    @AfterAll
    public void testDelete() throws Exception {
        this.mockMvc.perform(delete(path + "/2"))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(get(path + "/2"))
                .andExpect(status().isNotFound());
    }
}
