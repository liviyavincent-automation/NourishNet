package com.nourishnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nourishnet.model.PickupRequest;
import com.nourishnet.model.VolunteerLocation;
import com.nourishnet.repository.PickupRequestRepository;
import com.nourishnet.repository.VolunteerLocationRepository;

@Service
public class VolunteerLocationService {


    @Autowired
    private VolunteerLocationRepository volunteerLocationRepository;


    @Autowired
    private PickupRequestRepository pickupRequestRepository;



    public VolunteerLocation saveLocation(
            int pickupRequestId,
            VolunteerLocation location) {


        PickupRequest pickupRequest =
                pickupRequestRepository
                .findById(pickupRequestId)
                .orElse(null);


        if(pickupRequest == null){
            return null;
        }


        location.setPickupRequest(pickupRequest);


        return volunteerLocationRepository.save(location);

    }





    public VolunteerLocation getLocation(int pickupRequestId){


        PickupRequest pickupRequest =
                pickupRequestRepository
                .findById(pickupRequestId)
                .orElse(null);


        if(pickupRequest == null){
            return null;
        }


        return volunteerLocationRepository
                .findByPickupRequest(pickupRequest)
                .orElse(null);

    }







    public VolunteerLocation updateLocation(
            int pickupRequestId,
            VolunteerLocation updatedLocation) {



        PickupRequest pickupRequest =
                pickupRequestRepository
                .findById(pickupRequestId)
                .orElse(null);



        if(pickupRequest == null){
            return null;
        }




        VolunteerLocation existingLocation =
                volunteerLocationRepository
                .findByPickupRequest(pickupRequest)
                .orElse(null);




        if(existingLocation == null){


            existingLocation = new VolunteerLocation();

            existingLocation.setPickupRequest(pickupRequest);

        }




        existingLocation.setLatitude(
                updatedLocation.getLatitude()
        );


        existingLocation.setLongitude(
                updatedLocation.getLongitude()
        );



        return volunteerLocationRepository.save(existingLocation);

    }

}