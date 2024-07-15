# Arquetipo de API Rest

Este repositorio contiene un arquetipo de API Rest con las siguientes características:
- Spring Boot
- Maven
- Java 17

Adicionalmente, se incorpora una versión con Gradle en lugar de Maven en la siguiente [rama](https://github.com/gabrielhuav/SecuenciasV3/tree/gradle).

## Instrucciones mínimas para poner en ejecución el proyecto

- Java Development Kit, (específicamente, se utiliza Amazon Corretto 17)
- Apache NetBeans IDE 21 (Opcional)
- Tomcat9
- Proyecto Spring Boot creado a través del servicio web [spring.io](http://start.spring.io/).

## Introduction
1. Se implementa un método que recibe como parámetro un número entero e implemente la secuencia de Collatz
   - Se puede probar con http://localhost:8080/collatz/19 y con http://localhost:8080/collatz?n=19
   - Se implementa un modelo y un controlador

2. Se implementa un método que recibe como parámetro un número entero e implemente la secuencia de FizzBuzz
   - Se puede probar con http://localhost:8080/fizzbuzz/15 y con http://localhost:8080/fizzbuzz?n=15
   - Se implementa un modelo y un controlador

3. Se implementa un endpoint en el API que responde a las siguientes características:
   - Path: /api/sequence/collatz/{input}
     - Se puede probar con http://localhost:8080/api/secuence/collatz o  http://localhost:8080/api/secuence/collatz/5
   - Métodos: PUT, GET (además del método PUT original, se agrega un método GET para la misma ruta)
   - Respuesta: Se devuelve un objeto Map que contiene la secuencia de Collatz como una cadena JSON
   - Se ha agregado un método GET adicional
     - Responde a la ruta /api/sequence/collatz sin parámetro input
     - Devuelve la secuencia de Collatz para un valor predeterminado.
   - Se implementa un modelo y un controlador
