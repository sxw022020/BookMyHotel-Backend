package com.haileysun.bookmyhotel.entity;

// Not an "entity"
// just for creation of token
public class Token {

    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
