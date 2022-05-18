package br.com.uniamerica.gajigo.unit.mock;

import br.com.uniamerica.gajigo.mock.CountryMock;
import br.com.uniamerica.gajigo.validator.CountryValidator;

public class CountryMockTest extends AbstractMockTest {
    public CountryMockTest() {
        super(new CountryMock(), new CountryValidator());
    }
}
