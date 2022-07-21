![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
# Online shop model
This project contains a simlpe example of online shop.

## Used technologies
1. Java;
2. Spring Boot;
3. Maven
4. PostgreSQL;

## Controlling functions
### Product section
1. Get all products from "products" table: 
```sh
http://localhost:8080/products/all
```
2. Get a page of 5 products from "products" table ("*id*" - page id): 
```sh
http://localhost:8080/products/page/{id}
```
3. Get a product by product ID from "products" table ("*id*" - product id): 
```sh
http://localhost:8080/products/{id}
```
4. Post a new product (***admin function***):
```sh
http://localhost:8080/products/post
```
5. Delete product (***admin function***, "*id*" - product id):
```sh
http://localhost:8080/products/delete?id=id
```
6. Update product by ID (***admin function***, "*id*" - product id):
```sh
http://localhost:8080/products/update-product?id=id
```
7. Update product price by ID (***admin function***, "*id*" - product id):
```sh
http://localhost:8080/products/update-price?id=id
```
8. Update product name by ID (***admin function***, "*id*" - product id):
```sh
http://localhost:8080/products/update-name?id=id
```

### Client section
1. Get all clients from "clients" table (***admin function***):
```sh
http://localhost:8080/clients/all
```
2. Get a client by ID (***admin function***, "*id*" - client id):
```sh
http://localhost:8080/clients/{id}
```
3. Post a new client:
```sh
http://localhost:8080/clients/add
```
4. Delete a client by ID ("*id*" - client id):
```sh
http://localhost:8080/clients/delete?id=id
```

### Cart section
1. Add a product to cart by client ID and product ID:
```sh
http://localhost:8080/cart/add?clientId=3&productId=17
```
2. Delete a product from cart by client ID and product ID:
```sh
http://localhost:8080/cart/delete?clientId=2&productId=12
```
3. Get all products in cart by client ID:
```sh
http://localhost:8080/cart/client-all?clientId=3
```

### Order section
1. Create a new order by client ID ("*id*" - client id):
```sh
http://localhost:8080/orders/create?clientId=id
```
2. Get all orders:
```sh
http://localhost:8080/orders/all
```
3. Get order by client ID ("*id*" - client id):
```sh
http://localhost:8080/orders/{id}
```
4. Delete order by client ID ("*id*" - client id):
```sh
http://localhost:8080/orders/delete?clientId=id
```
