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
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch the parcels from the database
        List<Parcel> parcels = new CourierDAO().getAllParcels();  // Make sure this method works properly
        if (parcels == null || parcels.isEmpty()) {
            System.out.println("No parcels found in the database.");
        } else {
            System.out.println("Fetched parcels: " + parcels.size());
        }

        // Set the list of parcels as an attribute to the request
        request.setAttribute("parcels", parcels);
        // Forward the request to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp"); // Updated to forward to the admin dashboard
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            // Update parcel status
            String trackingNumber = request.getParameter("trackingNumber");
            String status = request.getParameter("status");
            try {
                new CourierDAO().updateParcelStatus(trackingNumber, status);
                response.sendRedirect("/your-project/admin");  // Redirect back to the admin page after update
            } catch (Exception e) {
                throw new ServletException("Error updating parcel status", e);
            }
        } else if ("add".equals(action)) {
            // Add new parcel
            String trackingNumber = request.getParameter("trackingNumber");
            String status = request.getParameter("status");
            String destination = request.getParameter("destination");
            String estimatedDelivery = request.getParameter("estimatedDelivery");

            Parcel newParcel = new Parcel(trackingNumber, status, destination, estimatedDelivery);
            try {
                new CourierDAO().addParcel(newParcel);
                response.sendRedirect("/your-project/admin");  // Redirect to the admin page after adding a new parcel
            } catch (Exception e) {
                throw new ServletException("Error adding new parcel", e);
            }
        } else {
            // Handle other actions or invalid action parameters
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
}
