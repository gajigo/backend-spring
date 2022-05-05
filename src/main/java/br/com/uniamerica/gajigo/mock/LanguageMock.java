package br.com.uniamerica.gajigo.mock;

import br.com.uniamerica.gajigo.entity.Language;

public class LanguageMock extends AbstractMock<Language> {
    public Language create() {
        Language language = new Language();

        language.setName(generateName());
        return language;
    }

    private String generateName() {
        return faker.nation().language();
    }
}
