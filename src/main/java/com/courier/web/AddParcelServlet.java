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

@WebServlet("/addParcel")
public class AddParcelServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the addParcel.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addParcel.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form data from the request
        String trackingNumber = request.getParameter("trackingNumber");
        String status = request.getParameter("status");
        String destination = request.getParameter("destination");
        String estimatedDelivery = request.getParameter("estimatedDelivery");
        String senderName = request.getParameter("senderName");  // Get sender name from form
        String receiverName = request.getParameter("receiverName");  // Get receiver name from form
        String senderContact = request.getParameter("senderContact");  // Get sender contact from form
        String receiverContact = request.getParameter("receiverContact");  // Get receiver contact from form

        // Create a new Parcel object with the sender and receiver names and contacts
        Parcel newParcel = new Parcel(null, trackingNumber, status, destination, estimatedDelivery, senderName, receiverName, senderContact, receiverContact);

        // Save the new parcel to the database
        CourierDAO courierDAO = new CourierDAO();
        courierDAO.addParcel(newParcel);

        // Redirect to the admin page (or any other page after adding the parcel)
        response.sendRedirect("admin");
    }
}
