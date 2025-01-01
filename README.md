
# QR Code Generator API

Este projeto fornece uma API REST para gerar QR Codes e retorná-los no formato Base64. Você pode enviar um link e um título, e a API retornará o QR Code gerado correspondente ao link, codificado em Base64, juntamente com o título.

## Funcionalidades

- Gerar QR Code: Envie um link e um título para gerar um QR Code codificado em Base64.
- Validação: A API valida o título e o link fornecidos. Ambos são obrigatórios e não podem ser vazios.

## Endpoints

### POST /api/v1/qr-code

Este endpoint gera um QR Code em Base64.

#### Request

```json
{
  "title": "Example Title",
  "link": "https://example.com"
}
```

- title: Título do QR Code (não pode ser vazio).
- link: Link para o qual o QR Code será gerado (não pode ser vazio).

#### Response

```json
{
  "success": true,
  "dateTime": "2025-01-01T06:27:44.320519800",
  "data": {
    "title": "Example Title",
    "qrCodeBase64": "iVBORw0K... (base64 encoded QR Code image)"
  }
}
```

- success: Indicador de sucesso da operação.
- dateTime: Data e hora da resposta.
- data: Contém o título e o QR Code em Base64.

#### Erros

Se algum dos campos estiver faltando ou inválido, a API retornará um erro com status HTTP 400 (Bad Request).

Exemplo de resposta com erro:

```json
{
  "success": false,
  "dateTime": "2025-01-01T06:27:44.320519800",
  "data": {
    "title": "Title cannot be empty",
    "link": "Link cannot be empty"
  }
}
```

Se ocorrer algum erro interno, será retornado um status HTTP 500 (Internal Server Error).

## Dependências

- Spring Boot: Framework principal para criar a API.
- Spring Web: Para configurar a API RESTful.
- Spring Validation: Para validação dos campos de entrada.
- ZXing: Biblioteca para gerar QR Codes.

## Como executar

### Pré-requisitos

Certifique-se de ter o Java 17 ou superior instalado em sua máquina.

### Passos para executar

1. Clone o repositório:
    git clone https://github.com/seu-usuario/qr-code-generator.git

2. Navegue até o diretório do projeto:
    cd qr-code-generator

3. Execute a aplicação:
    ./mvnw spring-boot:run

    Ou, se estiver usando o Maven diretamente:
    mvn spring-boot:run

## Testando a API

Você pode testar a API utilizando o Postman ou cURL. Aqui está um exemplo de requisição cURL:

curl -X POST http://localhost:8080/api/v1/qr-code   -H "Content-Type: application/json"   -d '{"title": "Example Title", "link": "https://example.com"}'

## Tratamento de Exceções

A API realiza o tratamento de exceções com respostas padronizadas para facilitar a integração e utilização:

- Se algum campo obrigatório estiver vazio ou inválido, retornará um erro com detalhes do campo faltante.
- Se ocorrer um erro inesperado, a API retornará um erro genérico.
