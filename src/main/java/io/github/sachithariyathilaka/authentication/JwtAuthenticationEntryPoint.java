package io.github.sachithariyathilaka.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.Serializable;

/**
 * Provides the functionalities for handle exceptions while jwt bearer token is not acceptable.
 *
 * @author  Sachith Ariyathilaka
 * @version 1.0.0
 * @since   2024/03/16
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    /**
     * Override method to filter web requests with JWT bearer token
     *
     * @param   request the http servlet request
     * @param   response the http servlet response
     * @param   authException the authentication exception
     *
     * @throws  IOException the io exception
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
