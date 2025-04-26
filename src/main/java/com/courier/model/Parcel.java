package com.courier.model;

public class Parcel {
    private String id;  // MongoDB ObjectId (can be auto-generated)
    private String trackingNumber;
    private String status;
    private String destination;
    private String estimatedDelivery;
    private String senderName;  // New field for sender name
    private String receiverName;  // New field for receiver name
    private String senderContact;  // New field for sender contact information
    private String receiverContact;  // New field for receiver contact information

    // Updated constructor to include senderName, receiverName, senderContact, and receiverContact
    public Parcel(String id, String trackingNumber, String status,
                  String destination, String estimatedDelivery, String senderName, String receiverName,
                  String senderContact, String receiverContact) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.destination = destination;
        this.estimatedDelivery = estimatedDelivery;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.senderContact = senderContact;
        this.receiverContact = receiverContact;
    }

    public Parcel(String trackingNumber, String delivered, String newYork, String date) {
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

    // Getters and setters for senderName and receiverName
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

    // Getters and setters for senderContact and receiverContact
    public String getSenderContact() { return senderContact; }
    public void setSenderContact(String senderContact) { this.senderContact = senderContact; }

    public String getReceiverContact() { return receiverContact; }
    public void setReceiverContact(String receiverContact) { this.receiverContact = receiverContact; }
}
