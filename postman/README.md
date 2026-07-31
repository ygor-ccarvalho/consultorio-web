# Testes da API — Postman

Coleção e environment para testar a API do Web Consultório.

## Arquivos
- `consultorio-web.postman_collection.json` — requests organizados por recurso
- `consultorio-web.postman_environment.json` — variáveis (localhost + IDs)

## Como importar
1. Abra o Postman → Import
2. Selecione os dois arquivos `.json`
3. No canto superior direito, selecione o environment "..."

## Variáveis do environment
| Variável       | Descrição                    | Exemplo               |
|----------------|------------------------------|-----------------------|
| local          | URL base da API              | http://localhost:8080 |
| pacienteId     | ID usado no fluxo de sucesso | (preenchido no E2E)   |
| consultaId     | ...                          |                       |
| lancamentoId   | ...                          |                       |

## Estrutura da coleção
- Cada recurso tem subpastas **Sucesso** e **Erros**
- Testes de **Erro** usam dados FIXOS do `data.sql` (não dependem de ordem)
- Fluxo de **Sucesso** monta seu próprio cenário (E2E encadeado)

## Como rodar
1. Suba a aplicação (`./mvnw spring-boot:run`) — o `data.sql` popula o seed
2. No Postman: **Runner** → selecione a coleção → Run
