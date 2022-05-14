package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

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
        assert findNull == null;
        assert findA != null;

        a.setId(findA.getId());

        assert a.equals(findA);
    }
}
