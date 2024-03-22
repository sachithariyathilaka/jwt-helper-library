package io.github.sachithariyathilaka.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Authentication model for JWt request.
 *
 * @author  Sachith Ariyathilaka
 * @version 1.0.0
 * @since   2024/03/16
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JwtRequest implements Serializable {
    private String username;
    private String password;
}
