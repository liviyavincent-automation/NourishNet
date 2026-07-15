package com.nourishnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.nourishnet.model.FoodDonation;
import com.nourishnet.model.PickupRequest;
import com.nourishnet.model.User;
import com.nourishnet.repository.FoodDonationRepository;
import com.nourishnet.repository.PickupRequestRepository;
import com.nourishnet.repository.UserRepository;

@Service
public class PickupRequestService {

    @Autowired
    private PickupRequestRepository pickupRequestRepository;

    @Autowired
    private FoodDonationRepository foodDonationRepository;

    @Autowired
    private UserRepository userRepository;

    public PickupRequest acceptDonation(int donationId, int ngoId) {

        FoodDonation donation = foodDonationRepository.findById(donationId).orElse(null);

        User ngo = userRepository.findById(ngoId).orElse(null);

        if (donation == null || ngo == null) {
            return null;
        }

        PickupRequest request = new PickupRequest();

        request.setDonation(donation);

        request.setNgo(ngo);

        request.setPickupStatus("Accepted");

        return pickupRequestRepository.save(request);
    }
    public List<PickupRequest> getPickupRequestsByNgo(int ngoId) {

    User ngo = userRepository.findById(ngoId).orElse(null);

    if (ngo == null) {
        return null;
    }

    return pickupRequestRepository.findByNgo(ngo);
    //Find every pickup request belonging to that NGO.
}
public PickupRequest updatePickupStatus(int requestId, String status) {

    PickupRequest request = pickupRequestRepository.findById(requestId).orElse(null);

    if (request == null) {
        return null;
    }

    request.setPickupStatus(status);

    return pickupRequestRepository.save(request);
}
public PickupRequest assignVolunteer(int requestId, int volunteerId) {

    PickupRequest request =
            pickupRequestRepository.findById(requestId)
            .orElse(null);

    User volunteer =
            userRepository.findById(volunteerId)
            .orElse(null);


    if(request == null || volunteer == null) {
        return null;
    }


    request.setVolunteer(volunteer);

    request.setPickupStatus("Assigned");


    return pickupRequestRepository.save(request);
}
public List<PickupRequest> getPickupRequestsByVolunteer(int volunteerId)
{

    User volunteer =
        userRepository.findById(volunteerId)
        .orElse(null);


    if(volunteer == null)
    {
        return null;
    }


    return pickupRequestRepository
            .findByVolunteer(volunteer);

}
}