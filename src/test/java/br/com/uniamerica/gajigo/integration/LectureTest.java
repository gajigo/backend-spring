package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.AttendanceMode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LectureTest extends AbstractIntegrationTest {
    public LectureTest() {
        super("lectures");
    }

    UserTest userTest = new UserTest();
    EventTest eventTest = new EventTest();
    LanguageTest languageTest = new LanguageTest();
    String userPath = "/api/users";
    String eventPath = "/api/events";
    String languagePath = "/api/languages";

    public String create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode interval = mapper.createObjectNode();
        ObjectNode lecture = mapper.createObjectNode();
        List<String> speakers = new ArrayList<>();
        speakers.add("/1");
        ArrayNode array = mapper.valueToTree(speakers);

        interval.put("startDate", "2023-07-15T00:00:00");
        interval.put("endDate", "2023-07-25T00:00:00");
        lecture.put("name", "a new lecture");
        lecture.put("description", "this is a new lecture");
        lecture.put("event", "/1");
        lecture.put("attendanceMode", AttendanceMode.Online.name());
        lecture.put("speakers", array);
        lecture.put("language", "/1");
        lecture.put("interval", interval);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lecture);

    }

    @Test
    @DirtiesContext
    public void testInsert() throws Exception {
        post(userPath, userTest.create());
        post(languagePath, languageTest.create());
        post(eventPath, eventTest.create());
        post(this.getPath(), this.create()).andExpect(status().isCreated());
    }

    @Test
    @DirtiesContext
    public void testFindById() throws Exception {
        post(userPath, userTest.create());
        post(languagePath, languageTest.create());
        post(eventPath, eventTest.create());
        post(this.getPath(), this.create());

        getById(getPath(), 1L).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testFindAll() throws Exception {
        post(userPath, userTest.create());
        post(languagePath, languageTest.create());
        post(eventPath, eventTest.create());
        post(this.getPath(), this.create());

        get(this.getPath()).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public  void testUpdate() throws Exception {
        post(userPath, userTest.create());
        post(languagePath, languageTest.create());
        post(eventPath, eventTest.create());
        post(this.getPath(), this.create());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode interval = mapper.createObjectNode();
        ObjectNode lecture = mapper.createObjectNode();
        List<String> speakers = new ArrayList<>();
        speakers.add("/1");
        ArrayNode array = mapper.valueToTree(speakers);

        interval.put("startDate", "2023-07-15T00:00:00");
        interval.put("endDate", "2023-07-25T00:00:00");
        lecture.put("active", true);
        lecture.put("name", "a new new lecture");
        lecture.put("description", "this is a new lecture");
        lecture.put("event", "/1");
        lecture.put("attendanceMode", AttendanceMode.Online.name());
        lecture.put("speakers", array);
        lecture.put("language", "/1");
        lecture.put("interval", interval);

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lecture);
        put(getPath(), 1L, json).andExpect(status().isOk());

    }

    @Test
    @DirtiesContext
    public void testDelete() throws Exception {
        post(userPath, userTest.create());
        post(languagePath, languageTest.create());
        post(eventPath, eventTest.create());
        post(this.getPath(), this.create());

        delete(getPath(), 1L).andExpect(status().isNoContent());
    }

    @Test
    @DirtiesContext
    public void testDisable() throws Exception {
        post(userPath, userTest.create());
        post(languagePath, languageTest.create());
        post(eventPath, eventTest.create());
        post(this.getPath(), this.create());

        disable(getPath(), 1L).andExpect(status().isOk());
    }
}
