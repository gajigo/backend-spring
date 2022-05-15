package br.com.uniamerica.gajigo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.ResultActions;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractSingularTest extends AbstractTest {
    private String resource;
    private String path;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AbstractSingularTest(String resource) {
        this.resource = resource;
        this.path = root + resource;
    }

    public void teardown(String resource) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, resource);
    }

    public void teardown() throws Exception {
        teardown(resource);
    }

    ResultActions postObject(String json) throws Exception {
        return postObject(json, path);
    }

    ResultActions putObject(String json) throws Exception {
        return putObject(json, path);
    }

    ResultActions patchObject(String json) throws Exception {
        return patchObject(json, path);
    }

    String simplePostAndDuplicateTest(String json) throws Exception {
        String url = getUrl(postObject(json).andExpect(status().isCreated()));  // First object should be created
        postObject(json).andExpect(status().isConflict()); // Duplicate shouldn't

        return url;
    }

    String getUrl(ResultActions result) throws Exception {
        String response = result.andReturn().getResponse().getContentAsString();
        if (response == null) return null;

        InputStream stream = new ByteArrayInputStream(response.getBytes());
        JsonReader reader = Json.createReader(stream);

        try {
            String self = reader.readObject()
                    .getJsonObject("_links")
                    .getJsonObject("self")
                    .getString("href");

            return self;
        } catch (Exception ignored) {}

        return null;
    }

    String getUri(String url) {
        return url.substring(url.indexOf("/api"));
    }

    public abstract String validJson(String discriminator, ObjectMapper mapper) throws Exception;

    public abstract String validJson(ObjectMapper mapper) throws Exception;

    @Test
    public void testHappyPath() throws Exception {
        // Resource collection should load
        tryLoading(path);

        // Object should be created and disallow duplicates
        String url = simplePostAndDuplicateTest(validJson(this.objectMapper));

        // Individual resource should load
        tryLoading(getUri(url));

        // Individual object should be deleted
        this.mockMvc.perform(delete(url))
                .andExpect(status().isNoContent());

        // Individual object page should return 404 after deletion
        this.mockMvc.perform(get(url))
                .andExpect(status().isNotFound());

        teardown();
    }
}
