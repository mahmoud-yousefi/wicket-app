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

## Run with Docker

1. **Build the Docker Image**:
   After building the project with Maven, create a Docker image for the application:
   ```bash
   docker build -t wicket-app .
   ```

2. **Run the Docker Container**:
   Start the application in a Docker container:
   ```bash
   docker run -d -p 8080:8080 --name wicket-app-container wicket-app
   ```

3. **Access the Application**:
   Open your browser and navigate to:
   ```
   http://localhost:8080/wicket-app
   ```

   To see front-end using React, navigate to:
   ```
   http://localhost:8080/wicket-app/front-end
   ```

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