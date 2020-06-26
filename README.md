# Spring-Security-JWT
## Spring Boot + Security + JWT + Database Authentication


This project includes the 3 Rest API Controllers:

1) RegisterUser(/registerUser) :

   This API is used to register the user in the database. The password is encrypted using the BCryptPasswordEncoder().
  
2) Authenticate(/authenticate) :

	This API will authenticate the user and return the JWT token in the response.
	
3) GetInfo(/getInfo) :

	This API is just a dummy method to get the information from the server if the user passes the valid JWT token.

	The user has to pass the JWT in the Header of the request,
	
	Key - Authorization
	
	Value - Bearer (JWT token)
