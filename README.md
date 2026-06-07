# Sistema de Agendamento - Recepção de Fábrica (API RESTful)

Este projeto foi desenvolvido como requisito acadêmico para demonstrar a proficiência na criação de uma API RESTful utilizando o ecossistema Spring Boot. A aplicação gerencia agendamentos de reuniões entre clientes, arquitetos e consultores na fábrica.

## 🏗️ Arquitetura e Estruturação do Projeto

Para garantir manutenibilidade e escalabilidade, o projeto foi refatorado seguindo o padrão de arquitetura em camadas (N-Tier Architecture). O código está organizado nos seguintes pacotes:
* **`controller`**: Camada de interface web. Recebe as requisições HTTP e devolve as respostas, repassando a lógica para os serviços.
* **`service`**: Camada de regras de negócio. Valida condições e intermedia a comunicação entre o controlador e o banco de dados.
* **`repository`**: Camada de persistência (Spring Data JPA) conectada ao banco em memória H2.
* **`model`**: Classes de domínio/entidades (`@Entity`).
* **`exception`**: Camada transversal responsável pela interceptação global e tratamento de erros (`@RestControllerAdvice`).

## 🔄 Versionamento da API

A API implementa a estratégia de **Versionamento por URL** (`/v1/`). Isso garante compatibilidade com clientes existentes caso futuras alterações estruturais (v2, v3) sejam necessárias, seguindo as melhores práticas de evolução de APIs RESTful.

## 🛡️ Segurança, Validação e Tratamento de Exceções

O sistema conta com *Bean Validation* (`@Valid`, `@FutureOrPresent`, `@NotBlank`) e um manipulador global de exceções para fornecer respostas JSON consistentes, amigáveis e estruturadas. O sistema blinda:
* **404 Not Found:** Intercepta buscas, atualizações ou exclusões de IDs inexistentes.
* **400 Bad Request (Regras de Negócio):** Detalha em um mapa quais campos falharam na validação (ex: agendamentos com data no passado).
* **400 Bad Request (Formato Incorreto):** Captura falhas de leitura (`HttpMessageNotReadableException`) e orienta o usuário sobre o formato correto do JSON.

---

## 📚 Documentação da API (Endpoints)

A API roda na URL base versionada: **`http://localhost:8080/api/v1/reunioes`**

### 1. Listar todas as reuniões
* **Método:** `GET`
* **Rota:** `/`
* **Respostas Possíveis:** `200 OK` (Retorna um *array* de reuniões).

### 2. Buscar uma reunião específica
* **Método:** `GET`
* **Rota:** `/{id}`
* **Respostas Possíveis:** `200 OK` (Retorna a reunião) ou `404 Not Found` (Erro estruturado).

### 3. Agendar uma nova reunião
* **Método:** `POST`
* **Rota:** `/`
* **Corpo (JSON):** Exige `dataReuniao`, `horaReuniao`, `nomeCliente`, `nomeArquiteto` e `nomeConsultor`.
* **Respostas Possíveis:** `201 Created` (Reunião salva) ou `400 Bad Request` (Erros de validação).

### 4. Atualizar uma reunião existente
* **Método:** `PUT`
* **Rota:** `/{id}`
* **Respostas Possíveis:** `200 OK` (Sucesso), `404 Not Found` (ID inexistente) ou `400 Bad Request` (Validação falhou).

### 5. Cancelar/Excluir uma reunião
* **Método:** `DELETE`
* **Rota:** `/{id}`
* **Respostas Possíveis:** `204 No Content` (Exclusão com sucesso) ou `404 Not Found` (ID inexistente).

---

## 🚀 Como testar localmente

1. Execute a classe principal `ApiRecepcaoApplication.java`.
2. A API possui integração viva e automatizada com o **Swagger (OpenAPI)**.
3. Com o servidor rodando, abra o navegador e acesse: `http://localhost:8080/swagger-ui.html`
