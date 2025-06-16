# BACK-END_AEP
# Sistema de Postagens e Comentários - API REST

Esta API REST permite o cadastro de usuários, criação de posts, adição de comentários, autenticação por login e outras funcionalidades. Abaixo estão os detalhes dos principais endpoints e tecnologias utilizadas.

## 📋 Sumário

- Autenticação
- Posts
- Comentários
- Tecnologias Utilizadas

## 🔐 Autenticação

### 📝 Registrar novo usuário

`POST /auth/register`\
Cria um novo usuário no sistema.

**Corpo da requisição (JSON):**

```json
{
  "name": "Marcelo",
  "email": "marcelo@email.com",
  "senha": "123456"
}
```

### 🔑 Login

`POST /auth/login`\
Realiza o login do usuário com e-mail e senha.

**Corpo da requisição (JSON):**

```json
{
  "email": "bruno@email.com",
  "senha": "123456"
}
```

## 📬 Posts

### ➕ Criar novo post

`POST /posts/create?userId={userId}`\
Cria um post para o usuário com o ID especificado.\
Exemplo: `POST /posts/create?userId=2`

**Corpo da requisição (JSON):**

```json
{
  "titulo": "Primeiro Post",
  "descricao": "Esse é o conteúdo do meu post.",
  "endereco": "RUA 2",
  "tipo": "DENUNCIA",
  "dataCriacao": null,
  "curtidas": 0
}
```

⚠️ O campo `tipo` deve ser `"DENUNCIA"` ou `"SUGESTAO"` (valores aceitos do enum).

### 📌 Listar todos os posts

`GET /posts`\
Retorna todos os posts cadastrados.

### 👤 Listar posts de um usuário

`GET /posts/user/{userId}`\
Retorna os posts de um usuário específico.\
Exemplo: `GET /posts/user/1`

### 🗑️ Deletar um post

`DELETE /posts/{id}`\
Remove um post pelo ID.\
Exemplo: `DELETE /posts/3`

### ✏️ Atualizar um post

`PUT /posts/{id}`\
Atualiza um post existente pelo ID.

**Corpo da requisição (JSON):**

```json
{
  "titulo": "Post Atualizado",
  "descricao": null,
  "endereco": null,
  "tipo": null,
  "dataCriacao": null,
  "curtidas": 0
}
```

## 💬 Comentários

### ➕ Adicionar comentário a um post

`POST /comentarios/add?userId={userId}&postId={postId}`\
Adiciona um comentário a um post específico.\
Exemplo: `POST /comentarios/add?userId=1&postId=2`

**Corpo da requisição (JSON):**

```json
{
  "conteudo": "Esse post ficou LIXOP!"
}
```

### 📄 Listar comentários de um post

`GET /comentarios/post/{postId}`\
Retorna todos os comentários de um post específico.\
Exemplo: `GET /comentarios/post/2`

## 📦 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **JPA / Hibernate**
- **PostgreSQL**
- **Lombok**
