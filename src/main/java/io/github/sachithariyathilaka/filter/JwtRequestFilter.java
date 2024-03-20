package io.github.sachithariyathilaka.filter;

import io.github.sachithariyathilaka.enums.ClaimType;
import io.github.sachithariyathilaka.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @class JwtRequestFilter
 * @description This class is used to filter web requests with the JWT token
 * @extends OncePerRequestFilter
 *
 * @author Sachith Ariyathilaka
 * @version 1.0.0
 * @date 2024/03/16
 */
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    /**
     * @constructor JwtRequestFilter
     * @description This the argument constructor for JwtRequestFilter class
     *
     * @param jwtTokenUtil       the jwt token util
     * @param userDetailsService the user details service
     */
    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * @method doFilterInternal
     * @description This is an override method to filter web requests with JWT bearer token
     *
     * @param request the http servlet request
     * @param response the http servlet response
     * @param filterChain the filter chain
     *
     * @throws ServletException the servlet exception
     * @throws IOException the io exception
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            token = requestTokenHeader.substring(7);

            try {
                username = (String) jwtTokenUtil.getClaimFromToken(ClaimType.USERNAME, token);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get jwt token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT token has expired");
            }
        } else
            logger.warn("JWT token does not begin with bearer string");

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
