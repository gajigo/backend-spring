package br.com.uniamerica.gajigo.unit.repository;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testFindUsername() {
        // Given
        User a = new User("UnitTestingUser", "asdfsafpass",
                          "Unit Testing User", "User for Testing",
                          "fake@test.com", true);
        entityManager.persistAndFlush(a);

        // When
        User findA = repository.findFirstByUsername("UnitTestingUser");
        User findNull = repository.findFirstByUsername("NullUser");

        // Then
        assertNull(findNull);
        assertNotNull(findA);

        a.setId(findA.getId());

        assertEquals(a, findA);
    }

    @Test
    public void testInsertUser() {
        User user = new User("eduardo123", "minhasenha123", "eduardo de souza magalhaes");
        user.setEmail("eduardo@gmail.com");
        repository.save(user);

        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testUpdateUser() {
        User user = new User("eduardo123", "minhasenha123", "eduardo de souza magalhaes");
        user.setEmail("eduardo@gmail.com");
        user = this.repository.save(user);

        assertEquals(user.getUsername(), "eduardo123");

        user.setUsername("teste123");
        user = this.repository.save(user);

        assertEquals(user.getUsername(), "teste123");
    }
}
