
## <P> Foro Hub API Rest</P>
API Rest developed in Java with Spring Boot Framework for a online forum managament. Allow to users to sign-up, sign-in, post topics, answers it and more.

## Index

1. [features](#features)
2. [Previous requeriment](#previous-requeriment)
3. [Configuration](#configuration)
4. [Usage](#usage)
5. [Project Structure](#project-structure)
6. [Contribution](#contributions)

## features

- User registration and authentication
- Topics, Users, Course and Replies managment.
- Show Topics orders by date.
- Documentation with Swagger
- Data persistence with MySQL
- Authentication with JWT Library

## Previous requeriment
- **Java 17 version or higher**
- **Maeven 3.8**
- **Spring Boot 3.4**

## Configuration 

  1. Clon Repository
     
     ```bash
     git clone (https://github.com/fervcg/foro-hub-challenge)
     cd foro-hub-challenge
  2. Configure enviroment or edit application.properties for you database connection

     ```yaml
      spring.datasource.url=jdbc:postgresql://localhost:5432/foro_db
      spring.datasource.username=user
      spring.datasource.password=password
      spring.jpa.hibernate.ddl-auto=update

  3. Compile and run project

     ```bash
     mvn clean install
     mvn spring-boot:run
     
  4. Open `http:localhost:8080` in your browser to test the API.


## Usage

 1. Open `http://localhost:8080/swagger-ui/index.html#/` in your browser for API usage
 2. Sign-in with your email and password to get the bearer token
 3. Copy the bearer token
 5. Paste on the authentication bearear-key field to allow the rest of the endpoints and there you go!

## Project Structure

      src
      ├── main
      │   ├── java/com/alura/forohub-api
      │   │   ├── controller   -> REST controllers.
      │   │   ├── dto          -> Data transfer Objects.
      │   │   ├── domain       -> JPA Entities and repositories.
      │   │   ├── infrastructure  -> Error handlers, security and docs.
      │   │   ├── service      -> Business Logic.
      │   └── resources
      │       ├── application.properties -> Configuration app.
      │       └── db.migration.sql             -> Scripts SQL and migration with flyway (optional).
      └── test
          └── java/com/ejemplo/foro      -> Units Test and Integration.            


## Contributions

¡Contributions are welcomed! to contribute:

1. Fork the repository.
2. Create a new branch for your feature or your fix: `git checkout -b feature/new feature`.
3. Make the changes and confirms commits: `git commit -m "adding new feature"`.
4. Send a Pull Requets describe your changes.


