\# CareerConnect – Job Portal



CareerConnect is a simple web-based Job Portal developed using Java, Servlets, JDBC and MySQL.



The platform connects candidates with recruiters. Candidates can create accounts, view available jobs, apply for jobs and track their applications. Recruiters can create accounts, post job opportunities and view applicants.



\## Features



\### Candidate



\* Candidate registration

\* Candidate login

\* View available jobs

\* Apply for jobs

\* View submitted applications

\* Check application status



\### Recruiter



\* Recruiter registration

\* Recruiter login

\* Post new job opportunities

\* View applicants



\## Technologies Used



\* HTML

\* CSS

\* Java

\* Java Servlets

\* JDBC

\* MySQL

\* Apache Tomcat 10

\* Git \& GitHub



\## Project Architecture



```text

HTML/CSS

&#x20;  ↓

Java Servlets

&#x20;  ↓

DAO Classes

&#x20;  ↓

JDBC

&#x20;  ↓

MySQL Database

```



\## Project Structure



```text

CareerConnect/

│

├── index.html

├── login.html

├── register.html

├── apply.html

├── postjob.html

├── style.css

│

├── src/

│   ├── dao/

│   │   ├── UserDAO.java

│   │   ├── JobDAO.java

│   │   └── ApplicationDAO.java

│   │

│   ├── model/

│   │   ├── User.java

│   │   └── Job.java

│   │

│   ├── servlet/

│   │   ├── LoginServlet.java

│   │   ├── RegisterServlet.java

│   │   ├── PostJobServlet.java

│   │   ├── ApplyServlet.java

│   │   ├── ViewJobsServlet.java

│   │   ├── ViewApplicantsServlet.java

│   │   └── ViewApplicationsServlet.java

│   │

│   └── util/

│       ├── DBConnection.java

│       ├── TestDB.java

│       ├── JobCounter.java

│       └── ThreadDemo.java

│

└── WEB-INF/

&#x20;   └── web.xml

```



\## Database



The project uses a MySQL database named:



```text

careerconnect

```



Main tables:



\* `users`

\* `jobs`

\* `applications`

\* `candidates`

\* `recruiters`



\### Database Relationships



```text

Users

&#x20; │

&#x20; ├──────────────┐

&#x20; │              │

&#x20; ↓              ↓

Candidates    Recruiters

&#x20;                

Users ─────── Applications ─────── Jobs

```



\## Requirements



Before running the project, install:



1\. Java JDK 17 or higher

2\. MySQL Server

3\. Apache Tomcat 10

4\. MySQL Connector/J

5\. Git (optional)



\## Database Setup



Create the database in MySQL:



```sql

CREATE DATABASE IF NOT EXISTS careerconnect;



USE careerconnect;



CREATE TABLE users (

&#x20;   user\_id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   name VARCHAR(100) NOT NULL,

&#x20;   email VARCHAR(100) UNIQUE NOT NULL,

&#x20;   password VARCHAR(100) NOT NULL,

&#x20;   role VARCHAR(20) NOT NULL

);



CREATE TABLE jobs (

&#x20;   job\_id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   title VARCHAR(100) NOT NULL,

&#x20;   company VARCHAR(100) NOT NULL,

&#x20;   location VARCHAR(100),

&#x20;   salary VARCHAR(50),

&#x20;   description TEXT

);



CREATE TABLE applications (

&#x20;   application\_id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   user\_id INT,

&#x20;   job\_id INT,

&#x20;   status VARCHAR(30) DEFAULT 'Applied',

&#x20;   FOREIGN KEY (user\_id) REFERENCES users(user\_id),

&#x20;   FOREIGN KEY (job\_id) REFERENCES jobs(job\_id)

);



CREATE TABLE candidates (

&#x20;   candidate\_id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   user\_id INT,

&#x20;   skills VARCHAR(300),

&#x20;   FOREIGN KEY (user\_id) REFERENCES users(user\_id)

);



CREATE TABLE recruiters (

&#x20;   recruiter\_id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   user\_id INT,

&#x20;   company\_name VARCHAR(100),

&#x20;   FOREIGN KEY (user\_id) REFERENCES users(user\_id)

);

```



\## Database Configuration



Open:



```text

src/util/DBConnection.java

```



Configure your local MySQL credentials:



```java

private static final String URL =

&#x20;       "jdbc:mysql://localhost:3306/careerconnect";



private static final String USER = "root";



private static final String PASSWORD =

&#x20;       "YOUR\_MYSQL\_PASSWORD";

```



Replace `YOUR\_MYSQL\_PASSWORD` with your own local MySQL password.



\*\*Important:\*\* Never upload your actual MySQL password to GitHub.



\## How to Run the Project



\### Step 1 – Start MySQL



