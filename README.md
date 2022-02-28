# Innova Java Spring Capstone Project

[<h2>Click for Loan Management Service](https://github.com/furkanni/LoanManagement)

## Scope
The purpose of this project is to run a validation process for a bank's loan application. First of all, the customer inputs personal informations such as name, surname, identity number, monthly income and mobile phone number to the system. Then, system gets the credit score of the customer (from another database which is assumed as already been created) by customer's identity number and checks the application's result with the help of customer's monthly income information. When the application form is filled by the customer, system sends an automatic SMS for notify the customer about the result of customer's application is approved or denied. If the application had approved then this message includes the Loan Limit of the customer can get.

## Additional Information
This project is written under Microservice Architecture. As a result, this project seperated in two services which are: Customer and Loan.

##Technologies That Used In This Project
- <span style="color: cornflowerblue" > Maven </span>
- <span style="color: cornflowerblue" > Validation </span>
- <span style="color: cornflowerblue" > Spring Web </span>
- <span style="color: cornflowerblue" > Spring Data - JPA </span>
- <span style="color: cornflowerblue" > JUNIT Test </span>
- <span style="color: cornflowerblue" > Lombok </span>
- <span style="color: cornflowerblue" > Swagger </span>
- <span style="color: cornflowerblue" > H2DB </span>
- <span style="color: cornflowerblue" > Spring Boot DevTools </span>



<br/>

 - <span style="color: cornflowerblue" > Customer </span>
 - <span style="color: cornflowerblue" > Loan </span>
 
<br/>

> <h3>Customer</h3>
This service is created for getting Customer's personal informations and store this informations in a database.
<br/><br/>

> <h3>Loan</h3>
This service is created for making necessary calculations and making a decision about loan applications. Loan Service get the customer's monthly income and mobile phone number from Customer Service. This service also sends the automatic SMS to customer's mobile phone.
> NOTE: Since the Twilio account is a trial account, all the entries for phoneNumber in Customer Service +9005358891025 this number has to be used.
<br/><br/>


<br/>

## Customer Service

###### Server port : 8080
###### Service url : http://localhost:8080
###### Used Database : H2 Database

<br/>

## Loan Service

###### Server port : 8081
###### Service url : http://localhost:8081
###### Used Database : H2 Database



## Swagger Open Api (Customer Service)

###### Url : http://localhost:8080/swagger-ui/index.html

| HTTP Verb |                                                   |
|-----------|---------------------------------------------------|
| POST      | /api/v1/customer                                  |
| PUT       | /api/v1/customer/{id}                                   |
| DELETE    | /api/v1/customer/{id}                |
| GET       | /api/v1/customer       |
| GET       | /api/v1/customer/{id}  |

<br/>

## Swagger Open Api (Loan Service)

###### Url : http://localhost:8081/swagger-ui/index.html

| HTTP Verb |                                           |
|-----------|-------------------------------------------|
| POST      | /api/v1/loan                              |
| GET       | /api/v1/customer/{id}              |


<br/>

