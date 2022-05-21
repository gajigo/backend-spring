package br.com.uniamerica.gajigo;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SelectPackages({
        "br.com.uniamerica.gajigo.unit"
})
class GajigoApplicationTests {

    @Test
    void contextLoads() {
    }

}
