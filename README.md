# 🎮 Game Manager

> Sistema de gerenciamento de jogos desenvolvido em **Java**, com integração ao **MySQL** através de **JDBC**.

O **Game Manager** é um sistema de gerenciamento de jogos desenvolvido para praticar a integração entre uma aplicação Java e um banco de dados MySQL.

O projeto possui operações de cadastro, consulta, compra, atualização, remoção e controle de estoque, com os dados sendo persistidos diretamente no banco.

---

## 🚀 Funcionalidades

* 🎮 Cadastro de jogos
* 📋 Listagem de jogos cadastrados
* 🔎 Busca de jogo por código
* 🛒 Simulação de compra
* 📦 Controle de estoque
* ✏️ Atualização de dados
* 🗑️ Remoção de jogos
* 🔢 Geração automática do próximo código
* 🕐 Registro de horário das operações
* 💾 Persistência dos dados no MySQL

O sistema possui um menu interativo no terminal para acessar as funcionalidades.

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Utilização                     |
| ---------- | ------------------------------ |
| ☕ Java     | Desenvolvimento da aplicação   |
| 🗄️ MySQL  | Armazenamento dos dados        |
| 🔌 JDBC    | Comunicação entre Java e MySQL |
| 🧩 Eclipse | Ambiente de desenvolvimento    |
| 🌱 Git     | Versionamento                  |
| 🐙 GitHub  | Hospedagem do código           |

---

## 🗃️ Estrutura dos dados

A aplicação trabalha com informações como:

* Código
* Nome
* Preço
* Plataforma
* Estoque

Esses dados são carregados do MySQL e transformados em objetos `Game` dentro da aplicação.

---

## 🔄 Operações do sistema

### Cadastro

Permite cadastrar um ou vários jogos informando nome, preço, plataforma e estoque.

O sistema também gera automaticamente o próximo código disponível.

### 🔎 Busca

É possível pesquisar um jogo através do seu código e visualizar suas principais informações.

### 🛒 Compra

A compra reduz automaticamente uma unidade do estoque e atualiza essa informação no banco de dados.

### ✏️ Atualização

O sistema permite alterar:

* Nome
* Preço
* Plataforma
* Estoque

As alterações são persistidas no MySQL.

### 🗑️ Remoção

Um jogo pode ser removido através do seu código, tanto do banco de dados quanto da lista em memória.

---

## 🧠 O que estou praticando com este projeto

Este projeto faz parte da minha evolução no desenvolvimento **Java Back-end**.

Com ele estou praticando conceitos como:

* Programação Orientada a Objetos
* Classes e objetos
* ArrayList
* Scanner
* JDBC
* PreparedStatement
* ResultSet
* SQL
* CRUD
* Integração Java + MySQL
* Tratamento de `SQLException`
* Persistência de dados
* Versionamento com Git e GitHub

---

## 📈 Evolução

Este projeto representa um passo importante na minha evolução porque foi um dos primeiros projetos em que integrei uma aplicação Java diretamente com um banco de dados.

A ideia é continuar evoluindo o projeto conforme avanço nos estudos de **Java, Back-end, bancos de dados e Spring Boot**.

---

## 👨‍💻 Desenvolvedor

**Paulo Henrique Alves Rodrigues**

📌 Java | Back-end | MySQL | Git | GitHub

🐙 GitHub: [phzdev7](https://github.com/phzdev7)

---

⭐ Projeto desenvolvido para aprendizado e evolução prática em desenvolvimento de software.
