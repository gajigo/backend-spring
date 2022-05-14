package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.Tag;
import br.com.uniamerica.gajigo.repository.TagRepository;
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
public class TagRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TagRepository repository;

    @Test
    public void testFindName() {
        // Given
        Tag a = new Tag("UnitTestingTag", "Test test test");
        entityManager.persistAndFlush(a);

        // When
        Tag findA = repository.findFirstByName("UnitTestingTag");
        Tag findNull = repository.findFirstByName("NullTag");

        // Then
        assert findNull == null;
        assert findA != null;

        a.setId(findA.getId());

        assert a.equals(findA);
    }
}
