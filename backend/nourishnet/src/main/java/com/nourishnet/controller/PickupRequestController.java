package com.nourishnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.nourishnet.model.PickupRequest;
import com.nourishnet.service.PickupRequestService;

@RestController
@RequestMapping("/api")
public class PickupRequestController {

    @Autowired
    private PickupRequestService pickupRequestService;

    @PostMapping("/donations/{donationId}/accept/{ngoId}")
    public PickupRequest acceptDonation(@PathVariable int donationId,
                                        @PathVariable int ngoId) {

        return pickupRequestService.acceptDonation(donationId, ngoId);
    }
    @GetMapping("/pickups/ngo/{ngoId}")
public List<PickupRequest> getPickupRequests(@PathVariable int ngoId) {

    return pickupRequestService.getPickupRequestsByNgo(ngoId);
}
@PutMapping("/pickups/{requestId}/status")
public PickupRequest updatePickupStatus(
        @PathVariable int requestId,
        @RequestParam String status) {

    return pickupRequestService.updatePickupStatus(requestId, status);
}
@PutMapping("/pickups/{requestId}/assign/{volunteerId}")
public PickupRequest assignVolunteer(
        @PathVariable int requestId,
        @PathVariable int volunteerId) {

    return pickupRequestService.assignVolunteer(
            requestId,
            volunteerId);
}
@GetMapping("/pickups/volunteer/{volunteerId}")
public List<PickupRequest> getVolunteerPickups(
        @PathVariable int volunteerId)
{

    return pickupRequestService
            .getPickupRequestsByVolunteer(volunteerId);

}
}