# 📚 API de Avaliações de Livros

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-6DB33F?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8+-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-Migrations-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger)

</p>

API REST desenvolvida com **Java 21** e **Spring Boot 4** para gerenciamento de livros e comentários/avaliações.

Permite cadastrar livros com upload de capa, gerenciar comentários vinculados a cada livro, e conta com documentação interativa via **Swagger/OpenAPI**. O schema do banco é versionado com **Flyway**, e o projeto segue a arquitetura em camadas (Controller → Service → Repository), com DTOs, Mappers e tratamento centralizado de exceções.

---

## ✨ Funcionalidades

### 📖 Livros
- Cadastro de livro com upload de capa (`multipart/form-data`)
- Listagem completa e busca por ID
- Atualização de livro (incluindo nova capa)
- Remoção de livro
- Download/consulta da imagem da capa

### 💬 Comentários
- Cadastro de comentário vinculado a um livro (com nota em estrelas)
- Atualização de comentário
- Remoção de comentário

### ⚙️ Infraestrutura da API
- Validação de arquivo de capa (aceita apenas `.png`, `.jpg`, `.jpeg`)
- Nomes de arquivo gerados via UUID (sem uso de dado do usuário no path — evita path traversal)
- Tratamento global de exceções via `@RestControllerAdvice`
- Migrations de banco versionadas com Flyway
- Documentação automática com Swagger

---

## 🛠 Tecnologias Utilizadas

| Tecnologia       | Descrição                              |
| ---------------- | --------------------------------------- |
| Java 21          | Linguagem principal                     |
| Spring Boot 4    | Framework principal                     |
| Spring Web MVC   | Construção da API REST                  |
| Spring Data JPA  | Persistência de dados                   |
| Flyway           | Versionamento e migração do schema      |
| MySQL 8+         | Banco de dados relacional               |
| Maven            | Gerenciamento de dependências e build   |
| Lombok           | Redução de boilerplate                  |
| Swagger/OpenAPI  | Documentação interativa da API          |

---

## 📂 Estrutura do Projeto

```text
src
├── main
│   ├── java
│   │   └── com/jm/Avaliacoes_de_Livros
│   │       ├── Config          # Segurança, CORS, tratamento global de exceções
│   │       ├── Controller      # Endpoints REST
│   │       │   ├── Request     # DTOs de entrada
│   │       │   └── Response    # DTOs de saída
│   │       ├── Exceptions      # Exceções de negócio customizadas
│   │       ├── Mapper          # Conversão entidade ↔ DTO
│   │       ├── Model           # Entidades JPA
│   │       ├── Repository      # Interfaces Spring Data JPA
│   │       ├── Service         # Regras de negócio
│   │       └── AvaliacoesDeLivrosApplication.java
│   └── resources
│       ├── application.properties
│       └── db/migration        # Migrations Flyway (V1, V2, ...)
└── test
    └── java/com/jm/Avaliacoes_de_Livros
```

---

## 🏛 Arquitetura

```text
Cliente
   │
   ▼
Controller  →  valida entrada, orquestra upload de arquivo
   │
   ▼
Service     →  regra de negócio
   │
   ▼
Repository  →  Spring Data JPA
   │
   ▼
MySQL       →  schema gerenciado pelo Flyway
```

---

## ⚙️ Como Executar

### 1. Pré-requisitos
- Java 21+
- Maven (ou use o `mvnw`/`mvnw.cmd` incluído)
- MySQL 8+ rodando localmente

### 2. Clone o repositório

```bash
git clone https://github.com/Jmarceloapolinario/Avaliacoes_de_Livros.git
cd Avaliacoes_de_Livros
```

### 3. Configure o banco de dados

Edite `src/main/resources/application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/avaliacoes_de_livros?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=America/Fortaleza
spring.datasource.username=root
spring.datasource.password=sua_senha
```

> O banco é criado automaticamente na primeira execução (`createDatabaseIfNotExist=true`), e o **Flyway** cria as tabelas rodando as migrations em `src/main/resources/db/migration`. Não é necessário criar tabelas manualmente.

### 4. Configure o diretório de upload das capas (opcional)

