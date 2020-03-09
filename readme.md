# RESTful Web Services with Spring and Spring Boot

Building RESTful web services with Spring Boot is fun. In this course, we will discover why Spring, Spring MVC and Spring Boot is becoming the best framework combination to develop RESTful web services. 

You will learn
- What is a RESTful Web Service? 
- How to implement RESTful Web Services with Spring and Spring Boot?
- What are the best practices in designing RESTful Web Services? 
- How to design Resources and GET, POST and DELETE operations?
- How to implement Validation for RESTful Web Services? 
- How to implement Exception Handling for RESTful Web Services? 
- What is HATEOAS? How to implement HATEOAS for a Resource?
- What are the different approach in versioning RESTful Services?
- How to use Postman to execute RESTful Service Requests?
- How to implement basic authentication with Spring Security?
- How to implement filtering for RESTful Services?
- How to monitor RESTful Services with Spring Boot Actuator?
- How to document RESTful Web Services with Swagger?
- How to connect RESTful Services to a backend with JPA?

## Steps

### Basics of RESTful Services
- Step 01 - Initializing a RESTful Services Project with Spring Boot
- Step 02 - Understanding the RESTful Services we would create in this course
- Step 03 - Creating a Hello World Service
- Step 04 - Enhancing the Hello World Service to return a Bean
- Step 05 - Quick Review of Spring Boot Auto Configuration and Dispatcher Servlet - What's happening in the background?
- Step 06 - Enhancing the Hello World Service with a Path Variable
- Step 07 - Creating User Bean and User Service
- Step 08 - Implementing GET Methods for User Resource
- Step 09 - Implementing POST Method to create User Resource
- Step 10 - Enhancing POST Method to return correct HTTP Status Code and Location URI
- Step 11 - Implementing Exception Handling - 404 Resource Not Found
- Step 12 - Implementing Generic Exception Handling for all Resources
- Step 13 - Exercise : User Post Resource and Exception Handling
- Step 14 - Implementing DELETE Method to delete a User Resource
- Step 15 - Implementing Validations for RESTful Services
- Step 16 - Implementing HATEOAS for RESTful Services

### Advanced RESTful Service Features
- Step 17 - Overview of Advanced RESTful Service Features
- Step 18 - Internationalization for RESTful Services
- Step 19 - Content Negotiation - Implementing Support for XML
- Step 20 - Configuring Auto Generation of Swagger Documentation
- Step 21 - Introduction to Swagger Documentation Format
- Step 22 - Enhancing Swagger Documentation with Custom Annotations
- Step 23 - Monitoring APIs with Spring Boot Actuator
- Step 24 - Implementing Static Filtering for RESTful Service
- Step 25 - Implementing Dynamic Filtering for RESTful Service
- Step 26 - Versioning RESTful Services - Basic Approach with URIs
- Step 27 - Versioning RESTful Services - Header and Content Negotiation Approaches
- Step 28 - Implementing Basic Authentication with Spring Security

### Connecting RESTful Service to JPA
- Step 29 - Overview of Connecting RESTful Service to JPA
- Step 30 - Creating User Entity and some test data
- Step 31 - Updating GET methods on User Resource to use JPA
- Step 32 - Updating POST and DELETE methods on User Resource to use JPA
- Step 33 - Creating Post Entity and Many to One Relationship with User Entity
- Step 34 - Implementing a GET service to retrieve all Posts of a User
- Step 35 - Implementing a POST service to create a Post for a User


### Links from course examples
- Basic Resources
  - http://localhost:8080/hello-world
  - http://localhost:8080/hello-world-bean
  - http://localhost:8080/hello-world/path-variable/Ranga
  - http://localhost:8080/users/
  - http://localhost:8080/users/1
- JPA Resources
  - http://localhost:8080/jpa/users/
  - http://localhost:8080/jpa/users/1
  - http://localhost:8080/jpa/users/10001/posts
- Filtering
  - http://localhost:8080/filtering
  - http://localhost:8080/filtering-list
- Actuator
  - http://localhost:8080/actuator
- Versioning
  - http://localhost:8080/v1/person
  - http://localhost:8080/v2/person
  - http://localhost:8080/person/param
     - params=[version=1]
  - http://localhost:8080/person/param
     - params=[version=2]
  - http://localhost:8080/person/header
     - headers=[X-API-VERSION=1]
  - http://localhost:8080/person/header
     - headers=[X-API-VERSION=2]
  - http://localhost:8080/person/produces
     - produces=[application/vnd.company.app-v1+json]
  - http://localhost:8080/person/produces
  	 - produces=[application/vnd.company.app-v2+json]
- Swagger
  - http://localhost:8080/swagger-ui.html
  - http://localhost:8080/v2/api-docs
- H2-Console
  - http://localhost:8080/h2-console

