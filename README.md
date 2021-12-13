# Final Project

This project is more open-ended than the midterm project. Use this as an opportunity to challenge yourself, learn new things.

## Requirements

Your project must meet all of the requirements below:

1. Include a microservices Java/Spring Boot back-end and an Angular frontend.
2. Include at least 2 SQL database tables.
3. Include at least 4 services plus at least 1 edge service.
4. Include at least 1 GET, POST, PUT/PATCH, and DELETE route.
5. Include adequate and complete documentation.
6. Include at least 1 technology, tool, framework, or library that has not been covered in class


## Table of Contents
  1. [ Diagrams](#---diagrams)
  2. [How to Use](#---how-to-use)


## 📊 Diagrams
[Microservices Diagram](/diagrams/microservices-diagram.png)

[Use Case Diagram](/diagrams/use-case-diagram.png)

## 💻 How to Use
First of all, **run the database script included in the project** in MySQL Workbench. You can find the scripts [here](/backend/resources/). This is where all the data will be persisted.

Then, you will have to **run the services**. The fastest way to do so is to run all services through the console. The easiest way to do that **requires having Maven installed** in your computer. You can **follow [this tutorial](https://maven.apache.org/install.html)**. 

**Having Maven installed**, go to the service root directory, open the terminal of your choice (cmd, Powershell or git bash) and run the following command:
`mvn spring-boot:run`

If you **don't have Maven installed**, run the service Application file. It will usually be called `<ServiceName>Application.java`. For example, `EurekaServiceApplication.java`.

The appropriate order to run every service is as follows:
1. Run the [Eureka server service](/backend/eureka-service)
2. Run the three proxy services: [article service](/backend/article-service), [discount service](/backend/discount-service) and [novelty service](/backend/novelty-service)
3. Run the [edge service](/backend/edge-service)

Finally, **run the frontend**. This requires having both [npm](https://nodejs.org/es/) and [Angular](https://angular.io/start) installed. npm will take care of the necessary dependencies for you.

Go to the root of the [Angular project](/frontend-app) and execute the following command in the terminal:
`npm install`.
This will install the necessary node modules. Then, execute:
`ng serve`.
This will run the frontend in [localhost:4200](http://localhost:4200/) by default. There, you will find the application running.
 
---


## ♥️ Thanks
This would not be possible without the support and dedication of the teacher and TAs. Thank you!
### IH ES WDPT - JUN21 - BECAS SANTANDER TECH
