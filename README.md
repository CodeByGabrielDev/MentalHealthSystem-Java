# 🧠 VallentClinic - Sistema de Gestão para Clínica de Psicologia

> ⚠️ **Projeto em andamento** — Esta aplicação está em constante desenvolvimento e evolução. Algumas funcionalidades ainda estão sendo implementadas e validadas.

---

## 📌 Sobre o projeto

O **VallentClinic** é um sistema desenvolvido em **Java puro com JDBC** para auxiliar na gestão de clínicas de psicologia. O sistema foi estruturado com foco na organização e integridade de dados, contemplando pacientes, psicólogos, convênios, sessões e pagamentos.

---

## 🚀 Tecnologias utilizadas

- ✅ **Java 17**
- ✅ **MySQL 8**
- ✅ **JDBC (Java Database Connectivity)**
- ✅ **Maven**
- ✅ **Procedures (Stored Procedures)**
- ✅ **POO (Programação Orientada a Objetos)**
- ✅ **Validações manuais com classes `Validator`**

---

## ⚙️ Funcionalidades

- 👥 Cadastro e consulta de **pacientes** e **psicólogos**
- 📑 Registro e busca de **convênios**
- 📆 Controle de **agenda** e **sessões**
- 💳 Lançamento de **pagamentos** com formas e status distintos
- 🔍 Execução de **procedures SQL** para consultas otimizadas
- 🧪 Validação completa de entradas: nomes, datas, CPF, CNPJ, e mais
- 📁 Separação por **camadas**: `Entities`, `DAO`, `Controller`, `Validators`, `Enums`

---

## 🗂 Estrutura do projeto

vallentclinic/
├── src/
│ ├── Entities/ # Classes modelo (Paciente, Psicólogo, Sessão, etc.)
│ ├── DAO/ # Acesso ao banco de dados (CRUD / procedures)
│ ├── Controllers/ # Lógica de negócio e interface com DAO
│ ├── Validators/ # Validações personalizadas para cada entidade
│ ├── Enums/ # Tipos fixos (status, formas de pagamento)
│ └── MySQL/ # Conexão com o banco de dados
├── pom.xml # Gerenciador Maven
└── README.md

yaml
Copiar
Editar

---

## 🛠 Como executar localmente

1. **Clone o repositório:**

```bash
git clone https://github.com/CodeByGabrielDev/vallentclinic.git
Configure o banco de dados MySQL:

Crie o schema e execute os scripts de tabelas e procedures

Ajuste a classe MySQL.java com suas credenciais:

java
Copiar
Editar
String url = "jdbc:mysql://localhost:3306/seubanco";
String user = "root";
String password = "sua_senha";
Execute o projeto via IDE (Eclipse, IntelliJ, etc.)



🎯 Objetivos do projeto
Consolidar conhecimento em Java Orientado a Objetos

Praticar estruturação de sistemas em camadas organizadas

Aplicar lógica de negócio com validações reais

Integrar banco de dados com stored procedures

Desenvolver um sistema funcional sem frameworks, com foco em lógica pura

📌 Observação final
Este projeto está sendo desenvolvido sem frameworks como Spring ou Hibernate intencionalmente, para aprofundar o domínio da linguagem Java, JDBC e a lógica aplicada em cada etapa do processo de desenvolvimento.
