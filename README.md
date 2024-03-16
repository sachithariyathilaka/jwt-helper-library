# JWT Helper
Java maven library project for support jwt related spring security configurations.

### Configuration
https://medium.com/@sachithariyathilaka

### Release Note
* ##### Date: 2024/03/16
* ##### Version: 1.0.0
* ##### Changes: Implements the following module.

  * Util
       * JWTTokenUtil
         * generateToken: Generate JWT token using username and claim map.
         * getClaimFromToken: Retrieve the selected type of claim from jwt token.
         * validateToken: This is used to validate jwt token.
         * isTokenExpired: This is used to check the jwt token expiration.
     
    ####

  * Resource
    * JwtRequest: This class is used to authentication model for JWt request.

  ####
    
  * Filter
    * JwtRequestFilter: This class is used to filter web requests with the JWT token.

  ####
    
  * Authentication
    * JwtAuthenticationEntryPoint: This class is used to handle exceptions while jwt bearer token is not acceptable.

  ####

    * Enums
        * ClaimTypeEnum: This is used to identify the claim type from the claim map. This is used to getClaimFromToken method in JWTTokenUtil class.
          * USERNAME
          * EXPIRATION_DATE

