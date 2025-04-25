package com.courier.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBConnection {
    private static final String CONNECTION_STRING = "mongodb+srv://surendiranm2004:rSGfm6O8x2uMiHRA@cluster-java.fc3dble.mongodb.net/?retryWrites=true&w=majority&appName=Cluster-Java";
    private static final String DATABASE_NAME = "courier_db";
    
    private static MongoClient mongoClient;
    
    static {
        try {
            // Create MongoDB connection
            mongoClient = MongoClients.create(CONNECTION_STRING);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to MongoDB", e);
        }
    }
    
    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase(DATABASE_NAME);
    }
    
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}