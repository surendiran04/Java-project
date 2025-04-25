package com.courier.service;

import com.courier.dao.CourierDAO;
import com.courier.model.Parcel;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.sql.SQLException;

@WebService
public class TrackingService {
    @WebMethod
    public String getParcelStatus(String trackingNumber) {
        try {
            Parcel parcel = new CourierDAO().getParcelById(String.valueOf(Integer.parseInt(trackingNumber)));
            return parcel != null ? parcel.getStatus() : "Parcel not found";
        } catch (NumberFormatException e) {
            return "Error: " + e.getMessage();
        }
    }

    @WebMethod
    public String updateParcelStatus(String trackingNumber, String newStatus) {
        try {
            new CourierDAO().updateParcelStatus(String.valueOf(Integer.parseInt(trackingNumber)), newStatus);
            return "Status updated successfully";
        } catch (NumberFormatException e) {
            return "Error: " + e.getMessage();
        }
    }
}
