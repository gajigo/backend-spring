package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.repository.CountryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        assert findNull == null;
        assert findA != null;

        a.setId(findA.getId());

        assert a.equals(findA);
    }
}
