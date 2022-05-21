package br.com.uniamerica.gajigo.unit;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Profile("test")
@ActiveProfiles("test")
public abstract class AbstractUnitTest {
}
