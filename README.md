# 🚗 Sistema de Gestão de Frota

![Versão](https://img.shields.io/badge/Versão-v1.1.0-blue?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-3.45-003B57?style=for-the-badge&logo=sqlite&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit-5.10-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Licença](https://img.shields.io/badge/Licença-MIT-yellow?style=for-the-badge)

**Um sistema console robusto em Java para gerenciamento de frotas de veículos, com persistência em SQLite, separação em camadas (Service Layer, Model, DAO) e testes automatizados com JUnit 5.**

---

## 📋 Índice

- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [Pré-requisitos e Instalação](#-pré-requisitos-e-instalação)
- [Como Executar](#️-como-executar)
- [Arquitetura do Projeto](#️-arquitetura-do-projeto)
- [Banco de Dados](#️-banco-de-dados)
- [Comandos do Menu](#️-comandos-do-menu)
- [Testes Automatizados](#-testes-automatizados)
- [Conceitos Demonstrados](#-conceitos-demonstrados)

---

## ✨ Funcionalidades

- 🚘 **Cadastro de veículos** — suporte a carros, motos, caminhões, ônibus, ambulâncias, tratores e triciclos elétricos com validação de dados.
- 🗑️ **Exclusão de veículos** — remoção de registros do banco de dados com base no identificador único (`ID`) (*Manutenção Perfectiva*).
- 📋 **Listagem polimórfica** — exibe toda a frota usando o método sobrescrito `exibirFichaTecnica()` de cada subclasse.
- 🛡️ **Validação de regras de negócio** — isolada na classe `ValidadorDeVeiculo` para garantir coesão e o Princípio da Responsabilidade Única (SRP).
- 💾 **Persistência automática** — registros salvos e consultados de forma segura no SQLite utilizando `PreparedStatement` para prevenção de SQL Injection.

---

## 🛠️ Tecnologias Utilizadas

| Camada | Tecnologia | Finalidade |
|--------|-----------|-----------|
| Linguagem | Java 21 | Base do projeto (Pattern Matching, Switch Expressions, Text Blocks) |
| Build / Dependências | Maven 3.x | Gerenciamento de pacotes e build |
| Banco de Dados | SQLite 3.45.1 (JDBC) | Persistência local em arquivo (`frota.db`) |
| Testes Unitários | JUnit 5.10.2 + AssertJ | Garantia de qualidade e validação de regras |
| Controle de Versão | Git / GitHub | Rastreabilidade de commits nas branches `main` e `manutencao` |

---

## ⚙️ Pré-requisitos e Instalação

**Requisitos mínimos:**
- JDK 21 ou superior
- Maven 3.x
- Git

**Passo a passo:**

### 1. Clone o repositório e mude para a branch de manutenção
```bash
git clone [https://github.com/VinciLeonardo/app_gestao_frotas.git](https://github.com/VinciLeonardo/app_gestao_frotas.git)
cd app_gestao_frotas
git checkout manutencao

2. Compile o projeto
Bash

mvn clean compile

O banco de dados frota.db será criado automaticamente na raiz do projeto na primeira execução.

▶️ Como Executar
1. Executar a aplicação principal (Main.java)

Como a classe Main unifica o ponto de entrada interativo, execute diretamente pelo Maven:
Bash

mvn exec:java -Dexec.mainClass="br.com.gestaofrota.Main"

Ou gere o JAR e execute:

Bash

mvn clean package
java -cp target/app_gestao_frotas-1.0-SNAPSHOT.jar br.com.gestaofrota.Main

2. Exemplo de saída no terminal

==========================================
  SISTEMA DE GESTÃO DE FROTA - MANUTENÇÃO
==========================================

--- MENU PRINCIPAL ---
1. Cadastrar Carro
2. Cadastrar Moto
...
8. Listar Frota Completa
9. Excluir Veículo por ID
0. Sair
> Escolha uma opção: 8

=== FROTA CADASTRADA ===
[CARRO] Fiat Uno - Ano: 2015 | Portas: 4
[MOTO] Honda Biz - Ano: 2000 | Cilindradas: 50cc

Arquitetura do Projeto

O projeto adota uma arquitetura limpa dividida em pacotes para garantir baixo acoplamento e alta coesão:

app_gestao_frotas/
│
├── src/main/java/br/com/gestaofrota/
│   ├── Main.java                          # 🚀 Ponto de entrada unificado e menu interativo
│   ├── database/
│   │   └── ConexaoBanco.java              # 🗄️ Camada de acesso a dados (DAO / JDBC)
│   ├── service/
│   │   └── FrotaService.java              # ⚙️ Camada de serviço (Desacoplamento e regras)
│   ├── model/
│   │   ├── Veiculo.java                   # 🔷 Classe base abstrata/super
│   │   ├── Carro.java, Moto.java, etc.    # 🚘 Subclasses específicas de veículos
│   │   └── ValidadorDeVeiculo.java        # 🛡️ Validador de domínio (SRP)
│   └── excecoes/
│       └── DadosVeiculoInvalidosException.java # Exceção customizada de negócio
│
├── src/test/java/br/com/gestaofrota/        # 🧪 Testes automatizados (JUnit 5)
├── pom.xml                                # ⚙️ Configuração Maven unificada
└── README.md                              # 📄 Esta documentação

Banco de Dados

Utiliza SQLite com o padrão Single Table Inheritance:
SQL

CREATE TABLE IF NOT EXISTS veiculos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL,                 -- 'CARRO', 'MOTO', etc.
    marca TEXT NOT NULL,
    modelo TEXT NOT NULL,
    ano INTEGER NOT NULL,
    detalhe_especifico INTEGER NOT NULL -- dado específico da subclasse
);

Comandos do Menu
Opção  Ação
1 a 7  Cadastrar diferentes tipos de veículos (Carro, Moto, Caminhão, Ônibus, Triciclo, Ambulância, Trator)
8      Listar toda a frota cadastrada (comportamento polimórfico)
9      Excluir um veículo informando o seu ID correspondente
0      Encerrar a aplicação

Testes Automatizados

O projeto conta com testes unitários desenvolvidos em JUnit 5 localizados na pasta src/test/java. Para rodá-los:
Bash

mvn test

Conceitos Demonstrados
Conceito                Onde é aplicado
Herança & Polimorfismo  Subclasses estendem Veiculo e sobrescrevem exibirFichaTecnica()
Princípio da Responsabilidade Única (SRP) Validação isolada em ValidadorDeVeiculo
Baixo Acoplamento         Introdução da camada FrotaService entre a Main e a ConexaoBanco
Manutenção Perfectiva     Funcionalidade de exclusão por ID (deletarVeiculo)
Testes Automatizados      Cobertura de testes unitários com JUnit 5
Recursos do Java 21       Pattern Matching (instanceof), Switch Expressions e Text Blocks

Licença
Este projeto é disponibilizado sob a Licença MIT para fins educacionais e acadêmicos na UFSC.