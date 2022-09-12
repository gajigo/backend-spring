package br.com.uniamerica.gajigo.unit;

import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@Profile("test")
@ActiveProfiles("test")
public abstract class AbstractUnitTest {
}
