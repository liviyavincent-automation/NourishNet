package com.nourishnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nourishnet.dto.LocationResponse;
import com.nourishnet.location.LocationService;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/api/location")
    public LocationResponse getLocation(@RequestParam String city) {

        return locationService.getCoordinates(city);

    }
}