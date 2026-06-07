# Sistema de Agendamento - Recepção de Fábrica (API RESTful)

Este projeto foi desenvolvido como requisito acadêmico para demonstrar a proficiência na criação de uma API RESTful utilizando o ecossistema Spring Boot. A aplicação gerencia agendamentos de reuniões entre clientes, arquitetos e consultores na fábrica.

## 🎯 Evidências de Atendimento aos Requisitos da Unidade

O código-fonte deste projeto contempla os seguintes aspectos exigidos:
* **Configuração do Projeto:** Estruturado em Spring Boot (v3+) com gerenciamento de dependências via Maven.
* **Mapeamento Objeto-Relacional (JPA/Hibernate):** Uso de `@Entity` para refletir tabelas no banco de dados em memória H2, garantindo persistência sem complexidade de infraestrutura.
* **Implementação de CRUD Completo:** Todos os 4 métodos HTTP básicos implementados (`GET`, `POST`, `PUT`, `DELETE`).
* **Boas Práticas REST:** Retorno padronizado de Códigos de Status HTTP apropriados (`200 OK`, `201 Created`, `204 No Content`, `404 Not Found`).
* **Segurança e Validação:** Implementação do *Bean Validation* (`@Valid`, `@NotBlank`, `@NotNull`) para proteger a integridade dos dados na entrada.
* **Tratamento de Exceções Avançado:** Uso de `@RestControllerAdvice` para capturar erros (ex: ID não encontrado) e devolver um JSON amigável e estruturado no lugar de erros genéricos do servidor.

---

## 📚 Documentação da API (Endpoints)

A API roda na URL base: `http://localhost:8080/api/reunioes`

### 1. Listar todas as reuniões
* **Método:** `GET`
* **Rota:** `/`
* **Parâmetros:** Nenhum.
* **Respostas Possíveis:**
    * `200 OK`: Retorna um *array* (lista) de objetos JSON contendo as reuniões cadastradas.

### 2. Buscar uma reunião específica
* **Método:** `GET`
* **Rota:** `/{id}`
* **Parâmetros de Rota (Path Variable):** * `id` (Long): O identificador único numérico da reunião.
* **Respostas Possíveis:**
    * `200 OK`: Retorna o objeto JSON da reunião encontrada.
    * `404 Not Found`: Retorna um erro customizado caso o ID não exista no banco de dados.

### 3. Agendar uma nova reunião
* **Método:** `POST`
* **Rota:** `/`
* **Parâmetros de Corpo (Request Body):** JSON exigindo os campos `dataReuniao`, `horaReuniao`, `nomeCliente`, `nomeArquiteto` e `nomeConsultor`.
* **Respostas Possíveis:**
    * `201 Created`: Reunião salva com sucesso. Retorna os dados recém-criados junto com o `id` gerado pelo banco.
    * `400 Bad Request`: Falha na validação (ex: nome do cliente enviado em branco ou nulo).

### 4. Atualizar uma reunião existente
* **Método:** `PUT`
* **Rota:** `/{id}`
* **Parâmetros de Rota:** `id` da reunião a ser alterada.
* **Parâmetros de Corpo (Request Body):** JSON com os dados completos atualizados da reunião.
* **Respostas Possíveis:**
    * `200 OK`: Retorna o objeto atualizado com sucesso.
    * `404 Not Found`: O ID fornecido na URL não foi encontrado.
    * `400 Bad Request`: Falha na validação de preenchimento dos campos enviados.

### 5. Cancelar/Excluir uma reunião
* **Método:** `DELETE`
* **Rota:** `/{id}`
* **Parâmetros de Rota:** `id` da reunião a ser removida.
* **Respostas Possíveis:**
    * `204 No Content`: Exclusão realizada com sucesso. Nenhum corpo é retornado.
    * `404 Not Found`: O ID fornecido para deleção não existe no banco.

---

## 🚀 Como testar localmente

1. Execute a classe principal `ApiRecepcaoApplication.java`.
2. A API possui integração com o **Swagger (OpenAPI)**.
3. Com o servidor rodando, abra o navegador e acesse: `http://localhost:8080/swagger-ui.html` para uma interface gráfica de testes completa.