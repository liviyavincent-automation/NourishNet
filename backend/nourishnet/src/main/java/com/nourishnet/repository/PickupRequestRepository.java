package com.nourishnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nourishnet.model.PickupRequest;
import com.nourishnet.model.User;

public interface PickupRequestRepository extends JpaRepository<PickupRequest, Integer> {

    List<PickupRequest> findByNgo(User ngo);
    List<PickupRequest> findByVolunteer(User volunteer);

}