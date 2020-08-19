package io.recko.network.auth.model;

/**
 * Response for the Authentication and later this token need to be used for Authorization
 */
public class AuthResponse {
    private String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
