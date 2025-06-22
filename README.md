# lms
Library Management System

- Java: 1.8
- Spring boot: 2.2.6_RELEASE
- Junit: 4 
- Swagger: 2
- Endpoint: [[local](http://localhost:8077/swagger-ui.html#/)](http://localhost:8077/swagger-ui.html)
- DB Schema name: lms

The application is service the purpose for the RESTful api expose with swagger documatation. The image of swagger ui is attached below. There are mainly 3 controller books(for book management), users(for user management), booksIssue(for issue management and book availability management). 


Steps to run the application:
[I am doing in Eclipse IDE, as I have multiple JDK in my laptop]

1. mvn clean compile
2. Run the main class in debug/run mode
3. The application is up ask for spring security based user/password which are userName: **khushboo**, password: **coachbar123**
4. Once the security credential given one can access the Swagger document and all api will goes through the filter of the Basic Authntication and rest will be as per API defination.

![image](https://github.com/user-attachments/assets/2c4fbf60-fcf6-4417-bf35-2ad3fca8995d)

![image](https://github.com/user-attachments/assets/0c0659a2-b1fa-46d6-bca6-e038e55c58dd)

There are 3 controllers,
And the description for each is already added at the documentation level, yet describing here too.

- Books Controller --> For management of book, the book code is generated along with primary key and the operations will be based of exposing the book code and not database primary key
![image](https://github.com/user-attachments/assets/47cd4e1d-7a14-4408-ae7e-8d8325eaf020)

- Users Controller --> For management of user, the user code is generated along with primary key and the operations will be based of exposing the book code and not database primary key
![image](https://github.com/user-attachments/assets/eb8e9d6e-35bb-4718-8d56-f9af76980722)

  
- Book Issues Controller --> For management of Issued transaction for book to user, here also the issue code is generated and tarcked with that and not with primary key. There is a BL while issueing and returning the book the book availability count is decreased and increased respectively. Along with that that is very naive fine calculation for overdued book. The test is is written fot this change for availability of book count as per issue and return is added. As most other layer are constant right now.
![image](https://github.com/user-attachments/assets/45fb3c19-5e77-4410-b592-c6eac5c645ce)



  ## Future Posibility
  - Can add more data for management of fine, tariff, maintain the book priority and tagging each versions and all
  - Can write more test cases
  - Document upgrades 

