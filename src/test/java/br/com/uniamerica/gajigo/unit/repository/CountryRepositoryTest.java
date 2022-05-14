package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.repository.CountryRepository;
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
public class CountryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

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
