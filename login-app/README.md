# Simple Login App (HTML/CSS/JS + Spring Boot + MySQL)

## Structure
```
login-app/
├── frontend/          # HTML, CSS, JS - static site
│   ├── index.html
│   ├── style.css
│   └── script.js
├── backend/           # Spring Boot REST API
│   ├── pom.xml
│   └── src/main/java/com/example/loginapp/
│       ├── LoginAppApplication.java
│       ├── controller/AuthController.java
│       ├── entity/User.java
│       ├── repository/UserRepository.java
│       ├── dto/LoginRequest.java, ApiResponse.java
│       └── config/CorsConfig.java
└── database/
    └── schema.sql
```

## Run locally

1. Create the MySQL database:
   ```
   mysql -u root -p < database/schema.sql
   ```
2. Set DB credentials as environment variables (or edit application.properties defaults):
   ```
   export DB_URL=jdbc:mysql://localhost:3306/logindb
   export DB_USERNAME=root
   export DB_PASSWORD=yourpassword
   ```
3. Run the backend:
   ```
   cd backend
   ./mvnw spring-boot:run
   ```
4. Register a test user:
   ```
   curl -X POST http://localhost:8080/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{"username":"testuser","password":"test1234"}'
   ```
5. Open `frontend/index.html` in a browser (or serve it with any static server) and log in with `testuser` / `test1234`.

See the deployment guide in chat for taking this to production.
