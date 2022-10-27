# Online Store Project Documentation

## Overview and Purpose

The purpose the application is to create an Online Store that allows customers to make a purchase and employees to process the order. The application has a feature where the employees are able to create and orders for supplies from other companies (Suppliers).

## Technologies

The application will be developed in Java and use the following supporting technologies:

- Spring Boot
- Spring Data
- Spring BWeb
- SQL Database (PostgreSQL)
- NoSQL Database (Redis and MongoDB)
- Rest API services
- Will use a microservice design approach

## Application Models

### Models

- [Category](#category)
- [Courier](#Courier)
- [Customer](#customer)
- [Department](#department)
- [Employee](#employee)
- [Order](#order)
- [OrderItem](#orderitem)
- [OrderStatus](#orderstatus)
- [OrderStatusName](#orderstatusname)
- [Product](#product)
- [ProductImage](#productimage)
- [Review](#review)
- [ShoppingCart](#shoppingcart)
- [Supplier](#supplier)
- [SupplierEmployeeContact](#supplieremployeecontact)
- [SupplierOrderStatus](#supplierorderstatus)
- [SupplyOrder](#supplierorders)
- [UserTypes](#usertype)

![Model Diagram](./images/OnlineStoreEnityDiagram.drawio.svg)

### Category

#### Purpose

The purpose of this class is to define the properties of category. The will hold information such the information related the type of Product such as TV, Perfume, Bicycles, Vegetables

#### Has associations with: Product

#### Properties

```java
   private int id;

   private String name;

   private String description;
```

### Courier

#### Purpose

The purpose of this class is to define the properties of supplier from where the company can purchase items for resale via the online store. The properties we be sum

#### Has associations with: Orders, Shopping Cart, Supplier Employee

#### Properties

```java

    private int id;

    private String name;

    private String emailAddress;

    private String address1;

    private String address2;

    private String phoneNumber;

    private String county;

    private String town;

    private String postCode;

```

### Customer

#### Purpose

The purpose of this class is to define the properties of customer such as contact details, delivery details and login credentials

#### Has associations with: Orders and Shopping Cart

#### Properties

```java
    private String emailAddress;

    private String password;

    private String billingFirstName;

    private String billingLastName;

    private String billingPhoneNumber;

    private String billingAddress1;

    private String billingAddress2;

    private String billingCounty;

    private String billingTown;

    private String billingPostCode;

    private String deliveryFirstName;

    private String deliveryLastName;

    private String deliveryPhoneNumber;

    private String deliveryAddress1;

    private String deliveryAddress2;

    private String deliveryCounty;

    private String deliveryTown;

    private String deliveryPostCode;

    private boolean accountActive;
```

### Department

#### Purpose

The purpose of this class is to define the properties of a department, and is used to determine which department belongs

#### Has associations with: Orders and Shopping Cart

#### Properties

```java
    private int id;

    private String name;

    private String description;
```

### Employee

#### Purpose

The purpose of this class is to define the properties of an employee this includes contact details, login details, access levels (not yet implemented), their department and position in the company

#### Has associations with: Department and UserTpye

#### Properties

```java
    private int id;

    private String emailAddress;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address1;

    private String address2;

    private String county;

    private String town;

    private String postCode;

    private String positionTitle;

    private Department department;

    private UserType userType;

```

### Order

#### Purpose

The purpose of this class is to define the properties of an order such as items ordered, delivery information and status

#### Has associations with: Customer, ShoppingCart, OrderItem

#### Properties

```java
    private long id;

    private LocalDateTime createdAt;

    private LocalDateTime deliveryDate;

    private double totalValue;

    private double deliveryCharge;

     private Customer customer;

    private List<OrderItem> listOrderItems;

    private Courier courier;

    private List<OrderStatus> listOrderStatus;
```

### OrderItem

#### Purpose

The purpose of this class is to define the properties of an item that has been ordered

#### Has associations with: Order and ShoppingCart

#### Properties

```java
    private  int id;

    private Product product;

    private int quantity;

    private double total;

```

### OrderStatus

#### Purpose

The purpose of this class is to define the properties of the various order status that can be assigned to an order

#### Has associations with: Order and OrderStatusName

#### Properties

```java
    private int id;

    private OrderStatusName orderStatusName;

    private LocalDateTime createdAt;

    private String description;
```

### OrderStatusName

#### Purpose

The purpose of this class is to define what Order status values can be assigned.

#### Has associations with: OrderStatus

#### Properties

```java
    public enum OrderStatusName
```

### Product

#### Purpose

The purpose of this class is to define the properties of a product such as the type of product, product details, images and number in stock

#### Has associations with: ProductImages, Category and OrderItems

#### Properties

```java
    private int id;

    private String name;

    private String description;

    private String detailedDescription;

    private String brand;

    private int quantity;

    private double price;

    private String warranty;

    private List<ProductImage> productImages;

    private Category category;
```

### ProductImage

#### Purpose

The purpose of this class is to define the properties of a product image such as name, description and the image

#### Has associations with: Product

#### Properties

```java
    private int id;

    private String name;

    private String description;

    private byte[] image;

    private Product product;
```

### Review

#### Purpose

The purpose of this class is to define the properties of a product review that customers can leave

#### Has associations with: Product

#### Properties

```java
    private int id;

    private String comments;

    private int rating;

    private Product product;

    private Customer user;
```

### ShoppingCart

#### Purpose

The purpose of this class is to define the properties of a shopping cart before it is converted to an order

#### Has associations with: Customer, OrderItem and Courier

#### Properties

```java
    private int id;

    private LocalDateTime createdDate;

    private LocalDateTime cartExpiryDate;

    private double total;

    private double deliveryCharge;

    private List<OrderItem> orderItems = new ArrayList<>();

    private Customer customer;

    private Courier courier;
```

### Supplier

#### Purpose

The purpose of this class is to define the properties of a Supplier the main company details of company that stock is purchased

#### Has associations with: SupplierEmployeeContact

#### Properties

```java
    private int id;

    private String name;

    private String emailAddress;

    private String address1;

    private String address2;

    private String phoneNumber;

    private String county;

    private String town;

    private String postCode;
```

### SupplierEmployeeContact

#### Purpose

The purpose of this class is to define the properties of a person in side the Supplier Company - example A person who you talk to about finance and invoices

#### Has associations with: Supplier

#### Properties

```java
    private int id;

    private String emailAddress;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address1;

    private String address2;

    private String county;

    private String town;

    private String postCode;

    private Supplier supplier;
```

### SupplierOrderStatus

#### Purpose

The purpose of this class is to define the properties of the statuses that can be assigned to an order during its lifecycle

#### Has associations with: SupplierOrder

#### Properties

```java
    private int id;

    private OrderStatusName orderStatusName;

    private String description;
```

### SupplyOrder

#### Purpose

The purpose of this class is to define the properties of an order that the firm places from its suppliers

#### Has associations with: OrderItem, Supplier,SupplierOrderStatus

#### Properties

```java
    private int id;

    private String number;

    private List<OrderItem> listOrderItems = new ArrayList<>();

    private Supplier supplier;

    private SupplierOrderStatus supplierOrderStatus;
```

### UserType

#### Purpose

The purpose of this class is to define the properties of an employee access level within the system.

#### Has associations with: OrderItem, Supplier,SupplierOrderStatus

#### Properties

```java
    private int id;

    private String name;

    private String description;
```