Por padrão, as capas são salvas em `./uploads/capas/`, relativo à pasta onde a aplicação roda — a pasta é criada automaticamente se não existir. Para usar outro caminho:

```properties
app.upload.dir=/caminho/que/voce/quiser/
```

### 5. Execute a aplicação

Windows:
```bash
mvnw.cmd spring-boot:run
```

Linux / macOS:
```bash
./mvnw spring-boot:run
```

Ou execute `AvaliacoesDeLivrosApplication` diretamente pela sua IDE.

---

## 📑 Documentação da API

Com a aplicação rodando, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📚 Endpoints

### Livros — base `/livros`

| Método | Endpoint              | Descrição                                  |
| ------ | ---------------------- | ------------------------------------------- |
| POST   | `/livros/`              | Cadastra um livro (`multipart/form-data`)   |
| GET    | `/livros/`              | Lista todos os livros                       |
| GET    | `/livros/{id}`          | Busca um livro por ID                       |
| GET    | `/livros/{id}/imagem`   | Retorna a imagem da capa do livro           |
| PUT    | `/livros/{id}`          | Atualiza um livro (`multipart/form-data`)   |
| DELETE | `/livros/{id}`          | Remove um livro                             |

O corpo `multipart/form-data` do POST/PUT espera duas partes:
- `dados`: JSON com os campos do livro
- `file`: arquivo de imagem da capa (`.png`, `.jpg` ou `.jpeg`)

### Comentários — base `/livros/comentarios`

| Método | Endpoint                   | Descrição                        |
| ------ | ---------------------------- | ---------------------------------- |
| POST   | `/livros/comentarios/`       | Cadastra um comentário             |
| PUT    | `/livros/comentarios/{id}`   | Atualiza um comentário             |
| DELETE | `/livros/comentarios/{id}`   | Remove um comentário               |

---

## 📥 Exemplos de Requisição

### Cadastro de livro

Parte `dados` (JSON):
```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "sinopse": "Boas práticas para desenvolvimento de software."
}
```
Parte `file`: arquivo de imagem (`.png`/`.jpg`/`.jpeg`)

### Cadastro de comentário

```json
{
  "comentario": "Livro excelente, mudou minha forma de programar.",
  "estrelas": 5,
  "livroId": 1
}
```

---

## 📤 Exemplo de Resposta — Livro

```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "capa": "uploads/capas/3f2a1b9c-....jpg",
  "sinopse": "Boas práticas para desenvolvimento de software.",
  "comentarios": [],
  "createdAt": "2026-07-11T10:00:00",
  "updateAt": "2026-07-11T10:00:00"
}
```

---

## ⚠️ Tratamento de Exceções

Tratamento centralizado via `@RestControllerAdvice`, retornando `400 Bad Request` com mensagem descritiva para:

- Livro não encontrado
- Livro vazio (sem arquivo de capa)
- Comentário vazio
- Arquivo de capa inválido (extensão não suportada ou falha ao salvar)

---

## 🔓 Sobre segurança

Este projeto foi pensado para consumo por um front-end confiável em ambiente de desenvolvimento — por isso, atualmente **todos os endpoints estão liberados sem autenticação** (`permitAll`), com CORS restrito à origem configurada em `app.frontend.url`. Não há verificação de identidade/autorização implementada. Se for evoluir este projeto para produção, adicionar autenticação (JWT, OAuth2, etc.) é o próximo passo recomendado.

---

## 📖 Padrões e Práticas Utilizadas

- DTOs (`record`) para entrada e saída de dados
- Mappers dedicados para conversão entidade ↔ DTO
- Injeção de Dependências via construtor
- Repository Pattern (Spring Data JPA)
- Service Layer Pattern
- Tratamento Global de Exceções
- Migrations versionadas com Flyway
- Upload de arquivo com nome gerado (UUID), sem uso de dado do usuário no path
- Separação de responsabilidades por camada

---

## 👨‍💻 Autor

### Marcelo Alencar

Estudante de Sistemas de Informação

- GitHub: https://github.com/Jmarceloapolinario
- LinkedIn: https://www.linkedin.com/in/marcelo-alencar-b7a072267/
