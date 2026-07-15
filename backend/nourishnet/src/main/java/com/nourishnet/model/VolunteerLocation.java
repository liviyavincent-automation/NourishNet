package com.nourishnet.model;

import jakarta.persistence.*;

@Entity
public class VolunteerLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    private String volunteerName;

    private Double latitude;

    private Double longitude;

    @OneToOne
    @JoinColumn(name = "pickup_request_id")
    private PickupRequest pickupRequest;

    public VolunteerLocation() {
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public PickupRequest getPickupRequest() {
        return pickupRequest;
    }

    public void setPickupRequest(PickupRequest pickupRequest) {
        this.pickupRequest = pickupRequest;
    }
}