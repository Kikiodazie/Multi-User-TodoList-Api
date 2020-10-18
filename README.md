# TodoList-Api-With-Auth
A todolist api with user authentication and authorization using Json Web Token(JWT) written with springboot


#Getting Started

##Prerequisites
* Git
* IntelliJ IDEA (or similar)
* Datagrip (or similar)
* Docker
* Postman

###About

This is a mini project practicing on building secured API's with Spring Boot and JWT. The idea was to 
build a Todo-List API that performs CRUD operations, and can be consumed by a frontend/mobile app.

It was made using **Spring Boot**, **Spring Web**, **Spring Security**, **Spring Data JPA** and **Docker**. 
A postgres Database linked to the app image with docker-compose and deployed on docker-hub.

An API Documentation, created/updated using **Swagger**. Visit   ``http://localhost:8080/swagger-ui.html`` when running locally to view API docs.



|Endpoint  |   |  Functionality  |   |   |
|---|---|---|---|---|
|**Post** /signup   |  |  Signup |   |   |
|**Post** /login   |   | Login  |   |   |
|**GET** /log-out  |   | Logout  |   |   |
|**GET** /todos  |   | Get todos  |   |   |
|**Post** /todos   |   | Create todo  |   |   |
|**GET**/todos/{todoId} |   |Get a todo   |   |   |
|**PUT**/todos/{todoId}   |   |Update a todo   |   |   |
|**DELETE**/todos/{todoId}   |   |Delete a todo   |   |   |
|**GET**/todos/{todoId}/items   |   |Get todo items   |   |   |
|**POST**/todos/{todoId}/items   |   |Create todo item   |   |   |
|**PUT**/todos/{todoId}/items{itemId}   |   | Edit todo item  |   |   |
|**DELETE**/todos/{todoId}/items{itemId}  |   |Delete todo item   |   |   |
|**GET**/todos/{todoId}/comments  |   |Get comments   |   |   |
|**POST**/todos/{todoId}/comments  |   |Create comment   |   |   |
|**PUT**/todos/{todoId}/comments/{commentId}  |   | Edit comment   |   |   |
|**DELETE**/todos/{todoId}/comments/{commentId}   |   | Delete comment  |   |   |





