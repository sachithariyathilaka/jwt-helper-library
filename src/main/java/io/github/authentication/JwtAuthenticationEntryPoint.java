package io.github.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.Serializable;

/**
 * @class JwtAuthenticationEntryPoint
 * @description This class is used to handle exceptions while jwt bearer token is not acceptable
 * @implements AuthenticationEntryPoint, Serializable
 *
 * @author Sachith Ariyathilaka
 * @version 1.0.0
 * @date 2024/03/16
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    /**
     * @method commence
     * @description This is an override method to filter web requests with JWT bearer token
     *
     * @param request the http servlet request
     * @param response the http servlet response
     * @param authException the authentication exception
     *
     * @throws IOException the io exception
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
