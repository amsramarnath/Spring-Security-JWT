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

DB details:

CREATE TABLE `usr_mst_jwt` (
  `USRMSTJWT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USR_NAME` varchar(400) DEFAULT NULL,
  `USR_PWD` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`USRMSTJWT_ID`)
);

![RegisterUser](https://user-images.githubusercontent.com/37022051/85830851-f6fce380-b795-11ea-818e-1730c54a4989.png)


![Authenticate](https://user-images.githubusercontent.com/37022051/85830887-05e39600-b796-11ea-9611-79d097682589.png)


![GetInfo](https://user-images.githubusercontent.com/37022051/85830913-0ed46780-b796-11ea-8f4d-e276a5dc5cb5.png)
