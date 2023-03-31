package com.haileysun.bookmyhotel.controller;

import com.haileysun.bookmyhotel.entity.Reservation;
import com.haileysun.bookmyhotel.entity.User;
import com.haileysun.bookmyhotel.exception.InvalidReservationDateException;
import com.haileysun.bookmyhotel.service.RegistrationService;
import com.haileysun.bookmyhotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/reservations")
    public List<Reservation> listReservation(Principal principal) {
        return reservationService.listByGuest(principal.getName());
    }

    @PostMapping(value = "/reservations")
    // `stayId` is written in the request body
    public void addReservation(@RequestBody Reservation reservation, Principal principal) {
        LocalDate checkinDate = reservation.getCheckinDate();
        LocalDate checkoutDate = reservation.getCheckoutDate();

        // invalid input check
        if (checkinDate.equals(checkoutDate) || checkinDate.isAfter(checkoutDate) || checkinDate.isBefore(LocalDate.now())) {
            throw new InvalidReservationDateException("Invalid date for reservation!");
        }

        // set guest to the reservation
        reservation.setGuest(new User.Builder().setUsername(principal.getName()).build());

        // save the reservation to database
        reservationService.add(reservation);
    }

    @DeleteMapping(value = "/reservations/{reservationId}")
    public void deleteReservation(@PathVariable Long reservationId) {
        reservationService.delete(reservationId);
    }
}
