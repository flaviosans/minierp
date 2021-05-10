# minierp

## Procedimentos para rodar o projeto:
* Clonar esse repositório;
* Criar um banco de dados PostgreSQL com o nome de minierp2 (ou qualquer outro nome,
contanto que coincida com o nome do banco referenciado na opção `spring.datasource.url` do arquivo `application.properties`;
* Dê o comando `mvn install` e, após, o comando `mvn spring-boot:run`
* Importe a coleção de requests do Postman do link https://www.getpostman.com/collections/afb9dd7a0928f0406297;
* Rode os requests na ordem em que estão, de cima para baixo. Ele criará os produtos e pedidos
