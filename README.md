# Assessment 2 - README

****YOU MAY NEED TO UPDATE THE DATABASE IN ORDER TO GET THE LOGIN WORKING****
****WHICH CAN BE ACHIEVED BY REDO ADMIN USER PASSWORD HASH AND ENSURE THAT H2-Console****
****UPDATES isAdmin = true*****

Michael Kalaf Bachelor of I.T. Project... with messy, overly commented comments intentionally, 
A - for me to learn, by writing it to myself
B - to explain it to tutors/anybody else that may view it
C - so it intentionally does not sound like just another A.I. produced readme. 

So this readme is intentionally more article and less readme, intentionally.

In essence, this Spring Boot project follows the MVC (Model View Controller) architecture pattern and separates different responsibilities into different layers.

Controller – Maps and Directs
The Controller handles requests from the browser and directs traffic through the application. It maps URLs using annotations such as @GetMapping and @PostMapping, receives browser input, sends tasks to the Service layer, and returns the correct page or data back to the user.

Model – Database Structure and Entities
The Model represents the data structure of the application. This includes entities, database tables, relationships and object design. For example, Person, Article, and Category are models that define what data exists and how it relates together (such as one-to-many or many-to-one relationships). The Model assists by carrying data between layers and eventually into the view.

A typical application flow looks like this:

Browser Input → Controller → Service → Repository (CRUD/Database) → Service → Controller → Model → Thymeleaf → Browser Output

In this sense, the Model helps carry the information back into the view layer so that dynamic content can be displayed.

Service – Business Logic and Coordination
The Service layer handles business logic and coordinates communication between the Controller and Repository. It is where rules, validation, password hashing, sanitisation, and processing occur before data is saved or returned.

Repository – CRUD and Database Access
The Repository layer handles CRUD operations (Create, Read, Update, Delete) and communicates with the database through Spring Data JPA. Spring can automatically generate queries using naming conventions such as findByUsername() or findByCategoryName(), which can feel like Spring’s “mystery parser.”

Thymeleaf Templates – Dynamic HTML Rendering
Thymeleaf converts Spring data into dynamic HTML pages. Instead of relying heavily on JavaScript to manually inject content, Spring can pass data into Thymeleaf templates, allowing dynamic pages to be rendered server-side. JavaScript can still be included where required for extra functionality.

H2 Database – Data Storage
The H2 database stores the application data. In development, data persistence depends on whether the database is configured as in-memory or file-based. If data becomes overwritten or missing, the H2 Console can be used to inspect or manually update records, such as inserting a BCrypt-hashed password for the administrator account (1 / 1) if required for testing.

For this project I also included a lot of seperated, long worded CSS files for styles, some Javascript for stronger validation as well as an image carousel to demonstrate how easy it is to apply.

I also intentionally added additional Create Category, Create Article, Article, Category, in the Navbar to demonstrate different components, and how easy Spring Boot and Thymeleaf makes it to navigate through a project.

Security is as per Assessment requirments and displays password hashing. 

I also ensured there was a Admin and a Users to create the real world feel of a Users that can access their own articles and categories, where as the Admin can access, edit and delete all Articles and Categories. This is handled through a isAdmin and enforced through the session logic. A perfect example of session usage, invalidate on logout, and ensuring that redirect is applied so as to avoid duplicate form submissions through applying GET in place of POST operations.
