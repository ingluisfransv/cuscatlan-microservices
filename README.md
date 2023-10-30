# cuscatlan-microservices
Microservices for Banco Cuscatlan
Installation Requirements for each microservice:
- INSTALL JAVA 17
- INSTALL APACHE MAVEN 3.9.3

Instructions to run each microservice:

- Configure and install dependencies: mvn clean install
- Go to the root directory of the selected microservice and run the command: mvn spring-boot:run 
- Go to the URL acoording to the port configured on the application.properties of each microservice using POSTMAN or other API Test Tool:
  - Customers: localhost:8686/customers
  - Payments: localhost:8989/payments
  - Products: localhost:8888/products
  - Orders: localhost:8887/orders
