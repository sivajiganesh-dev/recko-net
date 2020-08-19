# recko-net
Simple social networking application for sharing textual post


## Requirements
For building and running the application you need:

- [JDK 1.8](https://openjdk.java.net/install/)

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `io.recko.network.ReckoNetApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## To-Do

* 	[ ] User Registration
* 	[ ] User Authentication
* 	[ ] User Authorization
* 	[ ] Adding Friends
* 	[ ] Creating Posts
* 	[ ] Check Feed

* Last three steps can be done in alternatively also. There is no dependcy in flow of the application on last three parts.


## Explore Rest APIs

The app defines following APIs.

### URLs

|  URL |  Method | Desc | Remarks | Sample Req/Resp JSON Data |
|----------|--------------|--------------|--------------|--------------|
|`http://localhost:8080/users/registration`  | POST | Registration  |  Header `Content-Type:application/json`| [Req](#reg_req) [Res](#reg_resp)|
|`http://localhost:8080/authenticate`       | POST | Authenticate user | Header `Content-Type:application/json` | [Req](#auth_req) [Res](#auth_resp)|
|`http://localhost:8080/users/`              | GET | Get list of users  | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | [Res](#users_resp)|
|`http://localhost:8080/users/:id`           | GET | Get user by id  | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | |
|`http://localhost:8080/posts/feed`          | GET | Get list of post feed  | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | [Req](#feed_req) [Res](#feed_resp)|
|`http://localhost:8080/posts/:id`           | GET | Get post by id         | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | |
|`http://localhost:8080/posts/`              | POST| Create a post          | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | [Req](#cpost_req) [Res](#cpost_resp)|
|`http://localhost:8080/posts/`              | PUT | Update post            | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | |
|`http://localhost:8080/posts/:id`           | DELETE | Delete post by id   | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` ||
|`http://localhost:8080/friends/`            | GET | Get list of friends    | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | [Req](#fnds_req) [Res](#fnds_resp)|
|`http://localhost:8080/friends/:user_id`    | POST| Add friend by user id | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | [Req](#cfnds_req) [Res](#cfnds_resp)|
|`http://localhost:8080/friends/:user_id`    | DELETE | Delete friend by user id | Header `Content-Type:application/json` AND `Authorization:Bearer JWT` | |


## Sample JSON Request & Response Bodys
### Registration
##### <a id="reg_req">Register user -> users/registration/</a>
```json
{  
    "username": "user1",  
    "password": "password1",  
    "email": "email@gmail.com",  
    "phone": "9492951665"  
}   
```

##### <a id="reg_resp">Registered user response -> users/registration/</a>
```json
{
"id": 1,
"username": "user1",
"email": "email@gmail.com",
"phone": "9492951665"
}
```
### Authentication
##### <a id="auth_req">Authentication for user -> /authenticate</a>
```json
{   
    "username": "user1",  
    "password": "password1"
}   
 
```

##### <a id="reg_resp">Authentication for user response -> /authenticate</a>
```json
{
"jwt": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTU5ODAwMjAxMiwiaWF0IjoxNTk3ODIyMDEyLCJ1c2VybmFtZSI6InVzZXIxIn0.1ntZVo4ix9B1nY7m9f9fA8HSuTcKbcJpUMxgku5yorAn7bfBHlwbkjuWeIxaMqdDqblfCkJv3MaLNxjD9gxwVg"
}
```
### User`

##### <a id="users_resp">List of users response -> /users/</a>
```json
[
{
"id": 1,
"username": "user1",
"email": "email@gmail.com",
"phone": "9492951665"
},
{
"id": 2,
"username": "user2",
"email": "email2@gmail.com",
"phone": "9492951666"
},
{
"id": 3,
"username": "user3",
"email": "email3@gmail.com",
"phone": "9492951667"
}
]
```
### Posts
##### <a id="cposts_req">Create a post -> /posts/</a>
```json
{
  "subject":"Fintech",
  "content":"Recko enables you to Fintech."
}
```

##### <a id="cposts_resp">Create a post response -> /authenticate</a>
```json
{
"id": 1,
"userId": 1,
"subject": "Fintech",
"content": "Recko enables you to Fintech.",
"created": "2020-08-19T08:34:26.728+00:00"
}
```

##### <a id="feed_req">Get list of posts -> /posts/feed</a>

##### <a id="feed_resp">Get list of posts response -> /posts/feed</a>
```json
[
{
"id": 3,
"userId": 1,
"subject": "Dhoni",
"content": "Dhoni got retired from Ind Cricket on 15 Aug, 2020.",
"created": "2020-08-19T09:02:17.613+00:00"
},
{
"id": 2,
"userId": 1,
"subject": "Covid-19",
"content": "India preparing 3 vaccines for virus.",
"created": "2020-08-19T09:01:42.772+00:00"
},
{
"id": 1,
"userId": 1,
"subject": "Fintech",
"content": "Recko enables you to Fintech.",
"created": "2020-08-19T08:34:26.728+00:00"
}
]
```

### Friends
##### <a id="cfnds_req">Add friend -> /friends/2</a>

##### <a id="cfnds_resp">Add friend response -> /friends/2</a>
```json
{
"id": 2,
"username": "user2",
"email": "email2@gmail.com",
"phone": "9492951666"
}
```
##### <a id="fnds_req">List friends -> /friends/</a>

##### <a id="fnds_resp">List friends response -> /friends/</a>
```json
[
{
"id": 2,
"username": "user2",
"email": "email2@gmail.com",
"phone": "9492951666"
}
]
```

## Files & Directories

```
|-- HELP.md
|-- mvnw
|-- mvnw.cmd
|-- pom.xml
|-- recko-net.iml
|-- src
|   |-- main
|   |   |-- java
|   |   |   `-- io
|   |   |       `-- recko
|   |   |           `-- network
|   |   |               |-- ReckoNetApplication.java
|   |   |               |-- auth
|   |   |               |   |-- configuration
|   |   |               |   |   `-- SpringJwtConfiguration.java
|   |   |               |   |-- controller
|   |   |               |   |   `-- AuthController.java
|   |   |               |   |-- filter
|   |   |               |   |   `-- JwtRequestFilter.java
|   |   |               |   `-- model
|   |   |               |       |-- AuthRequest.java
|   |   |               |       `-- AuthResponse.java
|   |   |               |-- friend
|   |   |               |   |-- controller
|   |   |               |   |   `-- FriendController.java
|   |   |               |   |-- model
|   |   |               |   |   `-- Friend.java
|   |   |               |   |-- repository
|   |   |               |   |   `-- FriendRepository.java
|   |   |               |   `-- service
|   |   |               |       `-- FriendService.java
|   |   |               |-- post
|   |   |               |   |-- controll
|   |   |               |   |   `-- PostController.java
|   |   |               |   |-- model
|   |   |               |   |   `-- Post.java
|   |   |               |   |-- repository
|   |   |               |   |   `-- PostRepository.java
|   |   |               |   `-- service
|   |   |               |       `-- PostService.java
|   |   |               |-- user
|   |   |               |   |-- controller
|   |   |               |   |   `-- UserController.java
|   |   |               |   |-- domain
|   |   |               |   |   `-- UserDTO.java
|   |   |               |   |-- model
|   |   |               |   |   `-- User.java
|   |   |               |   |-- repository
|   |   |               |   |   `-- UserRepository.java
|   |   |               |   `-- service
|   |   |               |       |-- UserService.java
|   |   |               |       `-- UserServiceDetailsImpl.java
|   |   |               `-- util
|   |   |                   `-- JwtTokenUtil.java
|   |   `-- resources
|   |       |-- application.properties
|   |       |-- db
|   |       |   `-- schema.sql
|   |       |-- static
|   |       `-- templates
|   `-- test
|       `-- java
|           `-- io
|               `-- recko
|                   `-- network
|                       `-- ReckoNetApplicationTests.java

```

