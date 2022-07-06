# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.example.manage-recipe' is invalid and this project uses 'com.example.managerecipes' instead.

# Getting Started

## Installation
* Install MongoDB
* Create Connection in MongoDB Compass with 'localhost:27017'
* Create Database with name 'RecipeBook' as mentioned in application.properties

## Apis
* '/recipe/addRecipe' - Create new recipe record, Accepts Recipe Object in JSON format
* '/recipe/updateRecipeById' - Update recipe record based on ID, Accepts Recipe Object in JSON format 
* '/recipe/deleteRecipeById' - Delete recipe based on ID, Accepts ID parameter.
* '/recipe/getRecipes' - Get list of recipes based on parameters passed in request, Accepts {DishType, ServingCount, Ingredients, Instructions, ExcludeIngredients} as parameter

## Guide for Swagger

* Once application is in running state go to "http://localhost:8080/swagger-ui.html" in browser url.
* All the supported apis are exposed in Swagger UI.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#data.nosql.mongodb)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#data.nosql.mongodb)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
