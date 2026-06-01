Assessment 2
Part A – Wiki Administrator Login
Marks for Assessment 2:
Part A - 20% (this submission)
Part B – 35%
Due Dates: See Subject Outline in Moodle
Overview
The purpose of this assessment is to ensure that you can correctly set up a Spring Boot application
and implement the View and Control parts of an MVC application.
Assessment Requirements
In this first submission you will only create the first stage for your Wiki – a login GUI that
authenticates the user’s username and password. That authentication will not involve a database –
you only need to hard code in 1 acceptable username and 1 acceptable password.
In Part B you will create:
• A database that stores articles and admin details
• A wiki interface for viewing articles
• An admin interface for back-end administration
• CRUD capabilities (Create, Read, Update, and Delete)
The Register/Login screen can look like this. Consider this the bare minimum. A more attractive and
instructive interface would be preferred. 

Assessment Tasks
Use Spring Boot to develop your code
a) Create login screen and error pages that are presented to the web client for
authentication of their Username and Password.
b) When a user submits the Username and Password through form, check to see if they
match your hardcoded acceptable username and password. These should be your first
name in lower case and 123 e.g.
String user = "barun";
String pwd = "123";
c) If the correct username and password are provided, send them a welcome message,
otherwise send an error message to the user.
Code comments
Add comments throughout your Java code to show that you understand the code that you are
submitting. There’s no need to be repetitive when there are near-duplicate lines, but every Java
command should be explained at least once.
Include your own name and student ID at the top of all files that you create (e.g., .java, .html, .css)

In essence, in a Bachelor Of I.T. students own words... to explain this project:

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