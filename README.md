# Instruções

### Product API
Projeto referente ao desafio técnico para a vaga Software Engineer 1.\

A aplicação permite o gerenciamento de produtos de acordo com as especificações ditas na
descrição do desafio.

### Versões utilizadas no projeto:
* Java >= 8 - [JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
* Spring Boot - [2.6.0](https://docs.spring.io/spring-boot/docs/2.6.0.RELEASE/reference/htmlsingle/)
* Maven - [4.0.0](https://maven.apache.org/docs/4.0.0/release-notes.html)

Importar o projeto como Maven na IDE de sua preferência (ex.: IntelliJ, Eclipse, Spring Tools etc).

### Endpoints
- **POST** - http://54.224.232.53:8085/v1/product:
  Endpoint referente ao cadastro de um novo produto, deve conter o **header**: ***Content-Type: text/html;charset=utf-8*** e seguir o padrão **HTML** no body;
  
  - Body: "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN"


- **PUT** - http://54.224.232.53:8085/v1/product/?logic={logic}:
  Endpoint refente a alterações de produtos já existentes na base de dados, deve conter o **header**: ***Content-Type: application/json*** e conter o atributo **logic** como parâmetro da requisição;
  - Body:
    {\
    "logic": 44332211,\
    "serial": "123",\
    "model": "CAIOCAMINHA",\
    "sam": 0,\
    "ptid": "F04A2E4088B",\
    "plat": 4,\
    "version": "8.00b3",\
    "mxr": 0,\
    "mxf": 16777216,\
    "pverfm": "PWWIN" \
    }


- **GET** - http://54.224.232.53:8085/v1/product/?logic={logic}:
  Endpoint referente a consulta de um produto específico já cadastrado na base de dados, deve ser passado o atributo **logic** como parametro da requisição;
  
 ## Responses
 - **Sucesso**: 
   { \
    "meta": {\
       "server": "product-api"\
    },\
    "data": {\
              "logic": 44332211,\
              "serial": "123",\
              "model": "PWWIN",\
              "sam": 0,\
              "ptid": "F04A2E4088B",\
              "plat": 4,\
              "version": "8.00b3",\
              "mxr": 0,\
              "mxf": 16777216,\
              "pverfm": "PWWIN"\
   }\
   }
   

- **Error**:
  {\
  "code": 400,\
  "status": "Bad Request",\
  "message": "Product not found"\
  }