Make sure your MySQL Server is running.



\### Step 2 – Start Apache Tomcat



Open CMD and run:



```cmd

cd /d C:\\Tomcat\\apache-tomcat-10.1.60\\bin

startup.bat

```



\### Step 3 – Go to the Project Directory



```cmd

cd /d C:\\Tomcat\\apache-tomcat-10.1.60\\webapps\\CareerConnect

```



\### Step 4 – Compile the Java Files



```cmd

javac -cp "WEB-INF\\classes;C:\\Tomcat\\apache-tomcat-10.1.60\\lib\\servlet-api.jar;WEB-INF\\lib\\mysql-connector-j-26.7.0.jar" -d WEB-INF\\classes src\\model\\\*.java src\\util\\\*.java src\\dao\\\*.java src\\servlet\\\*.java

```



\### Step 5 – Open the Application



Open your browser and visit:



```text

http://localhost:8080/CareerConnect/

```



\## Main Application URLs



\### Homepage



```text

http://localhost:8080/CareerConnect/

```



\### Jobs



```text

http://localhost:8080/CareerConnect/jobs

```



\### Candidate Applications



Replace `5` with the required user ID:



```text

http://localhost:8080/CareerConnect/applications?userId=5

```



\### Recruiter Applicants



```text

http://localhost:8080/CareerConnect/applicants

```



\## Application Workflow



\### Candidate Workflow



```text

Register

&#x20;  ↓

Login

&#x20;  ↓

View Jobs

&#x20;  ↓

Select Job

&#x20;  ↓

Apply

&#x20;  ↓

Application Stored in MySQL

&#x20;  ↓

View Application Status

```



\### Recruiter Workflow



```text

Register

&#x20;  ↓

Login

&#x20;  ↓

Post Job

&#x20;  ↓

Job Stored in MySQL

&#x20;  ↓

Candidates View Job

&#x20;  ↓

Recruiter Views Applicants

```



\## Core Java Concepts Used



The project demonstrates:



\* Classes and Objects

\* Encapsulation

\* Constructors

\* Getters and Setters

\* Inheritance

\* Collections

\* Generics

\* Exception Handling

\* DAO Pattern

\* JDBC

\* Multithreading

\* Synchronization



\## Important Java Classes



\### User



Represents a candidate or recruiter.



\### Job



Represents a job opportunity posted by a recruiter.



\### UserDAO



Handles user registration and login operations.



\### JobDAO



Handles job creation and retrieving available jobs.



\### ApplicationDAO



Handles job applications.



\### DBConnection



Creates the JDBC connection with MySQL.



\### Servlets



The application uses multiple Servlets including:



\* `LoginServlet`

\* `RegisterServlet`

\* `PostJobServlet`

\* `ApplyServlet`

\* `ViewJobsServlet`

\* `ViewApplicantsServlet`

\* `ViewApplicationsServlet`



\## JDBC Integration



The project uses JDBC to communicate with MySQL.



Main JDBC components used:



\* `Connection`

\* `PreparedStatement`

\* `ResultSet`

\* `executeQuery()`

\* `executeUpdate()`



Example:



```java

PreparedStatement ps = con.prepareStatement(sql);

ResultSet rs = ps.executeQuery();

```



`PreparedStatement` is used for parameterized database queries.



\## Multithreading \& Synchronization



The project includes a multithreading demonstration using `JobCounter` and `ThreadDemo`.



Two threads increment the same counter:



```java

public synchronized void increment() {

&#x20;   count++;

}

```



The `synchronized` keyword prevents multiple threads from modifying the counter at the same time.



Expected output:



```text

Total jobs counted: 2000

```



\## Testing



The following major functionalities were tested:



\* User registration

\* Candidate login

\* Recruiter login

\* Job posting

\* Viewing available jobs

\* Applying for jobs

\* Viewing candidate applications

\* Viewing recruiter applicants

\* Database connectivity

\* Multithreading and synchronization



\## Team Members



\* \*\*Om Kushwaha\*\*

\* \*\*Mahak Sharma\*\*

\* \*\*Shivanshi Vishwakarma\*\*

\* \*\*Vishal Singh\*\*



\## Future Scope



The project can be extended with:



\* Resume upload

\* Advanced job search

\* Job filtering

\* Recruiter dashboard

\* Admin panel

\* Email notifications

\* Password encryption

\* Application status management

\* Online deployment

\* User profile management



\## Project Purpose



CareerConnect was developed as a college project to demonstrate practical implementation of Java programming, Object-Oriented Programming, Servlets, JDBC, MySQL, DAO architecture and multithreading through a functional web application.



\## GitHub Repository



GitHub:



https://github.com/kushwahaom643/CareerConnect



\## License



This project was developed for educational and academic purposes.



