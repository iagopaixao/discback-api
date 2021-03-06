# Disc Back API

[![Build Status](https://travis-ci.org/euiagosilva/discback-api.svg?branch=master)](https://travis-ci.org/euiagosilva/discback-api)

> A modern REST API for selling vinyl records and calculating customer cashback value.

This project is the result of a test asked by [Beblue](https://www.beblue.com.br/).

## Environment Provisioning

- Download the project: `$ git clone https://github.com/euiagosilva/discback-api.git`
- Solve dependencies: `$ ./gradlew -s --parallel clean build -x test`
- Testing execution: `$ ./gradlew test`
- Install [Docker](https://docs.docker.com/install/) and [Docker Compose](https://docs.docker.com/compose/install/) in your OS;
- Create the postgres database container: `$ docker-compose -f docker/docker-compose-postgres.yml up -d`

## Run Application and Executing Operations

Perform REST operations on API using [Swagger UI](https://swagger.io/tools/swagger-ui/). Only access:

- Execute: `$ ./gradlew bootRun`
- Access: `http://localhost:8081/swagger-ui.hml`  

## Meta

[Iago Paixão](https://www.linkedin.com/in/iagopaixao/) – euiagopaixao@gmail.com

Distributed under [Apache License 2.0](https://github.com/euiagosilva/discback-api/blob/master/LICENSE) license

https://github.com/euiagosilva/discback-api


