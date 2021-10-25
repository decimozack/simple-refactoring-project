# Simple Refactoring project

This is a simple refactoring kata written in Java. There are code smells that you will be able to identify and start
refactoring. Feel free to clone this project to practice some basic refactoring. Have fun and happy refactoring.

## What is this project about
This project is about a simple Point-of-sale (POS) system for a drink stall. When the order is provided to the system,
the system will process the order and output the OrderDetail containing the total price, receipt number and voucher for customer.

### Project Structure
Here are some basic information of the packages that we have in this project:

* dao
  - Contains Data Access Objects. As this is a simple refactoring project, we have initialised the data directly in the classes under this
    package. If you wish, you can create a database table and update the implementations in this package to connect and retrieve the data from database.
* dto
  - Contains all the Data Transfer Objects
* models
  - Contains the domain models that we are using for this application. Eg. Customer, Order and many more.
* services
  - Contains business logics divided into their respective Service class based on the domain model.

## Getting Started
To be able to run the project, please ensure you have the things listed in the **Prerequisites** section installed.
### Prerequisites
* Java 11
* Your favourite IDE (Intellij IDEA recommended)
* Gradle