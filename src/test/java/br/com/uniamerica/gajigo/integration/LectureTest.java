package br.com.uniamerica.gajigo.integration;

import br.com.uniamerica.gajigo.entity.AttendanceMode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class LectureTest extends AbstractIntegrationTest {
    public LectureTest() {
        super("lectures");
    }

    public String createUser() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("name", "name");
        user.put("email", "email@email.com");
        user.put("username", "username");
        user.put("password", "password");

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
    }

    public String createEvent() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode event = mapper.createObjectNode();
        ObjectNode interval = mapper.createObjectNode();
        interval.put("startDate", "2023-07-15T00:00:00");
        interval.put("endDate", "2023-07-25T00:00:00");
        event.put("name", "nome do evento");
        event.put("attendanceMode", "Online");
        event.put("interval", interval);
        event.put("owner", "http://localhost:8080/api/users/1");
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);
    }

    public String createLanguage() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode language = mapper.createObjectNode();

        language.put("name", "portuguese");

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(language);
    }

    public String createLecture() throws JsonProcessingException {
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
        String pathUsers = "http://localhost:8080/api/users";
        String pathEvent = "http://localhost:8080/api/events";
        String pathLecture = "http://localhost:8080/api/lectures";
        String pathLanguage = "http://localhost:8080/api/languages";

        post(pathUsers, createUser()).andExpect(status().isCreated());
        post(pathLanguage, createLanguage()).andExpect(status().isCreated());
        post(pathEvent, createEvent()).andExpect(status().isCreated());
        post(pathLecture, createLecture()).andExpect(status().isCreated());
    }

    @Test
    @DirtiesContext
    public void testFindById() throws Exception {
        String pathUsers = "http://localhost:8080/api/users";
        String pathEvent = "http://localhost:8080/api/events";
        String pathLecture = "http://localhost:8080/api/lectures";
        String pathLanguage = "http://localhost:8080/api/languages";

        post(pathUsers, createUser()).andExpect(status().isCreated());
        post(pathLanguage, createLanguage()).andExpect(status().isCreated());
        post(pathEvent, createEvent()).andExpect(status().isCreated());
        post(pathLecture, createLecture()).andExpect(status().isCreated());

        getById(getPath(), 1L).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void testFindAll() throws Exception {
        String pathUsers = "http://localhost:8080/api/users";
        String pathEvent = "http://localhost:8080/api/events";
        String pathLecture = "http://localhost:8080/api/lectures";
        String pathLanguage = "http://localhost:8080/api/languages";

        post(pathUsers, createUser()).andExpect(status().isCreated());
        post(pathLanguage, createLanguage()).andExpect(status().isCreated());
        post(pathEvent, createEvent()).andExpect(status().isCreated());
        post(pathLecture, createLecture()).andExpect(status().isCreated());

        get(this.getPath()).andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public  void testUpdate() throws Exception {
        String pathUsers = "http://localhost:8080/api/users";
        String pathEvent = "http://localhost:8080/api/events";
        String pathLecture = "http://localhost:8080/api/lectures";
        String pathLanguage = "http://localhost:8080/api/languages";

        post(pathUsers, createUser()).andExpect(status().isCreated());
        post(pathLanguage, createLanguage()).andExpect(status().isCreated());
        post(pathEvent, createEvent()).andExpect(status().isCreated());
        post(pathLecture, createLecture()).andExpect(status().isCreated());

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
        put(this.getPath(), 1L, json).andExpect(status().isOk());

    }

    @Test
    @DirtiesContext
    public void testDelete() throws Exception {
        String pathUsers = "http://localhost:8080/api/users";
        String pathEvent = "http://localhost:8080/api/events";
        String pathLecture = "http://localhost:8080/api/lectures";
        String pathLanguage = "http://localhost:8080/api/languages";

        post(pathUsers, createUser()).andExpect(status().isCreated());
        post(pathLanguage, createLanguage()).andExpect(status().isCreated());
        post(pathEvent, createEvent()).andExpect(status().isCreated());
        post(pathLecture, createLecture()).andExpect(status().isCreated());

        delete(getPath(), 1L).andExpect(status().isNoContent());
    }

    @Test
    @DirtiesContext
    public void testDisable() throws Exception {
        String pathUsers = "http://localhost:8080/api/users";
        String pathEvent = "http://localhost:8080/api/events";
        String pathLecture = "http://localhost:8080/api/lectures";
        String pathLanguage = "http://localhost:8080/api/languages";

        post(pathUsers, createUser()).andExpect(status().isCreated());
        post(pathLanguage, createLanguage()).andExpect(status().isCreated());
        post(pathEvent, createEvent()).andExpect(status().isCreated());
        post(pathLecture, createLecture()).andExpect(status().isCreated());

        disable(getPath(), 1L).andExpect(status().isOk());
    }
}
