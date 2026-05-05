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
