package com.courier.swing;

import com.courier.dao.CourierDAO;
import com.courier.model.Parcel;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TrackingGUI {
    private JFrame frame;
    private JTextField trackingField;
    private JTextArea resultArea;
    
    public TrackingGUI() {
        frame = new JFrame("Courier Tracking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Tracking input
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Tracking Number:"));
        trackingField = new JTextField(20);
        inputPanel.add(trackingField);
        
        JButton trackButton = new JButton("Track");
        trackButton.addActionListener(e -> trackParcel());
        inputPanel.add(trackButton);
        
        // Results display
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    private void trackParcel() {
        String trackingNumber = trackingField.getText().trim();
        try {
            if (trackingNumber.isEmpty()) {
                resultArea.setText("Please enter a tracking number.");
                return;
            }

            Parcel parcel = new CourierDAO().getParcelByTrackingNumber(trackingNumber);
            if (parcel != null) {
                resultArea.setText("Tracking Number: " + parcel.getTrackingNumber() + "\n" +
                        "Status: " + parcel.getStatus() + "\n" +
                        "Destination: " + parcel.getDestination() + "\n" +
                         "Estimated Delivery: " + parcel.getEstimatedDelivery());
            } else {
                resultArea.setText("Parcel not found with tracking number: " + trackingNumber);
            }
        } catch (Exception ex) {
            resultArea.setText("An error occurred: " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrackingGUI::new);
    }
}