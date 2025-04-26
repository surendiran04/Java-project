package com.courier.web;

import com.courier.dao.CourierDAO;
import com.courier.model.Parcel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/track")
public class TrackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String trackingNumber = request.getParameter("trackingNumber");

        // Check if tracking number is provided
        if (trackingNumber == null || trackingNumber.isEmpty()) {
            request.setAttribute("error", "Tracking number is required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/trackParcel.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Initialize DAO to fetch parcel
        CourierDAO courierDAO = new CourierDAO();
        Parcel parcel = courierDAO.getParcelByTrackingNumber(trackingNumber);

        // Check if parcel is found and set the appropriate attribute
        if (parcel == null) {
            request.setAttribute("error", "No parcel found with tracking number: " + trackingNumber);
        } else {
            request.setAttribute("trackedParcel", parcel);
        }

        // Forward the request to trackParcel.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/trackParcel.jsp");
        dispatcher.forward(request, response);
    }
}
