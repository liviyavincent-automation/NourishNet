package com.nourishnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nourishnet.dto.TrackingResponse;
import com.nourishnet.service.TrackingService;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/{requestId}")
    public TrackingResponse getTrackingDetails(
            @PathVariable int requestId) {

        return trackingService.getTrackingDetails(requestId);
    }
}