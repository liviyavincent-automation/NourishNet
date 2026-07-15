package com.nourishnet.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.nourishnet.dto.TrackingResponse;
import com.nourishnet.model.PickupRequest;
import com.nourishnet.model.VolunteerLocation;
import com.nourishnet.repository.PickupRequestRepository;
import com.nourishnet.repository.VolunteerLocationRepository;


@Service
public class TrackingService {


    @Autowired
    private PickupRequestRepository pickupRequestRepository;


    @Autowired
    private VolunteerLocationRepository volunteerLocationRepository;



    public TrackingResponse getTrackingDetails(int requestId) {


        TrackingResponse response = new TrackingResponse();



        PickupRequest request =
                pickupRequestRepository.findById(requestId)
                .orElse(null);



        if(request == null) {

            return response;

        }



        // Pickup Status

        response.setPickupStatus(
                request.getPickupStatus()
        );




        // =====================
        // DONOR DETAILS
        // =====================

        if(request.getDonation()!=null) {


            response.setDonorLatitude(
                    request.getDonation().getLatitude()
            );


            response.setDonorLongitude(
                    request.getDonation().getLongitude()
            );



            if(request.getDonation().getUser()!=null) {


                response.setDonorName(
                        request.getDonation()
                        .getUser()
                        .getFullName()
                );

            }

        }





        // =====================
        // NGO DETAILS
        // =====================

        if(request.getNgo()!=null) {


            response.setNgoLatitude(
                    request.getNgo().getLatitude()
            );


            response.setNgoLongitude(
                    request.getNgo().getLongitude()
            );


            response.setNgoName(
                    request.getNgo().getFullName()
            );


        }






        // =====================
        // VOLUNTEER DETAILS
        // =====================


        if(request.getVolunteer()!=null) {


            response.setVolunteerName(
                    request.getVolunteer()
                    .getFullName()
            );


        }




        // =====================
        // LIVE LOCATION
        // =====================


        VolunteerLocation location =
                volunteerLocationRepository
                .findByPickupRequest(request)
                .orElse(null);



        if(location!=null) {


            response.setVolunteerLatitude(
                    location.getLatitude()
            );


            response.setVolunteerLongitude(
                    location.getLongitude()
            );


        }



        return response;


    }


}