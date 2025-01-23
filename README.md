NOTE: The "API Calls" File in "Documentation" Folder provides all the API Calls for CRUD Operations and other methods.

Project Working LOOM Video :https://www.loom.com/share/28b7d0ce7e9740a19410371d1c4e346b?sid=8cd7498b-ccf4-4be1-905e-f59e6a103bbc


A well-designed Java Spring Boot application with the following features:

1. **Entities:**
   - Two main entities, **Student** and **Course**, are used to manage students and their course associations.
   - Each student has attributes like `name`, `email`, and an associated `Course`. The email must be unique, while the names can be duplicates.

2. **CRUD Operations:**
   - The project supports full CRUD (Create, Read, Update, Delete) operations for both **Student** and **Course** entities via respective **API Endpoints**.
   - The `CourseController` and `StudentController` classes provide a clear and organized approach to managing these entities through APIs.

3. **Course Association Management:**
   - The project includes features to assign or dissociate courses for students. Students can be associated with or dissociated from a course using `PUT` APIs.
   - Course deletions are restricted if students are associated with the course, ensuring data integrity. The system forces you to dissociate all students from a course before deleting it.

4. **Role-Based Authorization (RBA):**
   - The project uses **Spring Security** to implement **Role-Based Authorization** with two roles: `ADMIN` and `USER`.
   - **Admin** has complete access to all CRUD operations and can modify data, while **User** can only view the data.
   - The `SecurityConfig` class configures access control, ensuring only users with the proper roles have access to specific endpoints.
   - Passwords are encrypted using **BCryptPasswordEncoder** for secure authentication.

5. **Database:**
   - The project integrates **MySQL** as the database for persisting student and course data.
   - Data relationships between students and courses are handled using proper foreign key relationships.

6. **API Documentation:**
   - Detailed **API documentation** is included in the `documentation` package, explaining how to make API calls via Postman for testing CRUD operations and course associations.

### **Strengths:**
   - **Code Clarity:** The separation of concerns between controllers, services, and repositories ensures that the code is clean and maintainable.
   - **Security:** Good use of Spring Security to implement authorization roles, protecting endpoints from unauthorized access.
   - **Validation:** Use of proper validations like unique email for students ensures data integrity.
   - **Modularity:** The services and controllers are well-structured for each entity, making the system easily extendable.


## **Steps to Run the Project Using IntelliJ IDEA:**

1. **Install Prerequisites:**
   - **Java JDK 17** or higher.
   - **MySQL** database.
   - **IntelliJ IDEA** with the **Spring Boot** plugin.

2. **Clone the Project:**
   - Open IntelliJ IDEA.
   - Select **File > New > Project from Version Control**.
   - Enter the repository URL to clone the project.

3. **Import the Project:**
   - Once the project is cloned, IntelliJ will recognize it as a Maven/Gradle project.
   - Follow the prompts to import the project dependencies by selecting the **Maven/Gradle** settings and syncing the project.

4. **Configure the Database:**
   - Update the `application.properties` (or `application.yml`) file with your local MySQL configuration:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
     spring.datasource.username=YOUR_MYSQL_USERNAME
     spring.datasource.password=YOUR_MYSQL_PASSWORD
     ```
   - Ensure that the database is created and running before launching the application.

5. **Build the Project:**
   - Run the **Maven/Gradle build** using the terminal or by right-clicking on the `pom.xml/build.gradle` file and selecting **Run**.

6. **Run the Spring Boot Application:**
   - Right-click on the **main class** (`SciqusTaskApplication`) and select **Run 'SciqusTaskApplication'** to start the Spring Boot application.
   - The application will start on the default port (e.g., `8080`).

7. **Testing API Calls:**
   - Use **Postman** to test the API endpoints provided in your documentation package.
   - Register users and test different role-based access using the login mechanism.
   - Test CRUD operations for **Student** and **Course** entities.

8. **Access the Application:**
   - Open a browser or Postman and hit the API endpoints (e.g., `http://localhost:8080/api/students`, `http://localhost:8080/api/courses`) to interact with the application.


In the below images, some students have no email and some students have no course allocated. This is to show the course dissociation as mentioned in the task.

SCREENSHOTS:
![Screenshot 2025-01-23 183813](https://github.com/user-attachments/assets/3f06dfd7-58ec-401e-a2be-c1ce94069bac)

![Screenshot 2025-01-23 183808](https://github.com/user-attachments/assets/5aa27f93-dc10-45e4-97b4-f78faa9dc774)

![Screenshot 2025-01-23 183728](https://github.com/user-attachments/assets/670b5bb5-7631-41ad-b8ce-9883f6117621)

![Screenshot 2025-01-23 183650](https://github.com/user-attachments/assets/a0f88040-ce41-462e-b4b5-301fa76e7442)

![Screenshot 2025-01-23 183958](https://github.com/user-attachments/assets/c42edefb-64a5-40b5-b2f7-1d07ed8668a7)

![Screenshot 2025-01-23 183950](https://github.com/user-attachments/assets/da7887ed-6eb9-42ff-a0cd-757a42ebcad5)
