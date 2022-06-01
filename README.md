# Spring Security Custom Authentication POC
### Multiple authentication providers

```mermaid
sequenceDiagram
    participant client
    participant server
    participant database
    client->>server: sends a GET request to /login with headers "username", "password"
    server->>database: get user with that username
    database->>server: returns the user entity
    server->>server: checks if password matches
    server->>database: if password matches saves a "one time token" (OTP) to the database
    server->>client: Status code of the operation
    client->>server: sends a GET request to /login with headers "username" and "otp"
    server->>database: check if such OTP entity (with that username and otp values) exist
    database->>server: returns entity if found
    server->>server: if OTP found, generates a new authorization token, saves it in memory
    server->>client: returns response with generated token as "Authorization" header
    client->>server: send GET request to a `/hello` resource with received token in "Authorization" header
    server->>client: return the resource (in this case "Hello!")
```

As you can see the step where OTP is saved to the database is somewhat incomplete in a sense that
OTP token is saved to DB but not communicated to the client. It would have to be communicated through
some other channel (e.g. SMS) which is not implemented as part of this POC.
<br/>
To go through the flow the developer would need to manually look up OTP in the database after the first successful
GET request to `/login` with "username" and "password"