
<p align="center">
  <img src="https://github.com/user-attachments/assets/9db3369e-9f26-4fdc-8ec0-f69afe7baa25">
</p>

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
     git clone https://github.com/CleymerAvila/Foro-Hub-API
     cd Foro-Hub-API
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
    
    ![image](https://github.com/user-attachments/assets/41e58795-1853-4d74-bf73-73b77caf4c67)
    
 3. Copy the bearer token

 4. ![image](https://github.com/user-attachments/assets/78624638-dc6d-4473-8e3f-8337207e1660)

 5. Paste on the authentication bearear-key field to allow the rest of the endpoints and there you go!

    ![image](https://github.com/user-attachments/assets/307455c1-6581-4428-872a-9a8701ba6752)


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


