# Sistema de Agendamento - Recepção de Fábrica (API RESTful)

Este projeto foi desenvolvido como requisito acadêmico para demonstrar a proficiência na criação de uma API RESTful utilizando o ecossistema Spring Boot. A aplicação gerencia agendamentos de reuniões entre clientes, arquitetos e consultores na fábrica.

## 🎯 Evidências de Atendimento aos Requisitos (Unidades 1 e 2)

O código-fonte deste projeto contempla os seguintes aspectos exigidos:
* **Configuração e JPA:** Estruturado em Spring Boot com mapeamento objeto-relacional (`@Entity`) para banco em memória H2.
* **Controladores RESTful e Mapeamento:** Uso de `@RestController` e `@RequestMapping` (`/api/reunioes`) para roteamento correto.
* **Implementação de CRUD Completo:** Todos os 4 métodos HTTP básicos implementados (`GET`, `POST`, `PUT`, `DELETE`).
* **Segurança e Validação Adicional:** Implementação do *Bean Validation* (`@Valid`, `@NotBlank`, `@NotNull`) e regras de negócio específicas, como `@FutureOrPresent` para impedir agendamentos no passado.
* **Tratamento Avançado de Exceções:** Uso de `@RestControllerAdvice` para capturar erros e devolver respostas JSON estruturadas, amigáveis e personalizadas. O sistema blinda:
  * **404 Not Found:** IDs não encontrados no banco.
  * **400 Bad Request (Validação):** Mapeia os campos exatos que feriram as regras de negócio.
  * **400 Bad Request (Formato):** Intercepta falhas de leitura (ex: envio de letras em campos de data).
* **Documentação Viva:** Integração com Swagger/OpenAPI para testes de rotas.

---

## 📚 Documentação da API (Endpoints)

A API roda na URL base: `http://localhost:8080/api/reunioes`

### 1. Listar todas as reuniões
* **Método:** `GET`
* **Rota:** `/`
* **Respostas Possíveis:**
  * `200 OK`: Retorna um *array* de objetos JSON.

### 2. Buscar uma reunião específica
* **Método:** `GET`
* **Rota:** `/{id}`
* **Respostas Possíveis:**
  * `200 OK`: Retorna o objeto JSON da reunião encontrada.
  * `404 Not Found`: Retorna um JSON de erro customizado informando que o ID não existe.

### 3. Agendar uma nova reunião
* **Método:** `POST`
* **Rota:** `/`
* **Parâmetros de Corpo (Request Body):** JSON exigindo os campos `dataReuniao`, `horaReuniao`, `nomeCliente`, `nomeArquiteto` e `nomeConsultor`.
* **Respostas Possíveis:**
  * `201 Created`: Reunião salva com sucesso. Retorna os dados com o `id` gerado.
  * `400 Bad Request`: Falha na validação dos campos (ex: data no passado) ou formato JSON inválido. Retorna a lista de erros.

### 4. Atualizar uma reunião existente
* **Método:** `PUT`
* **Rota:** `/{id}`
* **Respostas Possíveis:**
  * `200 OK`: Retorna o objeto atualizado com sucesso.
  * `404 Not Found`: O ID não existe no banco.
  * `400 Bad Request`: Falha na validação ou formato.

### 5. Cancelar/Excluir uma reunião
* **Método:** `DELETE`
* **Rota:** `/{id}`
* **Respostas Possíveis:**
  * `204 No Content`: Exclusão realizada com sucesso. Nenhum corpo é retornado.
  * `404 Not Found`: O ID não existe no banco.

---

## 🚀 Como testar localmente

1. Execute a classe principal `ApiRecepcaoApplication.java`.
2. Acesse a documentação viva do Swagger em: `http://localhost:8080/swagger-ui.html`
