package com.haileysun.bookmyhotel.repository;

import com.haileysun.bookmyhotel.entity.Location;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class CustomLocationRepositoryImpl implements CustomLocationRepository {
    // TODO - Why 50?
    private final String DEFAULT_DISTANCE = "50";

    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public CustomLocationRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    /**
     * Searches for Location documents in Elasticsearch based on the distance from a given latitude and longitude.
     *
     * @param lat
     * @param lon
     * @param distance
     * @return
     */
    @Override
    public List<Long> searchByDistance(double lat, double lon, String distance) {
        if (distance == null || distance.isEmpty()) {
            distance = DEFAULT_DISTANCE;
        }

        /**
         * 1. A `NativeSearchQueryBuilder` object is created.
         *    This object will be used to build a search query.
         */
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        /**
         * 2. A GeoDistanceQueryBuilder object is created with the provided latitude, longitude, and distance.
         *    This object is added as a filter to the queryBuilder.
         */
        queryBuilder.withFilter(new GeoDistanceQueryBuilder("geoPoint").point(lat, lon).distance(distance, DistanceUnit.KILOMETERS));

        /**
         * 3. The search query is executed using `elasticsearchOperations.search()`,
         *    and the search results are stored in a `SearchHits<Location> object`.
         */
        SearchHits<Location> searchResult = elasticsearchOperations.search(queryBuilder.build(), Location.class);

        /**
         * 4. Extracts `location IDs` and adds to the `locationIDs list`.
         */
        List<Long> locationIDs = new ArrayList<>();
        for (SearchHit<Location> hit : searchResult.getSearchHits()) {
            locationIDs.add(hit.getContent().getId());
        }

        return locationIDs;
    }
}
