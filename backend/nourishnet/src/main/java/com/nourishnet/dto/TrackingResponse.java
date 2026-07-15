package com.nourishnet.dto;

public class TrackingResponse {

    private Double donorLatitude;
    private Double donorLongitude;

    private Double ngoLatitude;
    private Double ngoLongitude;

    private Double volunteerLatitude;
    private Double volunteerLongitude;
    private String donorName;

private String ngoName;

private String volunteerName;

private String pickupStatus;

    public TrackingResponse() {
    }

    public Double getDonorLatitude() {
        return donorLatitude;
    }

    public void setDonorLatitude(Double donorLatitude) {
        this.donorLatitude = donorLatitude;
    }

    public Double getDonorLongitude() {
        return donorLongitude;
    }

    public void setDonorLongitude(Double donorLongitude) {
        this.donorLongitude = donorLongitude;
    }

    public Double getNgoLatitude() {
        return ngoLatitude;
    }

    public void setNgoLatitude(Double ngoLatitude) {
        this.ngoLatitude = ngoLatitude;
    }

    public Double getNgoLongitude() {
        return ngoLongitude;
    }

    public void setNgoLongitude(Double ngoLongitude) {
        this.ngoLongitude = ngoLongitude;
    }

    public Double getVolunteerLatitude() {
        return volunteerLatitude;
    }

    public void setVolunteerLatitude(Double volunteerLatitude) {
        this.volunteerLatitude = volunteerLatitude;
    }

    public Double getVolunteerLongitude() {
        return volunteerLongitude;
    }

    public void setVolunteerLongitude(Double volunteerLongitude) {
        this.volunteerLongitude = volunteerLongitude;
    }
    public String getDonorName() {
    return donorName;
}

public void setDonorName(String donorName) {
    this.donorName = donorName;
}

public String getNgoName() {
    return ngoName;
}

public void setNgoName(String ngoName) {
    this.ngoName = ngoName;
}

public String getVolunteerName() {
    return volunteerName;
}

public void setVolunteerName(String volunteerName) {
    this.volunteerName = volunteerName;
}

public String getPickupStatus() {
    return pickupStatus;
}

public void setPickupStatus(String pickupStatus) {
    this.pickupStatus = pickupStatus;
}
}