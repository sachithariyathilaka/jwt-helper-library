# JWT Helper
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
    * https://medium.com/@sachithariyathilaka

* ### Release Note
  * Release: 1.0.0 
  * Date: 2024/03/16
  * Changes: Implements the following module. <br><br>

    * Util
         * JWTTokenUtil
           * generateToken: Generate JWT token using username and claim map.
           * getClaimFromToken: Retrieve the selected type of claim from jwt token.
           * validateToken: This is used to validate jwt token.
           * isTokenExpired: This is used to check the jwt token expiration. <br><br>

    * Resource
      * JwtRequest: This class is used to authentication model for JWt request. <br><br>
    
    * Filter
      * JwtRequestFilter: This class is used to filter web requests with the JWT token. <br><br>
    
    * Authentication
      * JwtAuthenticationEntryPoint: This class is used to handle exceptions while jwt bearer token is not acceptable. <br><br>

    * Enums
      * ClaimTypeEnum: This is used to identify the claim type from the claim map. This is used to getClaimFromToken method in JWTTokenUtil class.
        * USERNAME
        * EXPIRATION_DATE

