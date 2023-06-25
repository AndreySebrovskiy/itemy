# itemy


## Requirements

- apache-maven-3.8.6 or higher (or use `mvnw` provided in repo)
- JDK 17
- Spring Boot 3
- docker-compose
- OAuth2 with keycloak
- IDE of your choice
- postman collection


## Build

Execute:

## RUN  `docker-compose up -d`  and use postman collection to check out REST API


## Alternatively RUN

* run `docker build -t application .`

* Run all needed services via `docker-compose up -d`

* Verify all started `docker-compose logs -f`


## Alternatively Run : Comment out app image.
Run all needed services via `docker-compose up -d`

* Build backend `mvn clean package`

* Run backend (class `ItemyApplication`) with `dev`  profile

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
