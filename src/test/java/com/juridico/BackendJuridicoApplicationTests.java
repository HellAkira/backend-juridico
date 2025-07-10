package com.juridico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendJuridicoApplicationTests {

    @Test
    void contextLoads() {// Noncompliant - method is empty
        vazioDeProposito();
    }

    public void vazioDeProposito() {
        //Vazio pelo context loads
    }
}
