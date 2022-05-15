package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.entity.State;
import org.junit.Test;

import javax.json.Json;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StateTest extends AbstractSingularTest {
    public StateTest() {
        super("states");
    }

    @Test
    public void testPostNullName() throws Exception {
        String country = objectMapper.writeValueAsString(new Country("Nullnameisland"));
        postObject(country, root + "countries");

        String state = Json.createObjectBuilder()
                .add("country", "/1")
                .toString();

        postObject(state).andExpect(status().isBadRequest());
    }

    @Test
    public void testPostEmptyName() throws Exception {
        String country = objectMapper.writeValueAsString(new Country("Emptynameisland"));

        postObject(country, root + "countries");
        String state = Json.createObjectBuilder()
                .add("name", "")
                .add("country", "/1")
                .toString();

        postObject(state).andExpect(status().isBadRequest());
    }

    @Override
    public void testPost() throws Exception {
        String country = objectMapper.writeValueAsString(new Country("Postateland"));
        postObject(country, root + "countries");

        String state = Json.createObjectBuilder()
                .add("name", "Happyland")
                .add("country", "/1")
                .build()
                .toString();

        simplePostAndDuplicateTest(state);
    }

    @Override
    public void testPut() throws Exception {
        String country = objectMapper.writeValueAsString(new Country("Putslandia"));
        postObject(country, root + "countries");

        String state = Json.createObjectBuilder()
                .add("name", "Putsland")
                .add("country", "/1")
                .build()
                .toString();

        putObject(state).andExpect(status().isCreated());
    }

    @Override
    public void testPatch() throws Exception {
        String country = objectMapper.writeValueAsString(new Country("Patcheslandia"));
        postObject(country, root + "countries");

        String state = Json.createObjectBuilder()
                .add("name", "Patchesland")
                .add("country", "/1")
                .build()
                .toString();


        patchObject(state).andExpect(status().isCreated());
    }
}
