# 📚 API de Avaliações de Livros

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge\&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8+-4479A1?style=for-the-badge\&logo=mysql\&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge\&logo=apachemaven)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge\&logo=swagger)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</p>

API REST desenvolvida utilizando **Java** e **Spring Boot** para gerenciamento de livros e avaliações.

A aplicação permite cadastrar livros, realizar upload da capa, gerenciar comentários e disponibiliza documentação completa da API através do **Swagger/OpenAPI**.

O projeto foi desenvolvido seguindo boas práticas de organização em camadas (**Controller → Service → Repository**), utilizando DTOs, Mappers e tratamento global de exceções para tornar a API mais organizada, escalável e de fácil manutenção.

---

# ✨ Funcionalidades

## 📖 Livros

* Cadastro de livros
* Consulta de livros
* Listagem completa
* Atualização de informações
* Remoção de livros
* Upload da imagem da capa

## 💬 Comentários

* Cadastro de comentários
* Consulta de comentários
* Associação entre comentários e livros

## ⚙️ API

* Validação de dados
* Tratamento global de exceções
* Respostas padronizadas
* Documentação automática com Swagger
* Arquitetura em camadas

---

# 🛠 Tecnologias Utilizadas

| Tecnologia      | Descrição                     |
| --------------- | ----------------------------- |
| Java 21         | Linguagem principal           |
| Spring Boot     | Framework principal           |
| Spring Web      | Desenvolvimento da API REST   |
| Spring Data JPA | Persistência dos dados        |
| MySQL           | Banco de dados relacional     |
| Maven           | Gerenciamento de dependências |
| Lombok          | Redução de código boilerplate |
| Swagger/OpenAPI | Documentação da API           |

---

# 📂 Estrutura do Projeto

```text
src
└── main
    ├── java
    │   └── com
    │       └── jm
    │           └── Avaliacoes_de_Livros
    │               ├── Config
    │               ├── Controller
    │               │   ├── Request
    │               │   └── Response
    │               ├── Exceptions
    │               ├── Mapper
    │               ├── Model
    │               ├── Repository
    │               ├── Service
    │               └── AvaliacoesDeLivrosApplication.java
    │
    └── resources
        └── application.properties
```

---

# 🏛 Arquitetura

A aplicação foi estruturada seguindo a arquitetura em camadas.

```text
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL
```

Cada camada possui uma responsabilidade específica, facilitando manutenção, testes e evolução da aplicação.

---

# ⚙️ Como Executar

## 1. Clone o repositório

```bash
git clone https://github.com/Jmarceloapolinario/Avaliacoes_de_Livros.git
```

---

## 2. Entre na pasta

```bash
cd Avaliacoes_de_Livros
```

---

## 3. Configure o banco de dados

Crie um banco MySQL.

Configure o arquivo:

```properties
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/avaliacoes_de_livros
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4. Execute a aplicação

Windows

```bash
mvnw.cmd spring-boot:run
```

Linux / macOS

```bash
./mvnw spring-boot:run
```

Ou execute diretamente pela sua IDE favorita.

---

# 📑 Documentação da API

Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

Toda a documentação dos endpoints poderá ser consultada diretamente pelo navegador.

---

# 📚 Endpoints

## Livros

| Método | Endpoint       | Descrição             |
| ------ | -------------- | --------------------- |
| POST   | `/livros`      | Cadastra um livro     |
| GET    | `/livros`      | Lista todos os livros |
| GET    | `/livros/{id}` | Busca um livro por ID |
| PUT    | `/livros/{id}` | Atualiza um livro     |
| DELETE | `/livros/{id}` | Remove um livro       |

---

## Comentários

| Método | Endpoint            | Descrição               |
| ------ | ------------------- | ----------------------- |
| POST   | `/comentarios`      | Cadastra um comentário  |
| GET    | `/comentarios`      | Lista comentários       |
| GET    | `/comentarios/{id}` | Busca comentário por ID |

> Os endpoints podem variar conforme a implementação dos controllers.

---

# 📥 Exemplo de Requisição

## Cadastro de Livro

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "sinopse": "Boas práticas para desenvolvimento de software."
}
```

---

# 📤 Exemplo de Resposta

```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "sinopse": "Boas práticas para desenvolvimento de software."
}
```

---

# ⚠️ Tratamento de Exceções

A aplicação possui tratamento centralizado utilizando `@ControllerAdvice`, retornando respostas padronizadas para erros da API.

Alguns exemplos de exceções tratadas:

* Livro não encontrado
* Livro vazio
* Comentário vazio
* Arquivo inválido
* Erros de validação
* Erros internos do servidor

---

# 📖 Padrões Utilizados

Durante o desenvolvimento foram utilizados diversos conceitos importantes do ecossistema Spring:

* DTOs para entrada e saída de dados
* Mappers para conversão entre entidades e DTOs
* Injeção de Dependências
* Repository Pattern
* Service Layer Pattern
* Tratamento Global de Exceções
* Validação com Bean Validation
* Separação de responsabilidades

---

# 👨‍💻 Autor

### Marcelo Alencar

Estudante de Sistemas de Informação 

* GitHub: https://github.com/Jmarceloapolinario
* LinkedIn: https://www.linkedin.com/in/marcelo-alencar-b7a072267/
