package br.com.uniamerica.gajigo.unit.mock;

import br.com.uniamerica.gajigo.mock.LanguageMock;
import br.com.uniamerica.gajigo.validator.LanguageValidator;

public class LanguageMockTest extends AbstractMockTest {
    public LanguageMockTest() {
        super(new LanguageMock(), new LanguageValidator());
    }
}
