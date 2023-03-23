package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.Location;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends ElasticsearchRepository<Location, Long>, CustomLocationRepository {
    /**
     * Not extend `JpaRepository`, because Elasticsearch has a different query implementation than MySQL.
     * But similar to JpaRepository, LocationRepository also provides some basic query functions like find(), save, delete().
     * But since our service needs to support search based on Geolocation, we need to implement the search function ourselves.
     *
     * ElasticsearchRepository<Location, Long> + CustomLocationRepository ==> LocationRepository
     * CustomLocationRepositoryImpl ==> CustomLocationRepository
     */
}
