package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class CountryRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private CountryRepository repository;

    @Test
    public void testFindName() {
        // Given
        Country a = new Country("Arrrgentina");
        entityManager.persistAndFlush(a);

        // When
        Country findA = repository.findFirstByName("Arrrgentina");
        Country findNull = repository.findFirstByName("Nulland");

        // Then
        assertNull(findNull);
        assertNotNull(findA);

        a.setId(findA.getId());

        assertEquals(a, findA);
    }
}
