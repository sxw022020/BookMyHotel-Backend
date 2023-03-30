package com.haileysun.bookmyhotel.service;

import com.haileysun.bookmyhotel.entity.Stay;
import com.haileysun.bookmyhotel.entity.StayAvailability;
import com.haileysun.bookmyhotel.repository.LocationRepository;
import com.haileysun.bookmyhotel.repository.StayAvailabilityRepository;
import com.haileysun.bookmyhotel.repository.StayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class SearchService {
    private StayRepository stayRepository;
    private StayAvailabilityRepository stayAvailabilityRepository;
    private LocationRepository locationRepository;

    @Autowired
    public SearchService(StayRepository stayRepository, StayAvailabilityRepository stayAvailabilityRepository, LocationRepository locationRepository) {
        this.stayRepository = stayRepository;
        this.stayAvailabilityRepository = stayAvailabilityRepository;
        this.locationRepository = locationRepository;
    }

    public List<Stay> search(int guestNumber, LocalDate checkinDate, LocalDate checkoutDate, double lat, double lon, String distance) {
        // 1. [LocationRepository] find available stays based on lat, lon, distance
        List<Long> stayIDs = locationRepository.searchByDistance(lat, lon, distance);

        // 2. calculate the duration
        long duration = Duration.between(checkinDate.atStartOfDay(), checkoutDate.atStartOfDay()).toDays();

        // 3. [StayAvailabilityRepository] find available stays based on checkin, checkout date, duration
        List<Long> filteredStayIDs = stayAvailabilityRepository.findByDateBetweenAndStateIsAvailable(stayIDs, checkinDate, checkoutDate.minusDays(1), duration);

        // 4. [StayRepository] find stays based on IDs ad guest number
        return stayRepository.findByIdInAndGuestNumberGreaterThanEqual(filteredStayIDs, guestNumber);
    }
}
