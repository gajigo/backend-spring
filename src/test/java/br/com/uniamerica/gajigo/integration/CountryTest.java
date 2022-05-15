package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.mock.CountryMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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

        teardown();
    }

    @Test
    public void testPostEmptyName() throws Exception {
        Country country = new Country("");
        String json = objectMapper.writeValueAsString(country);

        postObject(json).andExpect(status().isBadRequest());

        teardown();
    }

    @Test
    public void testPostMock() throws Exception {
        Country country = new CountryMock().create();
        String json = objectMapper.writeValueAsString(country);

        String url = getUrl(postObject(json).andExpect(status().isCreated()));

        Country newCountry = new Country("Absolutediferentia");
        String putJson = objectMapper.writeValueAsString(country);

        putObject(putJson, url);

        teardown();
    }

    public String validJson(String name, ObjectMapper mapper) throws Exception {
        Country a = new Country(name);
        String country = mapper.writeValueAsString(a);

        return country;
    }

    public String validJson(ObjectMapper mapper) throws Exception {
        return validJson("Happycountryland", mapper);
    }

}
