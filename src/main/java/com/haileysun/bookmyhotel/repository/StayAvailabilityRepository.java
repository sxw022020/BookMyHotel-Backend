package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.StayAvailability;
import com.haileysun.bookmyhotel.entity.StayAvailabilityID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface StayAvailabilityRepository extends JpaRepository<StayAvailability, StayAvailabilityID> {
    /**
     * In SQL queries, the question mark symbol "?" is used as a parameter placeholder.
     * It indicates that a value will be supplied at the time of query execution, rather than being hard-coded into the query itself.
     * This is often used for dynamic values, such as user input or variables, to build safe and flexible queries.
     * Using parameter placeholders can also help prevent SQL injection attacks.
     *
     * In the given query, there are four placeholders:
     *
     * ?1: Represents a list of stay IDs that you want to filter the results by.
     * ?2: Represents the start date of the date range you want to filter the results by.
     * ?3: Represents the end date of the date range you want to filter the results by.
     * ?4: Represents the count of available local dates that the groups should have.
     *
     * `sa.state = 0`: state is available
     *
     * When executing the query, you would replace these placeholders with actual values to filter the results according to your desired criteria.
     */
    @Query(value = "SELECT sa.stayAvailabilityID.stay_id FROM StayAvailability sa WHERE sa.stayAvailabilityID.stay_id IN ?1 AND sa.state = 0 AND sa.stayAvailabilityID.local_date BETWEEN ?2 AND ?3 GROUP BY sa.stayAvailabilityID.stay_id HAVING COUNT(sa.stayAvailabilityID.local_date) = ?4")
    List<Long> findByDateBetweenAndStateIsAvailable(List<Long> stayIds, LocalDate startDate, LocalDate endData, long duration);
}
