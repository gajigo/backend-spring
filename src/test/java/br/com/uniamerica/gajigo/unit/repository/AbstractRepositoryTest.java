package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractRepositoryTest extends AbstractUnitTest {
}
