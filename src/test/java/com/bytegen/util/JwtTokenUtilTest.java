package com.bytegen.util;

import com.bytegen.enums.ClaimType;
import com.bytegen.util.JwtTokenUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;

/**
 * @class JwtTokenUtilTest
 * @description This class is used to unit test the functionalities of JWTTokenUtil class using JUnit.
 *
 * @author Sachith Ariyathilaka
 * @version 1.0.0
 * @date 2024/03/11
 */
public class JwtTokenUtilTest {
    private static JwtTokenUtil jwtTokenUtil;
    private static UserDetails userDetails;
    private HashMap<String, Object> claims;
    private static String username;
    private String token;

    /**
     * @method init
     * @description This method is the @BeforeClass method of the JWTTokenUtilTest.
     */
    @BeforeClass
    public static void init() {

        long tokenValidityPeriod = 86400000;
        String tokeSecret = "javainuse";
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        username = "JwtTokenUtil";
        jwtTokenUtil = new JwtTokenUtil(tokenValidityPeriod, tokeSecret, signatureAlgorithm);
        userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
    }

    /**
     * @method initGenerateTokenTest
     * @description This method is the @Before method of the generateTokenTest.
     */
    @Before
    public void initGenerateTokenTest() {
        claims = new HashMap<>();
        claims.put("Username", username);
    }

    /**
     * @method generateTokenTest
     * @description This method is the test case for generateToken.
     */
    @Test
    public void generateTokenTest() {
        Assert.assertNotNull(jwtTokenUtil.generateToken(claims, username));
        Assert.assertNull(jwtTokenUtil.generateToken(null, null));
    }

    /**
     * @method intGetClaimFromTokenTest
     * @description This method is the @Before method of the getClaimFromTokenTest.
     */
    @Before
    public void intGetClaimFromTokenTest() {
        initGenerateTokenTest();
        token = jwtTokenUtil.generateToken(claims, username);
    }

    /**
     * @method getClaimFromTokenTest
     * @description This method is the test case for getClaimFromToken.
     */
    @Test
    public void getClaimFromTokenTest() {
        Assert.assertNotNull(jwtTokenUtil.getClaimFromToken(ClaimType.USERNAME, token));
        Assert.assertNull(jwtTokenUtil.getClaimFromToken(ClaimType.USERNAME, null));

        Assert.assertNotNull(jwtTokenUtil.getClaimFromToken(ClaimType.EXPIRATION_DATE, token));
        Assert.assertNull(jwtTokenUtil.getClaimFromToken(ClaimType.EXPIRATION_DATE, null));
    }

    /**
     * @method intValidateTokenTest
     * @description This method is the @Before method of the validateTokenTest.
     */
    @Before
    public void intValidateTokenTest() {
        intGetClaimFromTokenTest();
    }

    /**
     * @method validateTokenTest
     * @description This method is the test case for validateToken.
     */
    @Test
    public void validateTokenTest() {
        Assert.assertTrue(jwtTokenUtil.validateToken(token, userDetails));
        Assert.assertFalse(jwtTokenUtil.validateToken(null, userDetails));
    }

    /**
     * @method intIsTokenExpiredTest
     * @description This method is the @Before method of the isTokenExpiredTest.
     */
    @Before
    public void intIsTokenExpiredTest() {
        intGetClaimFromTokenTest();
    }

    /**
     * @method isTokenExpiredTest
     * @description This method is the test case for isTokenExpired.
     */
    @Test
    public void isTokenExpiredTest() {
        Assert.assertFalse(jwtTokenUtil.isTokenExpired(token));
        Assert.assertTrue(jwtTokenUtil.isTokenExpired(null));
    }
}