package com.haileysun.bookmyhotel.controller;

import com.haileysun.bookmyhotel.entity.Stay;
import com.haileysun.bookmyhotel.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "/search")
    public List<Stay> searchStays (
            @RequestParam("guest_number") int guestNumber,
            @RequestParam("checkin_date") String start,
            @RequestParam("checkout_date") String end,
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            // `required = false`: If the request parameter is missing, and you haven't specified a defaultValue attribute, Spring MVC will pass a null value to the method parameter.
            // In this case, if the distance is null, the distance will be assigned a default value (i.e. 50) in `CustomLocationRepositoryImpl`
            @RequestParam(value = "distance", required = false) String distance
    ) {
        // 1. get checkin data
        LocalDate checkinDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 2. get checkout data
        LocalDate checkoutDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 3. return value returned from service
        return searchService.search(guestNumber, checkinDate, checkoutDate, lat, lon, distance);
    }
}
