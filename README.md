# Consultório Web — Backend

Sistema de gestão para consultório de psicologia: agenda de consultas, pacientes, testes psicológicos (com controle de estoque), fluxo financeiro, lembretes via WhatsApp e relatórios.

> Frontend (Angular): [web-consultorio-frontend](https://github.com/ygor-ccarvalho/web-consultorio-frontend)

## Tecnologias

- Java 21 + Spring Boot 3.x
- Spring Data JPA + Flyway (migrations)
- Bean Validation (Validation Groups — Create/Update)
- Lombok
- JUnit 5 + Mockito (testes unitários e de integração)
- H2 (dev, in-memory) / PostgreSQL (prod)
- Maven

## Status

🚧 Backend com entidades principais concluídas e testado via Postman. Frontend Angular funcional (dashboard, agenda, pacientes, testes, financeiro) — login/autenticação pendente.

## Arquitetura

O projeto segue uma separação clara em camadas, com injeção de dependência via construtor (`@RequiredArgsConstructor` + campos `final`):

    Model      -> entidade JPA (mapeamento de tabela)
    DTO        -> objeto de transporte (entrada/saída da API, nunca expõe a entidade direto)
    Repository -> interface Spring Data JPA (acesso a dados)
    Service    -> regra de negócio (validações, soft delete, cálculos)
    Controller -> "thin controller": só recebe requisição, delega ao Service e devolve resposta

Validações diferem por contexto usando **Validation Groups** (ex: `Create` exige campos que `Update` não exige). Erros são tratados de forma centralizada por um `@RestControllerAdvice`.

## Decisões de modelagem

- **Soft delete (padrão) vs delete físico**: a maioria das entidades (Paciente, Consulta, TestePsicologico, etc.) usa soft delete — um campo `ativo` marca o registro como inativo em vez de removê-lo. Isso preserva histórico clínico e evita perda de dados usados em relatórios. `Lancamento` (financeiro) é a exceção: usa delete físico, pois um lançamento errado não tem valor histórico a preservar.
- **Tentar excluir algo em uso retorna 409**: um registro vinculado a outro (ex: paciente com consultas) não pode ser inativado/excluído sem tratamento explícito.
- **Snapshot de dados históricos**: em `ConsultaTeste`, o `custoUnitario` é gravado no momento da aplicação do teste (não referencia o valor atual do cadastro). Isso garante que alterar o preço de um teste no futuro não reescreve o histórico financeiro passado.
- **Campos derivados nunca são persistidos**: valores calculados (ex: `custoTotal`, idade, sessão X de Y) são computados na saída do DTO, nunca salvos no banco.
- **BigDecimal para dinheiro**: nenhum valor monetário usa `double`/`float`, evitando erros de arredondamento.
- **DTOs de entrada não recebem campos controlados pelo Service**: ex: status inicial de uma consulta é sempre definido pela regra de negócio, nunca vindo do corpo da requisição.

## Migrations (Flyway)

O projeto usa Flyway em vez de `data.sql`. Cada alteração de schema é uma nova migration em `src/main/resources/db/migration`, nomeada como `V{numero}__descricao.sql`. Uma migration já aplicada **nunca é editada** — qualquer ajuste gera uma nova migration. O banco H2 (dev) é recriado do zero a cada boot, rodando todas as migrations em sequência; os dados de seed (exemplos para teste manual/Postman) também vivem em migrations.

Configuração relevante: `ddl-auto=validate` (o Hibernate só confere se o schema bate com as entidades, nunca gera DDL automaticamente) e `open-in-view=false`.

## Estrutura de pastas

    src/main/java/com/ygor/web_consultorio/
    ├── paciente/          # Model, DTO, Repository, Service, Controller
    ├── consulta/
    ├── lancamento/
    ├── anotacaopaciente/
    ├── avaliacao/
    ├── testepsicologico/
    ├── consultateste/     # vínculo N-N Consulta <-> TestePsicologico
    ├── config/            # CorsConfig, etc.
    └── exception/         # StandardError, @RestControllerAdvice

    src/main/resources/
    ├── application.properties
    └── db/migration/       # scripts Flyway (V1__..., V2__...)

    postman/                 # coleção versionada de requisições

## Funcionalidades

### Concluído
- [x] CRUD de Pacientes (soft delete, validação de CPF único, aniversariantes da semana)
- [x] Agenda de Consultas (validação de conflito de horário, status, tipos)
- [x] Fluxo financeiro (lançamentos — delete físico)
- [x] Anotações do paciente (append-only)
- [x] Avaliações (versão enxuta)
- [x] Testes Psicológicos (CRUD + controle de estoque)
- [x] ConsultaTeste (vínculo N-N Consulta↔Teste, débito automático de estoque, snapshot de custo)
- [x] Tratamento global de exceções (`@RestControllerAdvice` — 404/409/422)
- [x] Testes unitários (ConsultaService, PacienteService) + testes de integração (PacienteServiceIT)
- [x] Migrations com Flyway (substitui `data.sql`, seed versionado)
- [x] Coleção Postman completa (sucesso + erros 404/409/422) para todos os recursos

### Pendente / Pausado
- [ ] Lembretes via WhatsApp (Fase 2)
- [ ] Relatórios (Fase 2 — excluindo consultas CANCELADA)
- [ ] Login / Autenticação (JWT)
- [ ] Integração automática Consulta → Financeiro (gerar lançamento ao marcar consulta como realizada)

## Testes

Testes unitários com JUnit 5 e Mockito cobrindo as regras de negócio dos Services (validação de horário, CPF duplicado, soft delete), e testes de integração com `@SpringBootTest` + `@Transactional` (rollback automático). A pirâmide de testes prioriza muitos testes unitários e poucos de integração.

    mvn test

## Documentação da API

A coleção de requisições do Postman está versionada na pasta `/postman`. Importe o arquivo `consultorio-web.postman_collection.json` no Postman para testar os endpoints (casos de sucesso e erro: 404/409/422).

## Como rodar o projeto

**Pré-requisitos:** Java 21 e Maven instalados.

    # 1. Clone o repositório
    git clone https://github.com/ygor-ccarvalho/consultorio-web
    cd consultorio-web

    # 2. Rode a aplicação (usa H2 em memória por padrão)
    mvn spring-boot:run

A API fica disponível em `http://localhost:8080`. Em ambiente de desenvolvimento, o banco H2 é recriado e as migrations do Flyway rodam automaticamente a cada boot — não é necessário configurar nada manualmente.

Para rodar com PostgreSQL (produção), configure as variáveis de conexão em `application-prod.properties` e inicie com o profile `prod`:

    mvn spring-boot:run -Dspring-boot.run.profiles=prod

Para testar o frontend junto, veja o README do [web-consultorio-frontend](https://github.com/ygor-ccarvalho/web-consultorio-frontend) — o CORS já está liberado para `http://localhost:4200`.
