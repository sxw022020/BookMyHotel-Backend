package com.haileysun.bookmyhotel.filter;

import com.haileysun.bookmyhotel.entity.Authority;
import com.haileysun.bookmyhotel.repository.AuthorityRepository;
import com.haileysun.bookmyhotel.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";

    private final String BEARER_PREFIX = "Bearer ";
    private AuthorityRepository authorityRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public JwtFilter(AuthorityRepository authorityRepository, JwtUtil jwtUtil) {
        this.authorityRepository = authorityRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     *  Responsible for:
     *  (1) extracting the JWT token from the Authorization header,
     *  (2) validating the token, and
     *  (3) creating and setting the Authentication object in the SecurityContextHolder.
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 1. gets the Authorization header from the incoming request using the getHeader method.
        final String authorizationHeader = httpServletRequest.getHeader(HEADER);

        // 2. if the `Authorization` header is not null and starts with the "Bearer " prefix,
        // indicating that a JWT token is present in the header.
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            jwt = authorizationHeader.substring(BEARER_PREFIX.length());
        }

        // 3. validates the token using the validateToken method of a JwtUtil class.
        // If the token is valid and the current Authentication object in the SecurityContextHolder is null,
        // indicating that the user is not already authenticated.
        if (jwt != null && jwtUtil.validateToken(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
            // user's username is extracted from the token using the extractUsername method of the JwtUtil class.
            String username = jwtUtil.extractUsername(jwt);
            // user's authority is retrieved from the Authority repository based on the username.
            Authority authority = authorityRepository.findById(username).orElse(null);

            // If the authority is not null,
            if (authority != null) {
                // a GrantedAuthority object is created using the SimpleGrantedAuthority class and added to a list of authorities.
                List<GrantedAuthority> grantedAuthorities = Arrays.asList(
                        new GrantedAuthority[] {new SimpleGrantedAuthority(authority.getAuthority())}
                );

                // UsernamePasswordAuthenticationToken object is created using the username, null password, and the list of authorities.
                // The token's details are set using the WebAuthenticationDetailsSource class.
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        /**
                         * Why password is set as null?
                         *  - the UsernamePasswordAuthenticationToken constructor requires a password argument, even though it's not used in JWT authentication.
                         *  The password argument is used by other authentication mechanisms, such as form-based authentication,
                         *  where the user's password needs to be validated against a database or other data source.
                         *
                         *  - In JWT authentication, the password is not used or needed since the user has already been authenticated and authorized
                         *  by the time the JWT token is generated. In this case, see `AuthenticationService`.
                         *  The JWT token contains all the necessary information about the user's identity and permissions, so the password is not required.
                         *
                         *  - By setting the password argument to null, we are indicating that the password is not needed or used in JWT authentication.
                         *  The UsernamePasswordAuthenticationToken is only used as a container for the user's username and authorities,
                         *  which are extracted from the JWT token and used to create the Authentication object.
                         */
                        username, null, grantedAuthorities
                );

                // the SecurityContextHolder is updated with the UsernamePasswordAuthenticationToken,
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // the request is forwarded to the next filter in the chain using the doFilter method of the FilterChain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
