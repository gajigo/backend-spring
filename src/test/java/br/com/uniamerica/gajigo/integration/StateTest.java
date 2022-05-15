package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.entity.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.json.Json;

import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StateTest extends AbstractSingularTest {
    public StateTest() {
        super("states");
    }

    @Test
    public void testPostNullName() throws Exception {
        validCountry();

        String state = Json.createObjectBuilder()
                .add("country", "/1")
                .toString();

        postObject(state).andExpect(status().isBadRequest());

        teardown("countries");
        teardown();
    }

    @Test
    public void testPostEmptyName() throws Exception {
        String state = validJson("", this.objectMapper);
        postObject(state).andExpect(status().isBadRequest());

        teardown("countries");
        teardown();
    }

    public String validJson(String name, ObjectMapper mapper) throws Exception {
        String country_url = validCountry();

        String state = Json.createObjectBuilder()
                .add("name", name)
                .add("country", country_url)
                .build()
                .toString();

        return state;
    }

    @Override
    public String validJson(ObjectMapper mapper) throws Exception {
        return validJson("Happyland", this.objectMapper);
    }

    private String validCountry() throws Exception {
        return getUrl(postObject(new CountryTest().validJson(this.objectMapper), root + "countries"));
    }
}
