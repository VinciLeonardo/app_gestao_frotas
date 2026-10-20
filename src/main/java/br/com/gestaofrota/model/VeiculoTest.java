package br.com.gestaofrota.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VeiculoTest {

    @Test
    public void testExibirFichaTecnicaCarro() {
        Carro carro = new Carro(1, "Fiat", "Uno", 2015, 4);
        String ficha = carro.exibirFichaTecnica();

        assertNotNull(ficha);
        assertTrue(ficha.contains("CARRO"));
        assertTrue(ficha.contains("Fiat"));
        assertTrue(ficha.contains("Uno"));
        assertTrue(ficha.contains("2015"));
        assertTrue(ficha.contains("Portas: 4"));
    }
}