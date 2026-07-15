package com.nourishnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nourishnet.model.VolunteerLocation;
import java.util.Optional;

import com.nourishnet.model.PickupRequest;

public interface VolunteerLocationRepository extends JpaRepository<VolunteerLocation, Integer> {
Optional<VolunteerLocation> findByPickupRequest(PickupRequest pickupRequest);

}