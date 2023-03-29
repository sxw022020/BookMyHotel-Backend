package com.haileysun.bookmyhotel.service;

import com.haileysun.bookmyhotel.entity.Location;
import com.haileysun.bookmyhotel.exception.AWSLocationServiceException;
import com.haileysun.bookmyhotel.exception.InvalidStayAddressException;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.location.LocationClient;
import software.amazon.awssdk.services.location.model.Place;
import software.amazon.awssdk.services.location.model.SearchForTextResult;
import software.amazon.awssdk.services.location.model.SearchPlaceIndexForTextRequest;
import software.amazon.awssdk.services.location.model.SearchPlaceIndexForTextResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Value("${amazonlocation.placesindexname}")
    private String placesIndexName;

    @Value("${aws.region}")
    private String awsRegion;

    /**
     * Returns a Location object with the latitude and longitude coordinates
     *
     * @param id
     * @param address
     * @return
     * @throws InvalidStayAddressException
     * @throws AWSLocationServiceException
     */
    public Location getLatLon(Long id, String address) throws InvalidStayAddressException, AWSLocationServiceException {

        // The LocationClient is used to interact with the AWS Location Service. We create a client by specifying the AWS region.
        LocationClient locationClient = LocationClient.builder()
                .region(Region.of(awsRegion))
                .build();

        try {
            // `SearchPlaceIndexForTextRequest` represents a request to search for a place index using the provided address.
            // The request is configured with the address and the AWS place index name.
            SearchPlaceIndexForTextRequest request = SearchPlaceIndexForTextRequest.builder()
                    .text(address)
                    .indexName(placesIndexName)
                    .build();
            // sends the request to the AWS Location Service and returns a SearchPlaceIndexForTextResponse object containing the search results.
            SearchPlaceIndexForTextResponse response = locationClient.searchPlaceIndexForText(request);
            List<SearchForTextResult> searchForTextResults = response.results();

            if (searchForTextResults.isEmpty()) {
                throw new InvalidStayAddressException("Failed to find stay address");
            }

            // extract the first `Place` object from the search results and retrieve its latitude and longitude coordinates.
            /**
             * Why only retrieve first `Place` object?
             *  - because it typically represents the most relevant or best-matching result for the given address query.
             *  - when you search for a place using the AWS Location Service, the API returns a list of possible matches sorted by relevance.
             *  - the first Place object in the list is considered the best match and is usually the most accurate result for the given address.
             */
            Place place = searchForTextResults.get(0).place();
            List<Double> coordinates = place.geometry().point();
            double lat = coordinates.get(1);
            double lng = coordinates.get(0);

            return new Location(id, new GeoPoint(lat, lng));

        } catch (Exception e) {
            e.printStackTrace();
            throw new AWSLocationServiceException("Failed to encode stay address");
        }
    }
}