# Consultório Web

Sistema de gestão para consultório de psicologia: agenda de consultas, fluxo financeiro, lembretes via WhatsApp e relatórios.

## Tecnologias
- Java 21 + Spring Boot 3.x
- Spring Data JPA
- Bean Validation (Validation Groups)
- Lombok
- JUnit 5 + Mockito (testes)
- H2 (dev) / PostgreSQL (prod)
- Maven

## Status
🚧 Em desenvolvimento

## Funcionalidades

- [x] CRUD de Pacientes (com soft delete)
- [x] Agenda de Consultas (validação de horário, status e tipos)
- [x] Fluxo financeiro (lançamentos)
- [x] Tratamento global de exceções (@RestControllerAdvice)
- [x] Testes unitários (Consulta e Paciente)
- [ ] Anotações do paciente
- [ ] Lembretes via WhatsApp
- [ ] Relatórios
- [ ] Front-end (Angular)

## Testes

Testes unitários com JUnit 5 e Mockito cobrindo as regras de negócio dos Services (validação de horário, CPF duplicado, soft delete).

    mvn test


## Documentação da API

A coleção de requisições do Postman está versionada na pasta [`/postman`](./postman).
Importe o arquivo `consultorio-web.postman_collection.json` no Postman para testar os endpoints.
