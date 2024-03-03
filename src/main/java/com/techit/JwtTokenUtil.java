package com.techit;

import com.techit.enums.ClaimType;
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
 * @record JwtTokenUtil is provided the needed util jwt token related functions
 */
public record JwtTokenUtil(@Getter long tokenValidityPeriod,
                           String tokenSecret,
                           SignatureAlgorithm signatureAlgorithm) implements Serializable {

    /**
     * Generate jwt token.
     *
     * @param claimMap the claim map
     * @param username the username
     * @return the token
     */
    public String generateToken(HashMap<String, Object> claimMap, String username) {

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
     * Gets claim from token.
     *
     * @param claimType the claim type
     * @param token     the token
     * @return the claim
     */
    public Object getClaimFromToken(ClaimType claimType, String token) {

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

    private <T> T getClaimFromToken(Claims claims, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }

    /**
     * Validate token boolean.
     *
     * @param token       the token
     * @param userDetails the user details
     * @return the boolean
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = (String) getClaimFromToken(ClaimType.USERNAME, token);
        return username.equals(userDetails.getUsername());
    }

    /**
     * Is token expired boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = (Date) getClaimFromToken(ClaimType.EXPIRATION_DATE, token);
        return expiration.before(new Date());
    }
}
