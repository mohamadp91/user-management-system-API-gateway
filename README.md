## User management system API gateway
Implement an API gateway that is the single entry point for all clients.
the API gateway receives user requests and handles them. 

good luck.
___
## Curl endpoints
 To get all users : 
 
 `curl -X GET -G http://localhost:8080/users`
 
 To add a user :
 
 `curl  -H "Content-type":"application/json" -X POST -d '{"firstName":"ali","lastName":"ahmadi","emailAddress":"hello@gmail.com"}' http://localhost:8080/users`
 
 To get a user by id :
 
 `curl -X GET -G http://localhost:8080/users/1`
 
 To delete a user by id:
 
 `curl -X DELETE -G http://localhost:8080/users/1`