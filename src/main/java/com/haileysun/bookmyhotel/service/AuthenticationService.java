package com.haileysun.bookmyhotel.service;

import com.haileysun.bookmyhotel.entity.Authority;
import com.haileysun.bookmyhotel.entity.Token;
import com.haileysun.bookmyhotel.entity.User;
import com.haileysun.bookmyhotel.entity.UserRole;
import com.haileysun.bookmyhotel.exception.UserNotExistException;
import com.haileysun.bookmyhotel.repository.AuthorityRepository;
import com.haileysun.bookmyhotel.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private AuthorityRepository authorityRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, AuthorityRepository authorityRepository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.authorityRepository = authorityRepository;
        this.jwtUtil = jwtUtil;
    }

    public Token authenticate(User user, UserRole role) throws UserNotExistException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (AuthenticationException e) {
            throw new UserNotExistException("User Doesn't Exist!");
        }

        // find "username" in "user" table
        // "JpaRepository<Authority, String>" defines which table to do query
        Authority authority = authorityRepository.findById(user.getUsername()).orElse(null);
        if (authority == null || !authority.getAuthority().equals(role.name())) {
            throw new UserNotExistException("User Doesn't Exist!");
        }

        return new Token(jwtUtil.generateToken(user.getUsername()));
    }
}
