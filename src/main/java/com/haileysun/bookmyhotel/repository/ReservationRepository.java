package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.Reservation;
import com.haileysun.bookmyhotel.entity.Stay;
import com.haileysun.bookmyhotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGuest(User guest);
    List<Reservation> findByStay(Stay stay);
}
