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
    public void insertUser() {
            User user = new User("username", "password", "name", "fullstack dev", "email@email.com", true);
            repository.save(user);
            Integer countUser = repository.findAll().size();
            assertEquals(1, countUser);
        }
    @Test
    public void userSavedWithUsernamePassword() {
        User user = new User("username", "password", "name", "fullstack dev", "email@email.com", true);
        repository.save(user);
        Integer countUser = repository.findAll().size();
        assertEquals(1, countUser);
        User users1 = repository.findUserByUsernameAndPasswordAndEmail("username", "password", "email@email.com" );
        assertNotNull(users1);
        assertEquals(user, users1);

    }


}
