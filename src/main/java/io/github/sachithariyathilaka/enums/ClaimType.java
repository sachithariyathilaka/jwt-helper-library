package io.github.sachithariyathilaka.enums;

/**
 * Provides the claim type from the claim map.
 *
 * @author  Sachith Ariyathilaka
 * @version 1.0.0
 * @since     2024/03/16
 */
public enum ClaimType {
    USERNAME("username"),
    EXPIRATION_DATE("expiration date");

    private final String name;

    ClaimType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
