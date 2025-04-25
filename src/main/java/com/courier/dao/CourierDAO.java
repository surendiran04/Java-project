package com.courier.dao;

import com.courier.model.Parcel;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

public class CourierDAO {
    private final MongoCollection<Document> parcelsCollection;

    public CourierDAO() {
        MongoDatabase database = DBConnection.getDatabase();
        this.parcelsCollection = database.getCollection("parcels");
    }

    public Parcel getParcelById(String id) {
        if (id == null || !ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Invalid ObjectId: must be a 24-character hexadecimal string");
        }
        Document doc = parcelsCollection.find(eq("_id", new ObjectId(id))).first();
        return doc != null ? documentToParcel(doc) : null;
    }

    public Parcel getParcelByTrackingNumber(String trackingNumber) {
        Document doc = parcelsCollection.find(eq("trackingNumber", trackingNumber)).first();
        return doc != null ? documentToParcel(doc) : null;
    }

    public List<Parcel> getAllParcels() {
        List<Parcel> parcels = new ArrayList<>();
        for (Document doc : parcelsCollection.find()) {
            System.out.println(doc.toJson());  // Print the document to the console
            parcels.add(documentToParcel(doc));
        }
        return parcels;
    }

    public void updateParcelStatus(String trackingNumber, String status) {
        parcelsCollection.updateOne(
                eq("trackingNumber", trackingNumber),
                new Document("$set", new Document("status", status))
        );
    }

    private Parcel documentToParcel(Document doc) {
        System.out.println("Converting document to Parcel: " + doc.toJson());  // Log document before conversion
        return new Parcel(
                doc.getObjectId("_id").toString(),
                doc.getString("trackingNumber"),
                doc.getString("status"),
                doc.getString("destination"),
                doc.getString("estimatedDelivery"),
                doc.getString("senderName"),        // Added senderName
                doc.getString("receiverName"),      // Added receiverName
                doc.getString("senderContact"),    // Added senderContact
                doc.getString("receiverContact")   // Added receiverContact
        );
    }

    public void addParcel(Parcel parcel) {
        if (parcel == null) {
            throw new IllegalArgumentException("Parcel cannot be null");
        }

        Document parcelDoc = new Document()
                .append("trackingNumber", parcel.getTrackingNumber())
                .append("status", parcel.getStatus())
                .append("destination", parcel.getDestination())
                .append("estimatedDelivery", parcel.getEstimatedDelivery())
                .append("senderName", parcel.getSenderName())   // Added senderName
                .append("receiverName", parcel.getReceiverName()) // Added receiverName
                .append("senderContact", parcel.getSenderContact())  // Added senderContact
                .append("receiverContact", parcel.getReceiverContact()); // Added receiverContact

        parcelsCollection.insertOne(parcelDoc);
        System.out.println("Parcel added: " + parcelDoc.toJson());
    }
}
