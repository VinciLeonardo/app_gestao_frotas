package br.com.gestaofrota.database;

import br.com.gestaofrota.excecoes.DadosVeiculoInvalidosException;
import br.com.gestaofrota.model.Carro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConexaoBancoTest {

    @Test
    public void testValidacaoAnoInvalido() {
        Carro carroInvalido = new Carro("Fiat", "Palio", 1880, 4);

        Exception exception = assertThrows(DadosVeiculoInvalidosException.class, () -> {
            ConexaoBanco.salvarVeiculo(carroInvalido);
        });

        assertTrue(exception.getMessage().contains("Ano do veículo é inválido"));
    }
}