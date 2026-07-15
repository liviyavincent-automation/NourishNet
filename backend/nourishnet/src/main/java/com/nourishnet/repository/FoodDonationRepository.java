package com.nourishnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nourishnet.model.FoodDonation;
import com.nourishnet.model.User;


public interface FoodDonationRepository 
extends JpaRepository<FoodDonation, Integer> {


    List<FoodDonation> findByUser(User user);


}