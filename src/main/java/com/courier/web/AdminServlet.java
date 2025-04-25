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
import java.sql.SQLException;
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String trackingNumber = request.getParameter("trackingNumber");
        String status = request.getParameter("status");

        try {
            if ("update".equals(action)) {
                new CourierDAO().updateParcelStatus(trackingNumber, status); // ✅ no ObjectId
            }
            doGet(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}