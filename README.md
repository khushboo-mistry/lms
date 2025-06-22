# lms
Library Management System

Java: 1.8
Spring boot: 2.2.6_RELEASE
Junit: 4 
Swagger: 2
Endpoint: [[local](http://localhost:8077/swagger-ui.html#/)](http://localhost:8077/swagger-ui.html)
DB Schema name: lms

The application is service the purpose for the RESTful api expose with swagger documatation. The image of swagger ui is attached below. There are mainly 3 controller books(for book management), users(for user management), booksIssue(for issue management and book availability management). 


Steps to run the application:
[I am doing in Eclipse IDE, as I have multiple JDK in my laptop]

1. mvn clean compile
2. Run the main class in debug/run mode
3. The application is up ask for spring security based user/password which are userName: **khushboo**, password: **coachbar123**
4. Once the security credential given one can access the Swagger document and all api will goes through the filter of the Basic Authntication and rest will be as per API defination.

![image](https://github.com/user-attachments/assets/2c4fbf60-fcf6-4417-bf35-2ad3fca8995d)

![image](https://github.com/user-attachments/assets/0c0659a2-b1fa-46d6-bca6-e038e55c58dd)
