package com.nourishnet.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nourishnet.dto.NgoRecommendation;
import com.nourishnet.model.FoodDonation;
import com.nourishnet.model.User;
import com.nourishnet.repository.FoodDonationRepository;
import com.nourishnet.repository.UserRepository;
import com.nourishnet.util.DistanceCalculator;

@Service
public class RecommendationService {

    @Autowired
    private FoodDonationRepository foodDonationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<NgoRecommendation> getNearestNgos(int donationId) {

        FoodDonation donation =
                foodDonationRepository.findById(donationId).orElse(null);

        if (donation == null) {
            return new ArrayList<>();
        }

        List<User> ngos = userRepository.findByRole("NGO");

        List<NgoRecommendation> recommendations = new ArrayList<>();

        for (User ngo : ngos) {

            if (ngo.getLatitude() == null || ngo.getLongitude() == null) {
                continue;
            }

            double distance = DistanceCalculator.calculateDistance(
                    donation.getLatitude(),
                    donation.getLongitude(),
                    ngo.getLatitude(),
                    ngo.getLongitude());

            double roundedDistance = Math.round(distance * 100.0) / 100.0;

recommendations.add(
        new NgoRecommendation(
                ngo.getFullName(),
                roundedDistance));
        }

        recommendations.sort(
                Comparator.comparingDouble(NgoRecommendation::getDistance));

        return recommendations;
    }
}