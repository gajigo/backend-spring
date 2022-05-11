package br.com.uniamerica.gajigo.mock;

import br.com.uniamerica.gajigo.entity.Country;

public class CountryMock extends AbstractMock<Country> {
    public Country create() {
        Country country = new Country();

        country.setName(generateName());

        return country;
    }

    private String generateName() {
        return faker.country().name();
    }
}