## Table Structure

```sql
create table user (
id integer not null, 
birth_date timestamp, 
name varchar(255), 
primary key (id)
);

create table post (
id integer not null, 
description varchar(255), 
user_id integer, 
primary key (id)
);

alter table post 
add constraint post_to_user_foreign_key
foreign key (user_id) references user;
```

## Example Requests

### GET http://localhost:8080/users
```json
[
    {
        "id": 1,
        "name": "Adam",
        "birthDate": "2017-07-19T04:40:20.796+0000"
    },
    {
        "id": 2,
        "name": "Eve",
        "birthDate": "2017-07-19T04:40:20.796+0000"
    },
    {
        "id": 3,
        "name": "Jack",
        "birthDate": "2017-07-19T04:40:20.796+0000"
    }
]
```

### GET http://localhost:8080/users/1
```json
{
    "id": 1,
    "name": "Adam",
    "birthDate": "2017-07-19T04:40:20.796+0000"
}
```

### POST http://localhost:8080/users
```json
{
    "name": "Ranga",
    "birthDate": "2000-07-19T04:29:24.054+0000"
}
```

### GET http://localhost:8080/users/1000
- Get request to a non existing resource. 
- The response shows default error message structure auto configured by Spring Boot.

```json
{
    "timestamp": "2017-07-19T05:28:37.534+0000",
    "status": 404,
    "error": "Not Found",
    "message": "id-500",
    "path": "/users/500"
}
```

### GET http://localhost:8080/users/1000
- Get request to a non existing resource. 
- The response shows a Customized Message Structure
```json
{
    "timestamp": "2017-07-19T05:31:01.961+0000",
    "message": "id-500",
    "details": "Any details you would want to add"
}
```

### POST http://localhost:8080/users with Validation Errors

#### Request

```json
{
    "name": "R",
    "birthDate": "2000-07-19T04:29:24.054+0000"
}
```

#### Response - 400 Bad Request

```json
{
    "timestamp": "2017-07-19T09:00:27.912+0000",
    "message": "Validation Failed",
    "details": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'user' on field 'name': rejected value [R]; codes [Size.user.name,Size.name,Size.java.lang.String,Size]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [user.name,name]; arguments []; default message [name],2147483647,2]; default message [Name should have atleast 2 characters]"
}
```

### GET http://localhost:8080/users/1 with HATEOAS
```json
{
    "id": 1,
    "name": "Adam",
    "birthDate": "2017-07-19T09:26:18.337+0000",
    "_links": {
        "all-users": {
            "href": "http://localhost:8080/users"
        }
    }
}
```

### XML Representation of Resources

#### GET http://localhost:8080/users

- Accept application/xml

```xml
<List>
    <item>
        <id>2</id>
        <name>Eve</name>
        <birthDate>2017-07-19T10:25:20.450+0000</birthDate>
    </item>
    <item>
        <id>3</id>
        <name>Jack</name>
        <birthDate>2017-07-19T10:25:20.450+0000</birthDate>
    </item>
    <item>
        <id>4</id>
        <name>Ranga</name>
        <birthDate>2017-07-19T10:25:20.450+0000</birthDate>
    </item>
</List>
```

#### POST http://localhost:8080/users
- Accept : application/xml
- Content-Type : application/xml

##### Request

```xml
<item>
        <name>Ranga</name>
        <birthDate>2017-07-19T10:25:20.450+0000</birthDate>
</item>
```

##### Response
- Status - 201 Created


### Versioning
 - Media type versioning (a.k.a “content negotiation” or “accept header”)
   - GitHub
 - (Custom) headers versioning
   - Microsoft
 - URI Versioning
   - Twitter
 - Request Parameter versioning 
   - Amazon
 - Factors
  - URI Pollution
  - Misuse of HTTP Headers
  - Caching
  - Can we execute the request on the browser?
  - API Documentation
 - No Perfect Solution 

#### More
- https://www.mnot.net/blog/2011/10/25/web_api_versioning_smackdown
- http://urthen.github.io/2013/05/09/ways-to-version-your-api/
- http://stackoverflow.com/questions/389169/best-practices-for-api-versioning
- http://www.lexicalscope.com/blog/2012/03/12/how-are-rest-apis-versioned/
- https://www.3scale.net/2016/06/api-versioning-methods-a-brief-reference/

## Resources and URI Mappings

- Retrieve all Users      - GET  /users
- Create a User           - POST /users
- Retrieve one User       - GET  /users/{id} -> /users/1   
- Delete a User           - DELETE /users/{id} -> /users/1

- Retrieve all posts for a User - GET /users/{id}/posts 
- Create a posts for a User - POST /users/{id}/posts
- Retrieve details of a post - GET /users/{id}/posts/{post_id}


---
