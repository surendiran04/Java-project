package com.courier.model;

public class Parcel {
    private String id;  // MongoDB ObjectId (can be auto-generated)
    private String trackingNumber;
    private String status;
    private String destination;
    private String estimatedDelivery;

    // Constructor without id (since MongoDB auto-generates it)
    public Parcel(String trackingNumber, String status, String destination, String estimatedDelivery) {
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.destination = destination;
        this.estimatedDelivery = estimatedDelivery;
    }

    // Constructor with id (if needed)
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
    public void setEstimatedDelivery(String estimatedDelivery) { this.estimatedDelivery = estimatedDelivery; }
}
