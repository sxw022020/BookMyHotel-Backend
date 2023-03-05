package com.haileysun.bookmyhotel.controller;

import com.haileysun.bookmyhotel.entity.User;
import com.haileysun.bookmyhotel.entity.UserRole;
import com.haileysun.bookmyhotel.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/guest")
    public void addGuest(@RequestBody User user) {
        registrationService.add(user, UserRole.ROLE_GUEST);
    }

    @PostMapping("/host")
    public void addHost(@RequestBody User user) {
        registrationService.add(user, UserRole.ROLE_HOST);
    }
}
