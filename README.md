# File Manager
### Wave 1

Functionalities:
1. User can add a file to a specific location (URI)
2. User can delete a file from a specific location
3. User can move a file to a specific location

Every business logic **MUST** be wrapped in its own Service class. 
Every service **MUST** respect SOLID principles!!!!!

### Wave 2
Implement Spring Boot

Requirements:
1. Use Spring Starter Data JPA
2. Use Spring Starter Web
3. Use Lombok

**LEARN HOW TO INJECT FUCKING ENVIRONMENT PROPERTIES FROM application.yml PORCODIO**

Functionalities:
1. Files locations persisted to the DB
2. Users persisted in the DB
3. Implement users CRUD ops in RestController [UsersAPI](http://localhost.8080/users)
4. Implement files CRUD ops in RestController [FileManagerAPI](http://localhost.8080/files)

#### Testing:
MUST respect a test coverage of at least [90, 90, 90]
MUST not introduce SQLInjections

### Wave 3
Implement and manage basic Spring Boot configurations (through Spring configuration processing (application.yml)) </br>
_NOTE: Keep in mind that you should implement spring annotation processing._ 

Requirements:
1. Users can configure automatic file ops (auto file move when file name or file extension detected in a folder), should also be persisted as a configuration in the db
2. Must have three spring profiles (test, integ, prod)
3. Integ profile must persist in the {user.dir} file location ({user.dir}/integ-tests/)
4. Test profiles must be kept black
5. Prod profiles must be kept blank

