package com.haileysun.bookmyhotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
/** WebSecurityConfigurerAdapter provides a convenient base class for customizing Spring Security's web security configuration. */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    /**
     * Define the security configuration for your application's HTTP endpoints
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * In summary, this code allows unauthenticated access to any HTTP POST request to a URL that starts with /register/,
         * and requires authentication for all other requests.
         * That is, this specifies that all requests to the application's endpoints should be authenticated,
         * except those that start with /registration/*, which can be accessed by anyone.
         *
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

                .antMatchers(HttpMethod.POST, "/authenticate/*").permitAll()
                .antMatchers("/stays").permitAll()
                .antMatchers("/stays/*").permitAll()

                // configures how requests that do not match any previous rules are authorized.
                // In this case, any request that does not match /register/* is required to be authenticated
                .anyRequest()
                // specifies that authenticated users are allowed to access any request that matches this rule
                .authenticated()
                // add another configuration
                .and()
                // configures Cross-Site Request Forgery (CSRF) protection for the application
                .csrf()
                // disables CSRF protection
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // read user/authority data from the MySQL database for authentication
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authority WHERE username = ?");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
