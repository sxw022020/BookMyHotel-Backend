package com.haileysun.bookmyhotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @Bean:
     * indicating that it should create and return a new instance of PasswordEncoder.
     *
     * When the Spring container starts up,
     * it will scan for all @EnableWebSecurity classes and
     * create instances of any @Bean methods that they define.
     * In this case, the Spring container will create a new instance of BCryptPasswordEncoder and
     * register it as a bean with the name passwordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * In summary, this code allows unauthenticated access to any HTTP POST request to a URL that starts with /register/,
         * and requires authentication for all other requests.
         * It also disables CSRF protection for the application.
         */
        http
                // configures how requests are authorized
                .authorizeRequests()
                // specifies which requests are matched by a given pattern.
                // In this case, the pattern is (HttpMethod.POST, "/register/*"),
                // which matches any HTTP POST request to a URL that starts with /register/.
                .antMatchers(HttpMethod.POST, "/registration/*")
                // allows unauthenticated access to the matched requests
                .permitAll()
                // configures how requests that do not match any previous rules are authorized.
                // In this case, any request that does not match /register/* is required to be authenticated
                .anyRequest()
                // specifies that authenticated users are allowed to access any request that matches this rule
                .authenticated()
                // TODO
                .and()
                // configures Cross-Site Request Forgery (CSRF) protection for the application
                .csrf()
                // disables CSRF protection
                .disable();
    }
}
