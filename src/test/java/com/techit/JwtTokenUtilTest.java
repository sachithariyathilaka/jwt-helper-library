package com.techit;

import com.techit.enums.ClaimType;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

/**
 * @class JwtTokenUtilTest
 */
public class JwtTokenUtilTest {
    private static JwtTokenUtil jwtTokenUtil;
    private HashMap<String, Object> claims;
    private String token;

    /**
     * Init test cases
     */
    @BeforeClass
    public static void init() {

        long tokenValidityPeriod = 86400000;
        String tokeSecret = "javainuse";
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        jwtTokenUtil = new JwtTokenUtil(tokenValidityPeriod, tokeSecret, signatureAlgorithm);
    }

    /**
     * Init generate token test
     */
    @Before
    public void initGenerateTokenTest() {
        claims = new HashMap<>();
        claims.put("Username", "JwtTokenUtil");
    }

    /**
     * Generate token test
     */
    @Test
    public void generateTokenTest() {
        Assert.assertNotNull(jwtTokenUtil.generateToken(claims, "JwtTokenUtil"));
    }

    /**
     * Int get claim from token test
     */
    @Before
    public void intGetClaimFromTokenTest() {
        token = jwtTokenUtil.generateToken(claims, "JwtTokenUtils");
    }

    /**
     * Gets claim from token test
     */
    @Test
    public void getClaimFromTokenTest() {
        Assert.assertNotNull(jwtTokenUtil.getClaimFromToken(ClaimType.USERNAME, token));
        Assert.assertNotNull(jwtTokenUtil.getClaimFromToken(ClaimType.EXPIRATION_DATE, token));
    }

    /**
     * Validate token test
     */
    @Test
    public void validateTokenTest() {
    }

    /**
     * Is token expired test
     */
    @Test
    public void isTokenExpiredTest() {
        Assert.assertFalse(jwtTokenUtil.isTokenExpired(token));
    }
}