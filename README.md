# Roulette Game :flower_playing_cards:

This project corresponds to the backend of a betting roulette, developed in [Spring Boot](https://spring.io/), and using [Redis](https://redis.io/) as a database engine.

## Installation / local-environment

Requirements
- JDK 8 or later
- Gradle 4+ or Maven 3.2+
- Redis 3.2.1 +

```shell
git clone https://github.com/AlaskaRising/roulette-backend.git
cd roulette-backend
./mvnw spring-boot:run
```
>After executing the commands, the app will be receiving requests on port 8080, it is essential to have a Redis engine running on the default port 6379.

#### Another way to run the project is by importing it into an editor like IntelliJ or SpringToolSuite.

## Considerations
> To calculate the winnings amounts, the basic rules of a roulette wheel with a single 0 were followed. [wikipedia-roulette](https://en.wikipedia.org/wiki/Roulette)
* Black numbers are those in which the reduction of the sum of their digits is pair. The exceptions are 10 and 28 (both black).
* The winnings of matching the RED or BLACK color are twice the bet.
* The winnings of matching a number are thirty six times the bet.
* The number 0 is green, a color that cannot be bet on.


## Features


Endpoints are available to play from the creation of the roulette, opening bets, placing bets, closing bets and a query of the results obtained by all bettors.

#### The available endpoints correspond to: 

* [/newRoullete](https://github.com/AlaskaRising/roulette-backend/blob/bfefbcf47ce38a8c51a21a6333ceceb65344d332/src/main/java/com/roulette/app/controller/RouletteController.java#L35)
* [/getAllRoulettes](https://github.com/AlaskaRising/roulette-backend/blob/bfefbcf47ce38a8c51a21a6333ceceb65344d332/src/main/java/com/roulette/app/controller/RouletteController.java#L44)
* [/openRoulette](https://github.com/AlaskaRising/roulette-backend/blob/bfefbcf47ce38a8c51a21a6333ceceb65344d332/src/main/java/com/roulette/app/controller/RouletteController.java#L51)
* [/closeRoulette](https://github.com/AlaskaRising/roulette-backend/blob/bfefbcf47ce38a8c51a21a6333ceceb65344d332/src/main/java/com/roulette/app/controller/RouletteController.java#L60)
* [/placeBet](https://github.com/AlaskaRising/roulette-backend/blob/bfefbcf47ce38a8c51a21a6333ceceb65344d332/src/main/java/com/roulette/app/controller/BetController.java#L23)


> A [collection](https://www.getpostman.com/collections/18db9343db9851e4e8c1) of the requests made from postman is added.


## Roadmap

* Resolve Jedi dependency issues when trying to dockerize.
* Develop an interactive front end.

:ocean: Thanks !
