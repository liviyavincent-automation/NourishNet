package com.nourishnet.dto;

public class NgoRecommendation {

    private String ngoName;
    private double distance;

    public NgoRecommendation() {
    }

    public NgoRecommendation(String ngoName, double distance) {
        this.ngoName = ngoName;
        this.distance = distance;
    }

    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}