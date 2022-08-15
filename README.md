### Getting started
In order to run the application with docker-compose, ensure that you have docker-compose and mvn installed on your machine.

After running the command below docker will build two backend services and postgres database.

Database will be initialised by spring application service, it will create schema and inject data (via liquibase).

From root directory run the next command:
```
      - mvn package
      - docker-compose up -d
```

### Startup
On startup application initialises the database and do injection.
After a startup, you will have next users in the database:

Admin:
```
    name: Tom
    surname: Robins
```

Users:
```
    name: Rob
    surname: Marko

    name: Mister
    surname: Bean
    
    name: Spider
    surname: Man
    
    name: Check
    surname: Jerry
```


### Functionalities
The application allows you as an admin to do next:
```
- login
- create users /api/accounts
- filter users /api/accounts/filter
- update users /api/accounts/{id}
- create addresses /api/addresses
- create services /api/services
- get your services /api/services/client
```

The application allows you as a user to do next:
```
- login
- get your services /api/services/client
```

### Authentication
After login use generated token in "authorisation" header for each request