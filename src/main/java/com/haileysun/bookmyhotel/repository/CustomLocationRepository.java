package com.haileysun.bookmyhotel.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomLocationRepository {

    // 1. Search by distance
    List<Long> searchByDistance(double lat, double lon, String distance);
}
