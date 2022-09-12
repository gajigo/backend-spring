package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CountryTest extends AbstractIntegrationTest {
    public CountryTest() {
        super("countries");
    }

    @Test
    public void testPostValidObject() throws Exception {
        Country country = new Country("Test Country");
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(country);

        post(this.getPath(), json).andExpect(status().isCreated());
    }
}
