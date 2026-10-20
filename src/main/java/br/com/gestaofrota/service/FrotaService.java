package br.com.gestaofrota.service;

import br.com.gestaofrota.database.ConexaoBanco;
import br.com.gestaofrota.excecoes.DadosVeiculoInvalidosException;
import br.com.gestaofrota.model.ValidadorDeVeiculo;
import br.com.gestaofrota.model.Veiculo;

import java.sql.SQLException;
import java.util.List;

public class FrotaService {

    public void cadastrarVeiculo(Veiculo veiculo) throws SQLException, DadosVeiculoInvalidosException {
        ValidadorDeVeiculo.validar(veiculo);
        ConexaoBanco.salvarVeiculo(veiculo);
    }

    public List<Veiculo> listarFrota() throws SQLException {
        return ConexaoBanco.listarVeiculos();
    }

    public boolean excluirVeiculo(int id) throws SQLException {
        return ConexaoBanco.deletarVeiculo(id);
    }
}