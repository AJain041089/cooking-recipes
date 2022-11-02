## SPRING BOOT, SPRING SECURITY, JPA, SWAGGER AND MOCKITO
=================================================================

This project manage the RecipeBook. It has following functionality
--View all the recipe present in database.
--View all recipe based on filter(Filter applied on isVeg(true/false),Cooking Instruction,No of servings,Ingredients)
--Search recipe by its ID.
--Create a new recipe.
--Update the existing recipe by its ID.
--Delete the recipe by its ID.


SQL Script
==========
It has data.sql file which have all the queries to create table and Insert some records which will be available to us when application starts.


MAVEN JARS
==========
This project have the springboot parent starter jars
--Spring boot
--jpa
--web
--security
--test
--h2

CONFIGURE SPRING SECURITY FOR IN-MEMORY AUTHENTICATION
=====================================================

This project makes use of in-memory authentication. We set up the following users

+---------+----------+-----------------------------+
| user id | password |            roles            |
+---------+----------+-----------------------------+
| user    | password | 				USER           |
| admin   | password | 				ADMIN		   |
+---------+----------+-----------------------------+

DISPLAY CONTENT BASED ON USER ROLE
==================================

In the application, we want to display content based on user role.

- USER ROLE: users in this role will  be allowed to list, add, update, search recipes.
- ADMIN ROLE: users in this role will be allowed to list, add, update, search and delete recipes. 

SWAGGER
==================================
We configure the swagger and used various annotation to setup the rules for our API. Use below url to verify: localhost:8080/v2/api-docs localhost:8080/swagger-ui.html

JAVA DOC
========
Read the java doc for information about the classes and methods.

H2 Database
===========
We used the H2 database to access that use the below url:
localhost:8080/h2-console
(verify the database url jdbc:h2:mem:testdb )

Mockito
=======
Mockito framework used for unit testing.

Test the Application
====================
1. Run the Spring Boot application: RecipesbookApplication.java

2. Open a web browser for the app: http://localhost:8080

3. Log in using one of the accounts

+---------+----------+-----------------------------+
| user id | password |            roles            |
+---------+----------+-----------------------------+
| user    | password | 				USER           |
| admin   | password | 				ADMIN		   |
+---------+----------+-----------------------------+
4. Confirm that you can login and access data based on the roles.

## Changes needed for Production
==========================

Network
==========
The service should be fronted by a reverse proxy and/or load balancer with https capability, as it only implements
unencrypted http endpoints.

 Database and Search
======================
Replace the in-memory h2 database with a database server connection, with its credentials set outside the
application.

 Security
=============
The service secures the endpoint using basic authentication and a username / password in plaintext in the spring
configuration. Replace the basic authentication with a JWT/Oauth approach if more control is needed over access to the API.

 Improvements
===============
A number of other possible improvements:

- Friendlier error responses.
- Increase unit and integration test coverage.
- Pagination of the search results.
 
