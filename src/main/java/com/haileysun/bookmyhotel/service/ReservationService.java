package com.haileysun.bookmyhotel.service;

import com.haileysun.bookmyhotel.entity.Reservation;
import com.haileysun.bookmyhotel.entity.Stay;
import com.haileysun.bookmyhotel.entity.User;
import com.haileysun.bookmyhotel.exception.ReservationCollisionException;
import com.haileysun.bookmyhotel.exception.ReservationNotFoundException;
import com.haileysun.bookmyhotel.repository.ReservationRepository;
import com.haileysun.bookmyhotel.repository.StayAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private StayAvailabilityRepository stayAvailabilityRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, StayAvailabilityRepository stayAvailabilityRepository) {
        this.reservationRepository = reservationRepository;
        this.stayAvailabilityRepository = stayAvailabilityRepository;
    }

    // 1. List reservation by guest
    public List<Reservation> listByGuest(String username) {
        return reservationRepository.findByGuest(new User.Builder().setUsername(username).build());
    }

    // 2. List reservation by stay id
    public List<Reservation> listByStay(Long stayId) {
        return reservationRepository.findByStay(new Stay.Builder().setId(stayId).build());
    }

    // 3. Add the reservation
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(Reservation reservation) throws ReservationCollisionException {
        // 1. get available dates based on the checkin and checkout date and stay id
        List<LocalDate> dates = stayAvailabilityRepository.countByDateBetweenAndId(reservation.getStay().getId(), reservation.getCheckinDate(), reservation.getCheckoutDate().minusDays(1));

        // 2. get the duration (# of days to stay) based on checkin and checkout date
        int duration = (int) Duration.between(reservation.getCheckinDate().atStartOfDay(), reservation.getCheckoutDate().atStartOfDay()).toDays();

        // 3. check reservation collision
        if (duration != dates.size()) {
            throw new ReservationCollisionException("Duplicate reservation, cannot do the reservation!");
        }

        // 4. if no collision, do the reservation to stay availability table in database
        stayAvailabilityRepository.reserveByDateBetweenAndId(reservation.getStay().getId(), reservation.getCheckinDate(), reservation.getCheckoutDate().minusDays(1));

        // 5. dave the reservation to the database
        reservationRepository.save(reservation);
    }

    // 4. Delete the reservation
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long reservationId) throws ReservationNotFoundException {
        // 1. find the reservation
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new ReservationNotFoundException("Reservation is not found!"));

        // 2. delete the reservation from stay availability table in database
        stayAvailabilityRepository.cancelByDateBetweenAndId(reservation.getStay().getId(), reservation.getCheckinDate(), reservation.getCheckoutDate().minusDays(1));

        // 3. delete the reservation from reservation table in database
        reservationRepository.deleteById(reservationId);
    }
}
