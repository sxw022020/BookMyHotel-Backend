package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.Location;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends ElasticsearchRepository<Location, Long> {
    /**
     * Not extend `JpaRepository`, because Elasticsearch has a different query implementation than MySQL.
     *
     *
     */
}
