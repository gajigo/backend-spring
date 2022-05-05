package br.com.uniamerica.gajigo.mock;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryMock extends AbstractMock<Country> {
    private CountryRepository countryRepository;

    @Autowired
    public CountryMock(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country create() {
        Country country = new Country();

        country.setName(generateName());

        return country;
    }

    private String generateName() {
        return faker.country().name();
    }
}
