package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.Stay;
import com.haileysun.bookmyhotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
    // 1. Find list of stays by host's username

    /**
     * When this method is invoked, Spring Data JPA generates a query that looks something like this:
     * `SELECT * FROM stays WHERE host_id = :userId;`
     */
    List<Stay> findByHost(User user);

    // 2. Find list of stays by stay ids and guest number
    /**
     * When this method is invoked, Spring Data JPA generates a query that looks something like this:
     * `SELECT * FROM reservations WHERE id IN (:ids) AND guest_number >= :minGuests`
     */
    List<Stay> findByIdInAndGuestNumberGreaterThanEqual(List<Long> stayIds, int guestNumber);
}