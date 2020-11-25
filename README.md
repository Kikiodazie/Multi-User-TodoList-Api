# TodoList-Api-With-Auth
A todolist api with user authentication and authorization using Json Web Token(JWT) written with springboot


# Getting Started

## Prerequisites
* Git
* IntelliJ IDEA (or similar)
* Datagrip (or similar)
* Docker
* Postman

### About

This is a mini project practicing on building secured API's with Spring Boot and JWT. The idea was to 
build a User Todo-List API that performs CRUD operations, and can be consumed by a frontend/mobile app.

Endpoints except, **/signup**, **/login** can only be accessed with a JWT token(Sent via httpHeader) which is generated when the user **/login** and invalidated when **/log-out**. 

It was made using **Spring Boot**, **Spring Web**, **Spring Security**, **Spring Data JPA** and **Docker**. 
A postgres Database linked to the app image with docker-compose and deployed on docker-hub.

An API Documentation, created/updated using **Swagger**.

View The Database Entity Relationship Diagram [TodoList ERD](https://lucid.app/lucidchart/invitations/accept/988fb2fa-a21d-40e9-a136-0b1c4cff4552)


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




# How To Run

**Step 1**
* Visit http://springtodolistapi.herokuapp.com/swagger-ui.html to view API Docs and Test Endpoints.

**Step 2**
* To obtain an authorization code, Open **POSTMAN** 
``http://springtodolistapi.herokuapp.com/login``

````
{
    "email": "test@gmail.com",
    "password": "test"
}
````

Copy the "Bearer token"  code from response header and paste as api key in authorize button.

            You can now access all the endpoints as test@gmail.com user.
 
To signup new user, provide the required data as stated on the docs in json from and go back to **STEP 2**.
 


### Locally
Clone the app repo & pull the **Docker** database image from **DockerHub**

* After cloning 

**Configuration**
Folder src/resources/ contains config files for todolist-API.

src/resources/application.properties - main configuration file.

Confirm the datasource url is : ``spring.datasource.url=jdbc:postgresql://localhost:5432/postgres`` to access locally.

* Pull the database image from DockerHub
``docker pull kikiodazie/todolist-api-postgres-image``

* Run the database ``docker run -d --name todoListApi-image -p 5432:5432 kikiodazie/todolist-api-postgres-image``

* Run the app on your IDE.

* Visit   ``http://localhost:8080/swagger-ui.html`` to view API docs or use **Postman** to test endpoints. 


 



