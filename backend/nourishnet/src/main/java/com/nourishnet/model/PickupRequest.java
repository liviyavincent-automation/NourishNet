package com.nourishnet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pickup_requests")
public class PickupRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private FoodDonation donation;

    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private User ngo;

    private String pickupStatus;

    @ManyToOne
@JoinColumn(name = "volunteer_id")
private User volunteer;

    public PickupRequest() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public FoodDonation getDonation() {
        return donation;
    }

    public void setDonation(FoodDonation donation) {
        this.donation = donation;
    }

    public User getNgo() {
        return ngo;
    }

    public void setNgo(User ngo) {
        this.ngo = ngo;
    }

    public String getPickupStatus() {
        return pickupStatus;
    }

    public void setPickupStatus(String pickupStatus) {
        this.pickupStatus = pickupStatus;
    }

    public User getVolunteer() {
    return volunteer;
}

public void setVolunteer(User volunteer) {
    this.volunteer = volunteer;
}

}