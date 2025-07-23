🧠 VallentClinic - Sistema de Gestão para Clínica de Psicologia
⚠️ Projeto em andamento — Esta aplicação está em constante desenvolvimento e evolução. Algumas funcionalidades ainda estão sendo implementadas e validadas.

📌 Sobre o projeto
O VallentClinic é um sistema robusto desenvolvido em Java puro com JDBC, com o objetivo de atender às necessidades reais de uma clínica de psicologia. Ele contempla toda a estrutura necessária para o gerenciamento de pacientes, psicólogos, convênios, sessões e movimentações financeiras (entradas e saídas).

🧩 O projeto foi arquitetado com base em regras de negócio reais, com controle de dados, consistência, relacionamentos entre entidades e uso extensivo de SQL avançado com procedures e triggers.

🚀 Tecnologias utilizadas
✅ Java 17

✅ MySQL 8

✅ JDBC (Java Database Connectivity)

✅ Maven

✅ Procedures (Stored Procedures)

✅ Triggers

✅ POO (Programação Orientada a Objetos)

✅ Validações manuais com classes Validator

⚙️ Funcionalidades
👥 Cadastro, consulta e listagem de pacientes e psicólogos

📑 Registro e gerenciamento de convênios

📆 Controle de sessões com vínculo entre psicólogo e paciente

💳 Lançamento de movimentações financeiras (entradas e saídas)

🔒 Geração de relatórios financeiros por tipo e período (via procedures SQL)

🔎 Validações completas: CPF, CNPJ, nomes, e datas

🧩 Separação clara por camadas:

entities

controller

dao

validators

enums

🗂️ Código organizado, com grande volume e lógica de negócio estruturada

🛠️ Como executar localmente
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/CodeByGabrielDev/vallentclinic.git
Configure o banco de dados MySQL:

Crie o schema e execute os scripts de criação de tabelas e procedures.

Atualize a classe MySQL.java com suas credenciais:

java
Copiar
Editar
String url = "jdbc:mysql://localhost:3306/seubanco";
String user = "root";
String password = "sua_senha";
Execute o projeto via sua IDE preferida (Eclipse, IntelliJ etc.)

🎯 Objetivos do projeto
Consolidar conhecimento em Java Orientado a Objetos

Praticar estruturação de sistemas reais com regras de negócio

Trabalhar com persistência de dados usando JDBC e SQL

Aplicar lógica de validação manual e tratamento de exceções

Implementar estrutura completa de DAO/Controller/Entities

Criar um sistema funcional e organizado sem frameworks, com foco em lógica pura

📌 Observação final
Este projeto foi idealizado para simular o desenvolvimento de um sistema real, com forte foco em lógica, organização de código e aplicação prática de conceitos de POO, banco de dados, SQL avançado e engenharia de software.
É um passo importante na minha trajetória rumo à especialização em desenvolvimento backend com Java.
