package com.ironhack.edgeservice.security;

public class Constants {

    public static final String LOGIN_URL = "/users/sign-in";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SECRET_KEY = "SecretKeyToGenJWTs";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 days
}
