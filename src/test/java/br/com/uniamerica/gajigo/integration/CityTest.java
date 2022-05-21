package br.com.uniamerica.gajigo.integration;

import org.junit.Test;

import javax.json.Json;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityTest extends AbstractSingularTest {
    public CityTest() {
        super("cities");
    }

    @Test
    public void testPostNullName() throws Exception {
        validState();

        String city = Json.createObjectBuilder()
                .add("state", "/1")
                .toString();

        postObject(city).andExpect(status().isBadRequest());

        teardown("states");
        teardown();
    }

    @Test
    public void testPostEmptyName() throws Exception {
        String city = validJson("");
        postObject(city).andExpect(status().isBadRequest());

        teardown("states");
        teardown();
    }

    public String validJson(String name) throws Exception {
        String state_url = validState();

        String city = Json.createObjectBuilder()
                .add("name", name)
                .add("state", state_url)
                .build()
                .toString();

        return city;
    }

    @Override
    public String validJson() throws Exception {
        return validJson("Happyland");
    }

    private String validState() throws Exception {
        return getUrl(postObject(new StateTest().validJson(), root + "states"));
    }
}
