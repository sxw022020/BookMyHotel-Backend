package com.haileysun.bookmyhotel.controller;

import com.haileysun.bookmyhotel.entity.Stay;
import com.haileysun.bookmyhotel.entity.User;
import com.haileysun.bookmyhotel.service.StayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class StayController {
    private StayService stayService;

    // constructor injection
    @Autowired
    public StayController(StayService stayService) {
        this.stayService = stayService;
    }

    // 1. get the list of stays based on host name
    @GetMapping(value = "/stays")
    public List<Stay> getStayList(@RequestParam(name = "host") String hostName) {
        return stayService.listByHost(hostName);
    }

    // 2. get a stay by stayID
    @GetMapping(value = "/stays/{stayID}")
    public Stay getStay(@PathVariable Long stayID) {
        return stayService.findStayByID(stayID);
    }

    // 3. add a stay
    @PostMapping("/stays")
    public void addStay(@RequestParam("stay_name") String stayName,
                        @RequestParam("address") String address,
                        @RequestParam("description") String description,
                        @RequestParam("host_name") String hostName,
                        @RequestParam("guest_number") int guestNumber,
                        @RequestParam("images") MultipartFile[] images) {

        Stay stay = new Stay
                .Builder()
                .setName(stayName)
                .setAddress(address)
                .setDescription(description)
                .setHost(new User.Builder().setUsername(hostName).build())
                .setGuestNumber(guestNumber)
                .build();

        stayService.addStay(stay, images);
    }

    // 4. delete a stay
    @DeleteMapping("/stays/{stayID}")
    public void deleteStay(@PathVariable Long stayID) {
        stayService.deleteStay(stayID);
    }
}
