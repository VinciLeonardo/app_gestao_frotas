package br.com.gestaofrota.model;

import br.com.gestaofrota.excecoes.DadosVeiculoInvalidosException;

public class ValidadorDeVeiculo {

    public static void validar(Veiculo veiculo) throws DadosVeiculoInvalidosException {
        if (veiculo.getAno() < 1900 || veiculo.getAno() > 2027) {
            throw new DadosVeiculoInvalidosException("Ano do veículo é inválido: " + veiculo.getAno());
        }

        if (veiculo.getMarca() == null || veiculo.getMarca().isBlank()) {
            throw new DadosVeiculoInvalidosException("A marca deve ser informada!");
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().isBlank()) {
            throw new DadosVeiculoInvalidosException("O modelo deve ser informado!");
        }

        if (veiculo instanceof Carro c) {
            if (c.getQuantidadePortas() < 2 || c.getQuantidadePortas() > 6) {
                throw new DadosVeiculoInvalidosException("Quantidade de portas deve ser de 2 a 6!");
            }
        }

        if (veiculo instanceof Moto m) {
            if (m.getCilindradas() < 50 || m.getCilindradas() > 2500) {
                throw new DadosVeiculoInvalidosException("Quantidade de cilindradas deve ser entre 50 e 2500!");
            }
        }
    }
}