package com.bytegen.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @class JwtRequest
 * @description This class is used to authentication model for JWt request
 * @implements Serializable
 *
 * @author Sachith Ariyathilaka
 * @version 1.0.0
 * @date 2024/03/16
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JwtRequest implements Serializable {
    private String username;
    private String password;
}
