package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.Tag;
import br.com.uniamerica.gajigo.repository.TagRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TagRepositoryTest extends AbstractRepositoryTest {
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
