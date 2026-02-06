# API Gerador de Senhas Seguras

API REST desenvolvida com Spring Boot 3 para geração de senhas aleatórias customizáveis com foco em segurança criptográfica.

## Descrição Técnica
O projeto utiliza a classe java.security.SecureRandom para garantir que a geração dos caracteres seja imprevisível, atendendo a requisitos de segurança superiores aos da classe Random convencional. A API permite a definição de parâmetros via Query Strings para personalizar a composição da senha.

## Tecnologias
* Java 17
* Spring Boot 3
* Maven
* Lombok

## Endpoints
GET /api/senhas/gerar
Parâmetros aceitos:
- tamanho (int): define o comprimento da senha (padrão: 12).
- usarMaiusculas (boolean): inclui letras de A-Z.
- usarNumeros (boolean): inclui algarismos de 0-9.
- usarSimbolos (boolean): inclui caracteres especiais.

## Como Executar
1. Certifique-se de ter o Java 17 instalado.
2. Execute o comando:
   ./mvnw spring-boot:run

## Exemplo de Requisição (PowerShell)
$response = Invoke-RestMethod -Uri "http://localhost:8080/api/senhas/gerar?tamanho=16&usarSimbolos=true"
Write-Output $response

## Regras de Negócio
- Validação de tamanho mínimo (4) e máximo (100) de caracteres.
- Tratamento de exceções para parâmetros inválidos.
