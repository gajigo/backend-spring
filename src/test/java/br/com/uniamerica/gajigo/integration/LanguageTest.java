package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Language;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LanguageTest extends AbstractIntegrationTest {
    public LanguageTest() {
        super("languages");
    }

    @Test
    public void testPostValidObject() throws Exception {
        Language language = new Language("Test Language");
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(language);

        post(path, json).andExpect(status().isCreated());
    }
}
