package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.Language;
import br.com.uniamerica.gajigo.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LanguageRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private LanguageRepository repository;

    @Test
    public void testFindName() {
        // Given
        Language a = new Language("Damokalad");
        entityManager.persistAndFlush(a);

        // When
        Language findA = repository.findFirstByName("Damokalad");
        Language findNull = repository.findFirstByName("Nullian");

        // Then
        assert findNull == null;
        assert findA != null;

        a.setId(findA.getId());

        assert a.equals(findA);
    }
}
