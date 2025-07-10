#  Backend Jurídico

API RESTful para gerenciamento de processos jurídicos, construída em Spring Boot.
Este sistema permite o cadastro
• Gerenciar processos jurídicos: criar, editar, consultar e arquivar um ou mais
processos.
• Gerenciar partes envolvidas: cada processo deve permitir o registro de uma ou
mais partes (autor, réu, advogado).
• Gerenciar ações: registrar ações associadas a um processo (por exemplo,
petições, audiências, sentenças).
• Status do processo: deve ser possível indicar se o processo está ativo,
suspenso ou arquivado.

O projeto adota a Arquitetura Limpa (Clean Architecture), com o objetivo de manter o sistema desacoplado, testável e sustentável.

# Camadas:
Aplicacao: Contém os serviços responsáveis pela lógica de negócio. Aqui estão também os DTOs usados para transportar dados entre as camadas.
Dominio: Representa o núcleo da aplicação. Define os modelos de domínio e concentra as regras de negócio fundamentais.
PortaAdaptador: Camada de integração com o mundo externo.
Config: Contém as configurações gerais do sistema.

##  Tecnologias Utilizadas
- **Java 24**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **Maven**
- **Lombok**
#Test
- **JUnit 5 + Mockito + Instancio**
#Banco
- **PostgreSQL**
#Documentacao
- **Astah**
- **Swagger (OpenAPI)**

##  Como Rodar o Projeto
Pré-requisitos
Java 24 ou superior instalado e configurado no sistema
Apache Maven 3.9.10 instalado e configurado no PATH

# Subir o banco PostgreSQL
No Windows, para iniciar o banco PostgreSQL, execute o script .bat que está na pasta banco/db:

```
cd banco\db
postgres-juridico.bat
```

# Rodar o backend Spring Boot com Maven 3.9.10
Na raiz do projeto, execute o comando para iniciar a aplicação:

```
mvn clean install
mvn spring-boot:run
```

# Acessar a API LOCALMENTE
API disponível em: http://localhost:8080
Documentação Swagger UI: http://localhost:8080/swagger-ui.html


# Acessar a Publicada no Railway
API disponível em: https://backend-juridico-production.up.railway.app
Documentação Swagger UI: https://backend-juridico-production.up.railway.app/swagger-ui/index.html#/
