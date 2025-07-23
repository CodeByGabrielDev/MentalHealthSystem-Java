🧠 VallentClinic – Sistema de Gestão para Clínica de Psicologia
⚠️ Projeto em andamento – Este sistema está em constante desenvolvimento. Algumas funcionalidades ainda estão sendo implementadas e validadas.

📌 Sobre o Projeto
O VallentClinic é um sistema desenvolvido em Java puro com JDBC, com o objetivo de oferecer uma solução robusta para a gestão de clínicas de psicologia. A aplicação foi construída com foco na organização em camadas, integridade dos dados e modelagem realista das regras de negócio.

🎯 Objetivos do Projeto
Consolidar conhecimentos em Java com Programação Orientada a Objetos (POO)

Praticar estruturação em camadas: Entities, DAO, Controller, Validators, Enums

Aplicar lógica de negócio com validações reais (CPF, CNPJ, datas, nomes, etc.)

Integrar banco de dados utilizando Stored Procedures e Triggers

Desenvolver um sistema funcional sem uso de frameworks (como Spring ou Hibernate), com foco na lógica pura

⚙️ Funcionalidades
👤 Cadastro e consulta de pacientes e psicólogos

📑 Gerenciamento de convênios

📅 Controle de agenda e sessões clínicas

💳 Registro de pagamentos com diferentes formas e status

🧾 Geração e controle de notas fiscais

🔐 Validações completas de entrada via classes Validator

🧠 Execução de procedures SQL e uso de triggers no banco

🧩 Arquitetura em camadas com clara separação de responsabilidades

🚀 Tecnologias Utilizadas
✅ Java 17

✅ JDBC (Java Database Connectivity)

✅ MySQL 8

✅ Maven

✅ Stored Procedures

✅ Triggers

✅ POO (Programação Orientada a Objetos)

✅ Validações manuais com Validator

🛠️ Como Executar Localmente
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/CodeByGabrielDev/vallentclinic.git
Configure o banco de dados MySQL:

Crie o schema (vallentclinic) e execute os scripts SQL com as tabelas e procedures.

Atualize a classe MySQL.java com suas credenciais:

java
Copiar
Editar
String url = "jdbc:mysql://localhost:3306/seubanco";
String user = "root";
String password = "sua_senha";
Execute o projeto pela sua IDE preferida (Eclipse, IntelliJ, NetBeans...)

🧾 Observações Finais
Este projeto foi desenvolvido sem o uso de frameworks como Spring ou Hibernate de forma proposital, com o intuito de reforçar o domínio da linguagem Java, entender em profundidade o JDBC e exercitar lógica aplicada a cenários reais de negócio.

