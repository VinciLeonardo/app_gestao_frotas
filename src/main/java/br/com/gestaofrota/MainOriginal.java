package br.com.gestaofrota;

import br.com.gestaofrota.database.ConexaoBanco;
import br.com.gestaofrota.excecoes.DadosVeiculoInvalidosException;
import br.com.gestaofrota.model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainOriginal {

    public static void main(String[] args) {
        // 1. Inicializa o schema no SQLite
        ConexaoBanco.inicializarTabela();

        // 2. Inicia a Thread de Monitoramento em Segundo Plano
//        Thread monitorThread = new Thread(new MonitorStatusThread());
//        monitorThread.setDaemon(true); // Permite finalizar a JVM sem travar na thread
//        monitorThread.start();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("==========================================");
        System.out.println("  SISTEMA DE GESTÃO DE FROTA - AULA 01");
        System.out.println("==========================================");

        while (opcao != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Carro");
            System.out.println("2. Cadastrar Moto");
            System.out.println("3. Cadastrar Caminhão");
            System.out.println("4. Cadastrar Onibus");
            System.out.println("5. Cadastrar Triciclo Eletrico");
            System.out.println("6. Cadastrar Ambulância");
            System.out.println("7. Cadastrar Trator");
            System.out.println("9. Listar Frota Completa (Polimorfismo)");
            System.out.println("0. Sair");
            System.out.print("> Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> cadastrarCarro(scanner);
                    case 2 -> cadastrarMoto(scanner);
                    case 3 -> cadastrarCaminhao(scanner);
                    case 4 -> cadastrarOnibus(scanner);
                    case 5 -> cadastrarTricicloEletrico(scanner);
                    case 6 -> cadastrarAmbulancia(scanner);
                    case 7 -> cadastrarTrator(scanner);
                    case 9 -> listarFrota();
                    case 0 -> System.out.println("Encerrando a aplicação...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números inteiros para o menu.");
            }
        }
        scanner.close();
    }

    private static void cadastrarCaminhao(Scanner scanner) {
        try {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Capacidade: ");
            double toneladas = Double.parseDouble(scanner.nextLine());

            Caminhao caminhao = new Caminhao(marca, modelo, ano, toneladas);
            ConexaoBanco.salvarVeiculo(caminhao);
            System.out.println("✅ Carro cadastrado e salvo no banco com sucesso!");

        } catch (DadosVeiculoInvalidosException e) {
            System.err.println("Regra de Negócio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de Banco de Dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida: Certifique-se de digitar números para Ano/Portas.");
        }
    }

    private static void cadastrarCarro(Scanner scanner) {
        try {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Quantidade de Portas: ");
            int portas = Integer.parseInt(scanner.nextLine());

            Carro carro = new Carro(marca, modelo, ano, portas);
            ConexaoBanco.salvarVeiculo(carro);
            System.out.println("✅ Carro cadastrado e salvo no banco com sucesso!");

        } catch (DadosVeiculoInvalidosException e) {
            System.err.println("Regra de Negócio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de Banco de Dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida: Certifique-se de digitar números para Ano/Portas.");
        }
    }

    private static void cadastrarAmbulancia(Scanner scanner) {
        try {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Número UTI: ");
            int d = Integer.parseInt(scanner.nextLine());

            Ambulancia ambulancia = new Ambulancia(marca, modelo, ano, d);
            ConexaoBanco.salvarVeiculo(ambulancia);
            System.out.println("✅ Ambulância cadastrada e salva no banco com sucesso!");

        } catch (DadosVeiculoInvalidosException e) {
            System.err.println("Regra de Negócio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de Banco de Dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida: Certifique-se de digitar números para Ano/Cilindradas.");
        }
    }

    private static void cadastrarTrator(Scanner scanner) {
        try {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Horas de Uso: ");
            int d = Integer.parseInt(scanner.nextLine());

            Trator trator = new Trator(marca, modelo, ano, d);
            ConexaoBanco.salvarVeiculo(trator);
            System.out.println("✅ Trator cadastrado e salvo no banco com sucesso!");

        } catch (DadosVeiculoInvalidosException e) {
            System.err.println("Regra de Negócio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de Banco de Dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida: Certifique-se de digitar números para Ano/Cilindradas.");
        }
    }

    private static void cadastrarTricicloEletrico(Scanner scanner) {
        try {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Autonomia da Bateria em Km: ");
            int d = Integer.parseInt(scanner.nextLine());

            TricicloEletrico tricicloEletrico = new TricicloEletrico(marca, modelo, ano, d);
            ConexaoBanco.salvarVeiculo(tricicloEletrico);
            System.out.println("✅ Triciclo Eletrico cadastrado e salvo no banco com sucesso!");

        } catch (DadosVeiculoInvalidosException e) {
            System.err.println("Regra de Negócio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de Banco de Dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida: Certifique-se de digitar números para Ano/Cilindradas.");
        }
    }

    private static void cadastrarOnibus(Scanner scanner) {
        try {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Capacidade de Passageiros: ");
            int d = Integer.parseInt(scanner.nextLine());

            Onibus onibus = new Onibus(marca, modelo, ano, d);
            ConexaoBanco.salvarVeiculo(onibus);
            System.out.println("✅ Onibus cadastrado e salvo no banco com sucesso!");

        } catch (DadosVeiculoInvalidosException e) {
            System.err.println("Regra de Negócio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de Banco de Dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida: Certifique-se de digitar números para Ano/Cilindradas.");
        }
    }

    private static void cadastrarMoto(Scanner scanner) {
        try {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            System.out.print("Cilindradas: ");
            int cc = Integer.parseInt(scanner.nextLine());

            Moto moto = new Moto(marca, modelo, ano, cc);
            ConexaoBanco.salvarVeiculo(moto);
            System.out.println("✅ Moto cadastrada e salva no banco com sucesso!");

        } catch (DadosVeiculoInvalidosException e) {
            System.err.println("Regra de Negócio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de Banco de Dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada inválida: Certifique-se de digitar números para Ano/Cilindradas.");
        }
    }

    private static void listarFrota() {
        try {
            List<Veiculo> frota = ConexaoBanco.listarVeiculos();
            System.out.println("\n=== FROTA CADASTRADA ===");

            if (frota.isEmpty()) {
                System.out.println("Nenhum veículo encontrado no banco de dados.");
                return;
            }

            // Exibição polimórfica
            for (Veiculo v : frota) {
                System.out.println(v.exibirFichaTecnica());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar a frota: " + e.getMessage());
        }
    }
}
