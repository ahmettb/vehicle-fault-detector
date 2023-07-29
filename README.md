
# Vehicle Defect Log

Vehicle Error Log Application is an application for logging errors and faults for vehicles. With this application, vehicles defects and defect images can be save.

## Technologies Used

- Spring Boot
- Spring Security(JWT Token)
- PostgreSQL
- Hibernate
    
## Features

- Role-based authentication
- User login/signup
- Vehicle defect save.
- Marking errors in pictures according to locations.


  
## Installation

 - Clone project repository
```java
git clone https://github.com/ahmettb/vehicleErrorApp.git

```

- Edit application.properties

```java
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.show-sql=true
spring.datasource.driver-class-name=org.postgresql.Driver
logging.level.org.springframework.security=INFO
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jackson.serialization.fail-on-empty-beans=false
cvqs.app.jwtSecret=yoursecret
cvqs.app.jwtExpirationMs=9990000
spring.servlet.multipart.enabled=true 
spring.servlet.multipart.maxFileSize=1000000KB
spring.servlet.multipart.maxRequestSize=1000000KB
```
-Run the project
  
## Screenshots

Sign Page
![Uygulama Ekran Görüntüsü](https://i.hizliresim.com/mgaizth.png 
)

Login Page
![Uygulama Ekran Görüntüsü](https://i.hizliresim.com/ttpxugm.png 
)

Defect Point Image
![Uygulama Ekran Görüntüsü](https://i.hizliresim.com/m0ybfn4.png 
)

