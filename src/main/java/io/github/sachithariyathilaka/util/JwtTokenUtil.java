package io.github.sachithariyathilaka.util;

import io.github.sachithariyathilaka.enums.ClaimType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @record      JwtTokenUtil
 * @description Provides the needed util jwt token related functions
 * @implements  Serializable
 *
 * @param       tokenValidityPeriod the token validity period
 * @param       tokenSecret the token secret
 * @param       signatureAlgorithm the signature algorithm
 *
 * @author      Sachith Ariyathilaka
 * @version     1.0.0
 * @date        2024/03/11
 */
public record JwtTokenUtil(@Getter long tokenValidityPeriod,
                           String tokenSecret,
                           SignatureAlgorithm signatureAlgorithm) implements Serializable {

    /**
     * @method      generateToken
     * @description Generate jwt token
     *
     * @param       claimMap the claim map
     * @param       username the username
     *
     * @return      the token
     */
    public String generateToken(HashMap<String, Object> claimMap, String username) {

        if (claimMap == null || claimMap.isEmpty() || username == null || username.isEmpty())
            return null;

        Map<String, Object> claims = new HashMap<>(claimMap);
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityPeriod))
                .signWith(signatureAlgorithm, tokenSecret).compact();
    }


    /**
     * @method      getClaimFromToken
     * @description Gets claim from token
     *
     * @param       claimType the claim type
     * @param       token     the token
     *
     * @return      the claim
     */
    public Object getClaimFromToken(ClaimType claimType, String token) {

        if (token == null || token.isEmpty())
            return null;

        Claims claims = Jwts
                .parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody();

        return switch (claimType) {
            case USERNAME -> getClaimFromToken(claims, Claims::getSubject);
            case EXPIRATION_DATE -> getClaimFromToken(claims, Claims::getExpiration);
        };
    }

    /**
     * @method      getClaimFromToken
     * @description Gets specific claim from the claim list
     *
     * @param       claims the claim list
     * @param       claimsResolver     claim filter function
     *
     * @return      the claim
     */
    private <T> T getClaimFromToken(Claims claims, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }

    /**
     * @method      validateToken
     * @description Validate token boolean
     *
     * @param       token       the token
     * @param       userDetails the user details
     *
     * @return      the boolean
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = (String) getClaimFromToken(ClaimType.USERNAME, token);

        if (username == null || username.isEmpty())
            return false;

        return username.equals(userDetails.getUsername());
    }

    /**
     * @method      isTokenExpired
     * @description Validate token expiration validation
     *
     * @param       token the token
     *
     * @return      the boolean
     */
    public boolean isTokenExpired(String token) {
        if (token == null || token.isEmpty())
            return true;

        final Date expiration = (Date) getClaimFromToken(ClaimType.EXPIRATION_DATE, token);
        return expiration.before(new Date());
    }
}
