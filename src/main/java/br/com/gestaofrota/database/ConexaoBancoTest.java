package br.com.gestaofrota.database;

import br.com.gestaofrota.excecoes.DadosVeiculoInvalidosException;
import br.com.gestaofrota.model.Carro;
import br.com.gestaofrota.model.ValidadorDeVeiculo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConexaoBancoTest {

    @Test
    public void testValidacaoAnoInvalido() {
        Carro carroInvalido = new Carro("Fiat", "Palio", 1880, 4);

        // Testa a validação utilizando o componente isolado de SRP
        Exception exception = assertThrows(DadosVeiculoInvalidosException.class, () -> {
            ValidadorDeVeiculo.validar(carroInvalido);
        });

        assertTrue(exception.getMessage().contains("Ano do veículo é inválido"));
    }
}