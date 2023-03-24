package com.haileysun.bookmyhotel.service;

import com.haileysun.bookmyhotel.entity.Authority;
import com.haileysun.bookmyhotel.entity.User;
import com.haileysun.bookmyhotel.entity.UserRole;
import com.haileysun.bookmyhotel.exception.UserAlreadyExistException;
import com.haileysun.bookmyhotel.repository.AuthorityRepository;
import com.haileysun.bookmyhotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(User user, UserRole role) throws UserAlreadyExistException {
        // check the User if already exists <-- "exception"
        if (userRepository.existsById(user.getUsername())) {
            throw new UserAlreadyExistException("User already exists!");
        }

        // encrypt user's password <-- "config"
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        // add user info to database
        userRepository.save(user);
        authorityRepository.save(
                new Authority.Builder()
                .setUsername(user.getUsername())
                .setAuthority(role.name())
                .build());
    }
}
