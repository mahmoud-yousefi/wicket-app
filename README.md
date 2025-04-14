To include instructions on how to log in, you can extend the documentation by adding a **"Login Process"** section. Here's how you can integrate it into your existing instructions:

---

## How to Run the Application

1. **Build the Project**:
   Run the following command to build the project:
   ```bash
   mvn clean install
   ```

2. **Run the Application**:
   Start the application using the embedded Tomcat server:
   ```bash
   mvn tomcat7:run
   ```

3. **Access the Application**:
   Once the application is running, open your browser and navigate to:
   ```
   http://localhost:8080/wicket-app
   ```

   To see front-end using React, navigate to:
   ```
   http://localhost:8080/wicket-app/front-end
   ```

---

## Login Process

1. **Navigate to the Login Page**:
   - If you are not automatically redirected to the login page, navigate to:
     ```
     http://localhost:8080/wicket-app/login
     ```

2. **Enter Credentials**:
   - Use the following default credentials to log in:
     - **Username**: `admin`
     - **Password**: `password`