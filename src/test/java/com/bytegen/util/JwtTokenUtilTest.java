package com.bytegen.util;

import com.bytegen.enums.ClaimType;
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
 * @class       JwtTokenUtilTest
 * @description Unit test the functionalities of JWTTokenUtil class using JUnit
 *
 * @author      Sachith Ariyathilaka
 * @version     1.0.0
 * @date        2024/03/11
 */
public class JwtTokenUtilTest {
    private static JwtTokenUtil jwtTokenUtil;
    private static UserDetails userDetails;
    private HashMap<String, Object> claims;
    private static String username;
    private String token;

    /**
     * @method      init
     * @description Before class method of the JWT token util test
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
     * @method      initGenerateTokenTest
     * @description Before method of the generate token test case
     */
    @Before
    public void initGenerateTokenTest() {
        claims = new HashMap<>();
        claims.put("Username", username);
    }

    /**
     * @method      generateTokenTest
     * @description Test case for the generate token function
     */
    @Test
    public void generateTokenTest() {
        Assert.assertNotNull(jwtTokenUtil.generateToken(claims, username));
        Assert.assertNull(jwtTokenUtil.generateToken(null, null));
    }

    /**
     * @method      intGetClaimFromTokenTest
     * @description Before method of the get claim from token test case
     */
    @Before
    public void intGetClaimFromTokenTest() {
        initGenerateTokenTest();
        token = jwtTokenUtil.generateToken(claims, username);
    }

    /**
     * @method      getClaimFromTokenTest
     * @description Test case for the get claim from token function
     */
    @Test
    public void getClaimFromTokenTest() {
        Assert.assertNotNull(jwtTokenUtil.getClaimFromToken(ClaimType.USERNAME, token));
        Assert.assertNull(jwtTokenUtil.getClaimFromToken(ClaimType.USERNAME, null));

        Assert.assertNotNull(jwtTokenUtil.getClaimFromToken(ClaimType.EXPIRATION_DATE, token));
        Assert.assertNull(jwtTokenUtil.getClaimFromToken(ClaimType.EXPIRATION_DATE, null));
    }

    /**
     * @method      intValidateTokenTest
     * @description Before method of the validate token test case
     */
    @Before
    public void intValidateTokenTest() {
        intGetClaimFromTokenTest();
    }

    /**
     * @method      validateTokenTest
     * @description Test case for validate token function
     */
    @Test
    public void validateTokenTest() {
        Assert.assertTrue(jwtTokenUtil.validateToken(token, userDetails));
        Assert.assertFalse(jwtTokenUtil.validateToken(null, userDetails));
    }

    /**
     * @method      intIsTokenExpiredTest
     * @description Before method of the JWT token expiration validation test case
     */
    @Before
    public void intIsTokenExpiredTest() {
        intGetClaimFromTokenTest();
    }

    /**
     * @method      isTokenExpiredTest
     * @description Test case for JWT token expiration validation test case
     */
    @Test
    public void isTokenExpiredTest() {
        Assert.assertFalse(jwtTokenUtil.isTokenExpired(token));
        Assert.assertTrue(jwtTokenUtil.isTokenExpired(null));
    }
}