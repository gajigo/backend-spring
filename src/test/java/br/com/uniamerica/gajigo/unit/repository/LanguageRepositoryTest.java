package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.Language;
import br.com.uniamerica.gajigo.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private LanguageRepository repository;

    @Test
    public void testFindName() {
        // Given
        Language a = new Language("Damokalad");
        this.repository.save(a);

        // When
        Language findA = repository.findFirstByName("Damokalad");
        Language findNull = repository.findFirstByName("Nullian");

        // Then
        assertNull(findNull);
        assertNotNull(findA);

        a.setId(findA.getId());

        assertEquals(a, findA);
    }
}
