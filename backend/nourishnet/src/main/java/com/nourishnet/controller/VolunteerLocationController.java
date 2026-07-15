package com.nourishnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nourishnet.model.VolunteerLocation;
import com.nourishnet.service.VolunteerLocationService;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerLocationController {

    @Autowired
    private VolunteerLocationService volunteerLocationService;

    @PostMapping("/location/{pickupRequestId}")
    public VolunteerLocation saveLocation(
            @PathVariable int pickupRequestId,
            @RequestBody VolunteerLocation location) {

        return volunteerLocationService.saveLocation(
                pickupRequestId,
                location);
    }
    @GetMapping("/location/{pickupRequestId}")
public VolunteerLocation getLocation(
        @PathVariable int pickupRequestId) {

    return volunteerLocationService.getLocation(pickupRequestId);
}
@PutMapping("/location/{locationId}")
public VolunteerLocation updateLocation(
        @PathVariable int locationId,
        @RequestBody VolunteerLocation updatedLocation) {

    return volunteerLocationService.updateLocation(
            locationId,
            updatedLocation);
}
}