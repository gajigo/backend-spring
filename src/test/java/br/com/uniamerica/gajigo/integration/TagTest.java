package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Tag;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TagTest extends AbstractIntegrationTest {
    public TagTest() {
        super("tags");
    }

    @Test
    public void testPostValidObject() throws Exception {
        Tag tag = new Tag("Test Tag", "testing");
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(tag);

        post(this.getPath(), json).andExpect(status().isCreated());
    }
}
