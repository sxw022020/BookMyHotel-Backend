package com.haileysun.bookmyhotel.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class Authority {
    private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String authority;

    /**
     * Getters and setters
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    // Empty constructor
    /**
     * when Spring creates an instance of a class,
     * it first creates an empty object using the default constructor and
     * then populates its properties using setters, constructors or fields injection.
     *
     * If you don't provide an empty constructor,
     * Spring won't be able to create an instance of your class using the default constructor,
     * and you'll get a runtime error.
     */
    public Authority() {};

    // Builder Pattern for constructor
    // `private` --> only Builder can access it, outside caller cannot access it
    private Authority(Builder builder) {
        this.username = builder.username;
        this.authority = builder.authority;
    }

    public static class Builder {
        @JsonProperty("username")
        private String username;
        @JsonProperty("authority")
        private String authority;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setAuthority(String authority) {
            this.authority = authority;
            return this;
        }

        public Authority build() {
            return new Authority(this);
        }
    }
}