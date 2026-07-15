package com.nourishnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.nourishnet.model.FoodDonation;
import com.nourishnet.model.User;
import com.nourishnet.repository.FoodDonationRepository;
import com.nourishnet.repository.UserRepository;
import com.nourishnet.util.FreshnessPredictor;
import com.nourishnet.location.LocationService;
import com.nourishnet.dto.LocationResponse;

@Service
public class FoodDonationService {

    @Autowired
    private FoodDonationRepository foodDonationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
private LocationService locationService;

    public FoodDonation saveDonation(int userId, FoodDonation donation) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }
        LocationResponse location =
        locationService.getCoordinates(donation.getDonorLocation());
        // Convert donor location into latitude and longitude

if (location != null) {

    donation.setLatitude(Double.parseDouble(location.getLat()));

    donation.setLongitude(Double.parseDouble(location.getLon()));

}

        donation.setUser(user);

      String status = FreshnessPredictor.predictFreshness(
        donation.getFoodType(),
        donation.getPreparationTime());

donation.setFreshnessStatus(status);

        return foodDonationRepository.save(donation);
    }
    public List<FoodDonation> getAllDonations() {
    return foodDonationRepository.findAll();
}
public FoodDonation getDonationById(int id) {
    return foodDonationRepository.findById(id).orElse(null);
}
public FoodDonation updateDonation(int id, FoodDonation updatedDonation) {

    FoodDonation existingDonation =
            foodDonationRepository.findById(id).orElse(null);

    if (existingDonation == null) {
        return null;
    }

    if (updatedDonation.getFoodName() != null) {
        existingDonation.setFoodName(updatedDonation.getFoodName());
    }

    if (updatedDonation.getFoodQuantity() != null) {
        existingDonation.setFoodQuantity(updatedDonation.getFoodQuantity());
    }

    if (updatedDonation.getPreparationTime() != null) {
        existingDonation.setPreparationTime(updatedDonation.getPreparationTime());
    }

    if (updatedDonation.getExpiryDate() != null) {
        existingDonation.setExpiryDate(updatedDonation.getExpiryDate());
    }

    if (updatedDonation.getDonorLocation() != null) {
        existingDonation.setDonorLocation(updatedDonation.getDonorLocation());
    }

    if (updatedDonation.getFoodType() != null) {
        existingDonation.setFoodType(updatedDonation.getFoodType());
    }

    if (updatedDonation.getFreshnessStatus() != null) {
        existingDonation.setFreshnessStatus(updatedDonation.getFreshnessStatus());
    }

    if (updatedDonation.getLatitude() != null) {
        existingDonation.setLatitude(updatedDonation.getLatitude());
    }

    if (updatedDonation.getLongitude() != null) {
        existingDonation.setLongitude(updatedDonation.getLongitude());
    }

    return foodDonationRepository.save(existingDonation);
}
public String deleteDonation(int id) {

    if (!foodDonationRepository.existsById(id)) {
        return "Donation not found";
    }

    foodDonationRepository.deleteById(id);

    return "Donation deleted successfully";
}

public List<FoodDonation> getDonationsByUser(int userId) {


    User user =
        userRepository.findById(userId)
        .orElse(null);


    if(user == null){

        return null;

    }


    return foodDonationRepository.findByUser(user);

}
}