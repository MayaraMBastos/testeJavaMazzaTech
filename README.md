# 💰 Sistema de Agendamento de Transferências

## 📌 Sobre o Projeto
O **Sistema de Agendamento de Transferências** é uma aplicação completa (Full-Stack) para gerir agendamentos de transferências bancárias.  
O sistema calcula a taxa de transferência com base em regras de negócio e permite que o utilizador agende, visualize e cancele transações.

---

## 🎯 Principais Funcionalidades
- ✅ API REST para agendamento, listagem e cancelamento de transferências
- ✅ Cálculo de taxa com base em regras de negócio complexas
- ✅ Validação robusta de dados (contas, valores e datas)
- ✅ Interface de utilizador (Frontend) para agendar e visualizar extratos
- ✅ Funcionalidade de extrato para filtrar agendamentos por conta de origem
- ✅ Cancelamento de agendamentos existentes
- ✅ Frontend reativo que se atualiza automaticamente com os dados do backend
- ✅ Tratamento de erros tanto no frontend quanto no backend

---

## 🏗️ Arquitetura
A aplicação é dividida em duas partes principais:

- **Backend**: Desenvolvido com **Java** e **Spring Boot**,estrutura MVC, persistência de dados (**H2 Database**), validações e testes unitários.
- **Frontend**: Desenvolvido com **Vue.js**, consumindo a API REST para agendar e consultar transferências.

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 11** – Linguagem de programação
- **Spring Boot 2.7.5** – Framework principal
- **Spring Data JPA** – Acesso ao banco de dados (H2)
- **Spring Web** – Criação da API REST
- **H2 Database** – Banco de dados em memória
- **Maven** – Gerenciamento de dependências
- **Swagger** – Documentação da API


### Frontend
- **Vue.js 2.6.14** – Framework principal
- **HTML/CSS** – Estrutura e estilização
- **Bootstrap** – Estilização
- **Axios** – Comunicação com a API REST

---

## 📁 Estrutura do Projeto
A estrutura de ficheiros está organizada para separar backend e frontend:

```
.
├── backendTesteMazzatech/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── maymb/
│   │   │   │       ├── Controller/
│   │   │   │       ├── DTOs/
│   │   │   │       ├── Model/
│   │   │   │       ├── Repository/
│   │   │   │       └── Service/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/
│   │           └── maymb/
│   │               ├── Controller/
│   │               │   └── AgendamentoControllerTest.java
│   │               └── Service/
│   │                   └── AgendamentoServiceTest.java
│   └── pom.xml
│
└── frontend/
    ├── src/
    │   ├── App.vue
    │   └── components/
    │       ├── AgendamentoFormulario.vue
    │       └── ExtratoTabela.vue
    └── package.json
```

---

## 🚀 Como Executar o Projeto

### 🔹 Pré-requisitos
Certifique-se de ter instalado:
- **Java 11** ou superior
- **Maven 3.6** ou superior
- **Node.js e npm**

### 🔹 Backend
```bash
cd backendTesteMazzatech
mvn clean package
java -jar target/backendTesteMazzatech-1.0-SNAPSHOT.jar
```

### 🔹 Frontend
```bash
cd frontend
npm install
npm run serve
```

### 🔹 Verificando a Aplicação
Após iniciar backend e frontend, acesse:  
👉 [http://localhost:8081](http://localhost:8081)

---

## 📖 Documentação da API

### 1. Agendar uma Nova Transferência
Acesse SWAGGER EM [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
**POST /agendamentos**

Request Body:
```json
{
  "contaOrigem": "1234567890",
  "contaDestino": "6789012345",
  "valorTransferencia": 1500.00,
  "dataTransferencia": "2025-09-01"
}
```

---

### 2. Listar Agendamentos por Conta de Origem
**GET /agendamentos?contaOrigem={conta}**

Exemplo:
```
http://localhost:8080/agendamentos?contaOrigem=1234567890
```

---

### 3. Cancelar um Agendamento
**DELETE /agendamentos/{id}**

Exemplo:
```
http://localhost:8080/agendamentos/1
```

---

## 🐛 Solução de Problemas

### Problema: `Port 8080 was already in use`
A porta do backend já está em uso.  
**Solução:** Altere no arquivo `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Problema: `SyntaxError: Cannot use import statement outside a module`
O servidor Vue.js não está em execução.  
**Solução:** Execute:
```bash
npm run serve
```
na pasta `frontend/`.

---

## 🤝 Contribuição
Contribuições são bem-vindas!  
Abra um **Pull Request** para sugerir melhorias.

---

## 📜 Licença
Este projeto está licenciado sob a **MIT License**.

---

## 👩‍💻 Autora

**Mayara Martinello Bastos**  
🌐 [linkedin.com/in/mayara-martinello-bastos](https://www.linkedin.com/in/mayara-martinello-bastos)  
📫 maybastos2021@gmail.com
