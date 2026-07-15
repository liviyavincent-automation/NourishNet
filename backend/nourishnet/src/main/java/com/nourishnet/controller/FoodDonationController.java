package com.nourishnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.nourishnet.model.FoodDonation;
import com.nourishnet.service.FoodDonationService;

@RestController
@RequestMapping("/api")
public class FoodDonationController {

    @Autowired
    private FoodDonationService foodDonationService;

    @PostMapping("/users/{userId}/donations")
    public FoodDonation createDonation(@PathVariable int userId,
                                       @RequestBody FoodDonation donation) {

        return foodDonationService.saveDonation(userId, donation);
    }
@GetMapping("/donations")
public List<FoodDonation> getAllDonations() {
    return foodDonationService.getAllDonations();
}
@GetMapping("/donations/{id}")
public FoodDonation getDonationById(@PathVariable int id) {
    return foodDonationService.getDonationById(id);
}
@PutMapping("/donations/{id}")
public FoodDonation updateDonation(@PathVariable int id,
                                   @RequestBody FoodDonation donation) {

    return foodDonationService.updateDonation(id, donation);
}
@DeleteMapping("/donations/{id}")
public String deleteDonation(@PathVariable int id) {

    return foodDonationService.deleteDonation(id);
}

@GetMapping("/donations/user/{userId}")
public List<FoodDonation> getDonationsByUser(
        @PathVariable int userId) {


    return foodDonationService
            .getDonationsByUser(userId);

}
}