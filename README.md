# CRUD Java Web com DAO Tradicional
Este projeto é uma aplicação Java Web do tipo **CRUD** (Create, Read, Update, Delete), desenvolvida com **Servlets, JSP, JSTL e JDBC**, seguindo o padrão **DAO tradicional** para organização da camada de acesso a dados.

🔧 Tecnologias Utilizadas
- Java 8+  
- Servlet API  
- JSP + JSTL  
- JDBC  
- HTML/CSS  
- Maven  
- Banco de Dados (MySQL ou PostgreSQL)

🚀 Como executar o projeto
Clone o repositório:

bash:
git clone https://github.com/Phoenix-code21/j2ee-dao.git
Importe como projeto Maven na sua IDE (Eclipse, IntelliJ ou VS Code)
Rode o maven para baixar as dependências.

Configure o banco de dados:
Crie o banco com base no script SQL (em "database/" tem um sql de testes).

Altere as credenciais no arquivo:
br.com.phoenix.utils.DB.java
Execute com Tomcat (ou outro servidor compatível):

Faça o deploy na IDE.
Acesse: http://localhost:8080/nome-do-projeto

✅ Funcionalidades
Cadastro de usuários
Edição de usuários
Exclusão de usuários
Listagem de todos os usuários
Login/autenticação com filtro

