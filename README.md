# Project : Diverse store

## Application Overview

The the reason for this project to is create an Online Application, tha allows customers to make purchases of the products that are available.

The application has can be divided into the following components:

- [Project : Diverse store](#project--diverse-store)
  - [Application Overview](#application-overview)
    - [Customer Management](#customer-management)
    - [Product Management](#product-management)
    - [Customer Order Management](#customer-order-management)
    - [Employee Management](#employee-management)
    - [Supply Management](#supply-management)
    - [Courier Management](#courier-management)
  - [Technologies and Concepts](#technologies-and-concepts)
  - [REST API User Guide](#rest-api-user-guide)
    - [Product Related Objects](#product-related-objects)
      - [Products](#products)
      - [Product Images](#product-images)
      - [Categories](#categories)

### Customer Management

This area is for the anything related to the Customer, this includes creating new customer records, updating and deleting Customer Records.

### Product Management

This area is for anything related to product management this includes adding, deleting, updating:

- Products
- Product Images
- Product Categories

### Customer Order Management

This area is for anything related to Customer management, this includes adding, updating, deleting orders, and shopping cart.

### Employee Management

This area is for anything related to Employee Management, this included adding, updating and deleting:

- Employeee records
- Departments
- User Types (these are groups that an employee can belong to for system level access)

### Supply Management

This area is for anything related to supply management, this includes adding, updating and deleting:

- Suppliers
- Suppler Contacts
- Supply Orders

### Courier Management

This area is for anything related to courier management, this includes adding, updating and deleting suppliers.

## Technologies and Concepts

The application is design using a microservice approach and using REST API for the interface, which a number of different backend technologies such as Java application coding, using PostgreSQL, MongoDB, Redis database data storage, using Spring Cloud, Spring Security for communication and security.

## REST API User Guide

### Product Related Objects

Below are examples of the REST APIs for the communicating with the Product area of the application.

#### Products

To add a new product:

```
POST /api/v1/products
```

The JSON example of the required data:

```JSON
{
    "name" : "Product_1",
    "description" : "This a short description",
    "detailedDescription" : "This is a detailed description",
    "brand" : "Product_Brand",
    "quantity" : 20,
    "price" : 30.25,
    "warranty" : "2 years",
    "reviews" : {"id" : 1 },
    "productImages" : { "id" : 1},
    "category" : {"id" : 1 }
}
```

To update an existing record:

```
PATCH /api/v1/products/
```

Via PATCH it is possible to update only the desired fields or all the fields:

Desired fields example:

```JSON
{

    "description" : "This an updated short description",
    "detailedDescription" : "This is a detailed description",
    "brand" : "Product_Brand",
    "quantity" : 20,
    "price" : 30.25,
    "category" : {"id" : 1 }
}
```

All fields example:

```JSON
{
    "name" : "Product_1",
    "description" : "This a short description",
    "detailedDescription" : "This is a detailed description",
    "brand" : "Product_Brand",
    "quantity" : 20,
    "price" : 30.25,
    "warranty" : "2 years",
    "reviews" : {"id" : 1 },
    "productImages" : { "id" : 1},
    "category" : {"id" : 1 }
}
```

To list all the products:

```
GET /api/v1/products/{id}
```

To list the details of a specific product:

```
GET /api/v1/products/{id}/product
```

To list the details of by Name:

```
GET /api/v1/products/?name=nameoftheproduct
```

To list all the products that belong to a specific brand:

```
GET /api/v1/products/?brand=brandname
```

To list all the products in a specific category:

```
GET /api/v1/products/{categoryID}/products
```

To list all the products who's quantity level is less than or equal to a value:

```
GET /api/v1/products/stock?quantity=number
```

To list all the products who's quantity level is less than or equal to a value in a specific Category:

```
GET /api/v1/products/{categoryID}/stock
```

To list all the images for a specific product:

```
GET /api/v1/products/{id}/images
```

To list all the reviews for a specific product:

```
GET /api/v1/products/{id}/reviews
```

#### Product Images

#### Categories

List all categories

```
GET /api/v1/categories
```

Get category by Name

```
GET /api/v1/categories/name?name=nameOFCategory
```


