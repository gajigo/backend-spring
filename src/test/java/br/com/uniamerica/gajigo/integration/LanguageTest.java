package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Language;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

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

        post(this.getPath(), json).andExpect(status().isCreated());
    }

    public String create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode language = mapper.createObjectNode();

        language.put("name", "portuguese");

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(language);
    }
}
