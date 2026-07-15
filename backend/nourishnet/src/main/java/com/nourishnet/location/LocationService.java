package com.nourishnet.location;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nourishnet.dto.LocationResponse;

@Service
public class LocationService {

    public LocationResponse getCoordinates(String location) {

        String url = "https://nominatim.openstreetmap.org/search?q="
                + location
                + "&format=json&limit=1";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "NourishNet/1.0 (MCA Project)");
        //Nominatim requires header as per their acceptable user policy

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<LocationResponse[]> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        LocationResponse[].class);

        if (response.getBody() != null && response.getBody().length > 0) {
            return response.getBody()[0];
        }

        return null;
    }
}