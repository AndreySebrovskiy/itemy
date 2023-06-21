# itemy


## Requirements

- apache-maven-3.8.6 or higher (or use `mvnw` provided in repo)
- JDK 17
- Spring Boot 3
- `docker-compose`
- OAuth2 with 
- IDE of your choice
- postman collection


## Build

Execute:

`mvn clean package`


## Run
Run all needed services via `docker-compose up --build`

## Alternatively: comment itemy-application in  docker-compose 

* Run all needed services via `docker-compose up -d`

* Verify all started `docker-compose logs -f`

* Build backend `mvn clean package`

* Run backend (class `GradBackendApplication`) with `dev`  profile

[[app-ports]]
== Application ports
. BE is on `8083`
. Keycloak is on `8080`

"BE client id": `itemy-backend`
"client-secret": `6WXVHu6HtSEqEQZtjNx9mwGMgEuO6Dmr`

In this solution there is lack of tests and Rest endpoints with functionality. Tried to make
it simple.
For more please visit https://github.com/AndreySebrovskiy/cityApp
Testcontainers, integration tests and filtering search. 
