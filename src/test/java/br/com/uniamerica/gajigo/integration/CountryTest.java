package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.mock.CountryMock;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CountryTest extends AbstractSingularTest {
    public CountryTest() {
        super("countries");
    }

    @Test
    public void testPostNullName() throws Exception {
        Country country = new Country();
        String json = objectMapper.writeValueAsString(country);

        postObject(json).andExpect(status().isBadRequest());
    }

    @Test
    public void testPostEmptyName() throws Exception {
        Country country = new Country("");
        String json = objectMapper.writeValueAsString(country);

        postObject(json).andExpect(status().isBadRequest());
    }

    @Test
    public void testPostMock() throws Exception {
        Country country = new CountryMock().create();
        String json = objectMapper.writeValueAsString(country);

        postObject(json).andExpect(status().isCreated());
    }

    @Override
    public void testPost() throws Exception {
        Country a = new Country("Happyland");
        Country b = new Country("Deleteland");
        String jsonA = objectMapper.writeValueAsString(a);
        String jsonB = objectMapper.writeValueAsString(b);

        simplePostAndDuplicateTest(jsonA);
        postObject(jsonB).andExpect(status().isCreated());
    }

    @Override
    public void testPut() throws Exception {
        Country country = new Country("Putland");
        String json = objectMapper.writeValueAsString(country);

        putObject(json).andExpect(status().is2xxSuccessful());
    }

    @Override
    public void testPatch() throws Exception {
        Country country = new Country("Patchland");
        country.setId(1L);

        String json = objectMapper.writeValueAsString(country);

        patchObject(json).andExpect(status().is2xxSuccessful());
    }
}
