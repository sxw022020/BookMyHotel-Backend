package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.StayAvailability;
import com.haileysun.bookmyhotel.entity.StayAvailabilityID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    /**
     * This method retrieves a list of LocalDate objects representing
     * the dates with available stays (where sa.state = 0) for a specific stayId and within the given date range (startDate to endDate).
     */
    @Query(value = "SELECT sa.stayAvailabilityID.local_date FROM StayAvailability sa WHERE sa.stayAvailabilityID.stay_id = ?1 AND sa.state = 0 AND sa.stayAvailabilityID.local_date BETWEEN ?2 AND ?3")
    List<LocalDate> countByDateBetweenAndId(Long stayId, LocalDate startDate, LocalDate endDate);

    /**
     * This method updates the state of the StayAvailability records to 1 (reserved) for a specific stayId and
     * within the given date range (startDate to endDate).
     * The @Modifying annotation indicates that this is an update query, which will modify the existing records in the database.
     */
    @Modifying
    @Query(value = "UPDATE StayAvailability sa SET sa.state = 1 WHERE sa.stayAvailabilityID.stay_id = ?1 AND sa.stayAvailabilityID.local_date BETWEEN ?2 AND ?3")
    void reserveByDateBetweenAndId(Long stayId, LocalDate startDate, LocalDate endDate);

    /**
     * This method updates the state of the StayAvailability records to 0 (available) for a specific stayId and
     * within the given date range (startDate to endDate).
     * The @Modifying annotation is also used here, as this query modifies the existing records in the database
     */
    @Modifying
    @Query(value = "UPDATE StayAvailability sa SET sa.state = 0 WHERE sa.stayAvailabilityID.stay_id = ?1 AND sa.stayAvailabilityID.local_date BETWEEN ?2 AND ?3")
    void cancelByDateBetweenAndId(Long stayId, LocalDate startDate, LocalDate endDate);
}