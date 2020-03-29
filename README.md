# Aplicação CIF - Question API

Microserviço que contempla o gerenciamento dos dados de questões da base da aplicação CIF.

# Veja Também
Os links abaixo levam as outras API's usadas no projeto. 
 * [Person API](https://github.com/MathLanca/personAPI/blob/master/README.md)
 * [Information Sorce API](https://github.com/MathLanca/informationSourceAPI/blob/master/README.md)
 * [Evaluation API](https://github.com/MathLanca/evaluationAPI/blob/master/README.md)


# Endpoints e Responses
Base Url: https://java-cif-question-api.herokuapp.com

List All
-
* Lista todas as perguntas registradas na base
```http
 GET /v1/listAll
```
Example Response
```json
[
  {
      "id": "string",
      "code": "string",
      "description": "string",
      "group": "string"
  }
]
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |

Find By Code
-
* Retorna a questão referente ao código recebido
```http
 GET /v1/questionCode/{code}
```

|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `code` | `string` | Question Code  |  **TRUE**    |

Example Response
```json
{
    "id": "string",
    "code": "string",
    "description": "string",
    "group": "string"
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |

Find By Id
-
* Retorna a questão referente ao id recebido
```http
 GET /v1/questionCode/{id}
```

|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `id` | `string` | Question ID  |  **TRUE**    |

Example Response
```json
{
    "id": "string",
    "code": "string",
    "description": "string",
    "group": "string"
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |

Insert Question
-
* Cadastra uma nova questão
```http
 POST /v1/question/save
```

```json
{
	"code": "string",
	"description": "string"
}
```

Example Response
```json
{
    "id": "string",
    "code": "string",
    "description": "string",
    "group": "string"
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |

Update Question
-
* Atualiza uma questão
```http
 PUT /v1/question/{ID}
```

|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `id` | `string` | Question ID  |  **TRUE**    |

```
{
	"code": "string",
	"description": "string"
}
```

Example Response
```json
{
    "id": "string",
    "code": "string",
    "description": "string",
    "group": "string"
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |

Update Question
-
* Atualiza uma questão
```http
 DELETE /v1/question/{ID}
```

|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `id` | `string` | Question ID  |  **TRUE**    |

Example Response
```json
{
    "id": "string",
    "code": "string",
    "description": "string",
    "group": "string"
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |
