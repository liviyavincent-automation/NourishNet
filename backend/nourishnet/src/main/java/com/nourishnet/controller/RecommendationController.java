package com.nourishnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nourishnet.dto.NgoRecommendation;
import com.nourishnet.service.RecommendationService;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/nearest")
    public List<NgoRecommendation> getNearestNgo(
            @RequestParam int donationId) {

        return recommendationService.getNearestNgos(donationId);
    }
}