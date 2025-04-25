package com.courier.model;

import java.util.Date;

public class Parcel {
    private String id;  // Changed from int to String for MongoDB ObjectId
    private String trackingNumber;
    private String status;
    private String destination;
    private String estimatedDelivery;
    
    public Parcel(String id, String trackingNumber, String status,
                  String destination, String estimatedDelivery) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.destination = destination;
        this.estimatedDelivery = estimatedDelivery;
    }
    
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getEstimatedDelivery() { return estimatedDelivery; }
    public void setEstimatedDelivery(Date estimatedDelivery) { this.estimatedDelivery = String.valueOf(estimatedDelivery); }
}