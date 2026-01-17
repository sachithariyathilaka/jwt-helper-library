# JWT Helper Library
Java maven library project for support jwt related spring security configurations.

* ### Technologies
  * Java 17
  * Spring Security 6.2.1
  * Embedded Tomcat 10.1.18
  * JWT 0.9.1
  * Lombok 1.18.28
  * JUnit 4.13.2
  * Maven

* ### Configuration
    * [Read on Medium](https://medium.com/towardsdev/jwt-helper-library-1fad8d1af0ef)

* ### Release Note
  
  * Release: 1.0.0 
  * Date: 2024/03/16
  
  * Module: Util
  * Changes
    * JWTTokenUtil
      * generateToken: Generate JWT token using username and claim map.
      * getClaimFromToken: Retrieve the selected type of claim from jwt token.
      * validateToken: This is used to validate jwt token.
      * isTokenExpired: This is used to check the jwt token expiration. <br><br>

  * Module: Resource
  * Changes
    * JwtRequest: This class is used to authentication model for JWt request. <br><br>

  * Module: Filter
  * Changes
    * JwtRequestFilter: This class is used to filter web requests with the JWT token. <br><br>

  * Module: Authentication
  * Changes
    * JwtAuthenticationEntryPoint: This class is used to handle exceptions while jwt bearer token is not acceptable. <br><br>

  * Module: Enums
  * Changes
    * ClaimTypeEnum: This is used to identify the claim type from the claim map. This is used to getClaimFromToken method in JWTTokenUtil class.
      * USERNAME
      * EXPIRATION_DATE <br><br>
      
  * Release: 1.0.1
  * Date: 2024/03/23
  * Module: JWT Helper
  * Changes
    * Configuration related bug fixed. <br><br>

  * Release: 1.0.2
  * Date: 2024/04/13
  * Module: JWT Helper
  * Changes
    * Configuration related bug fixed.
           

