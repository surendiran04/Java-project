package com.courier.swing;

import com.courier.dao.CourierDAO;
import com.courier.model.Parcel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

public class TrackingGUI {
    private JFrame frame;
    private JTextField trackingField;
    private JTextArea resultArea;
    private JButton mapButton;  // Button to open map

    public TrackingGUI() {
        JFrame frame = new JFrame("Courier Tracking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Larger window for better layout
        frame.setLocationRelativeTo(null);  // Center the window on the screen

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        // Tracking input section
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel trackingLabel = new JLabel("Enter Tracking Number:");
        trackingLabel.setFont(new Font("Arial", Font.BOLD, 14));  // Make the label bold
        inputPanel.add(trackingLabel);

        trackingField = new JTextField(20);
        trackingField.setFont(new Font("Arial", Font.PLAIN, 14));
        trackingField.setBorder(BorderFactory.createLineBorder(Color.GRAY));  // Add a border to the text field
        inputPanel.add(trackingField);

        // Track Button with ActionListener
        JButton trackButton = new JButton("Track");
        trackButton.setFont(new Font("Arial", Font.BOLD, 14));
        trackButton.setBackground(new Color(0, 123, 255));  // Blue background
        trackButton.setForeground(Color.WHITE);  // White text
        trackButton.setFocusPainted(false);
        trackButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Button padding
        trackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Change cursor on hover
        trackButton.addActionListener(e -> trackParcel());
        inputPanel.add(trackButton);

        // Results display section with scroll pane
        resultArea = new JTextArea(10, 40);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBackground(Color.LIGHT_GRAY);  // Light gray background for results
        resultArea.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Map Button (Initially disabled)
        mapButton = new JButton("View on Map");
        mapButton.setFont(new Font("Arial", Font.BOLD, 14));
        mapButton.setBackground(new Color(0, 123, 255));
        mapButton.setForeground(Color.WHITE);
        mapButton.setFocusPainted(false);
        mapButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        mapButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mapButton.setEnabled(false);  // Disable the map button initially
        mapButton.addActionListener(e -> openMap());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.add(mapButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void trackParcel() {
        String trackingNumber = trackingField.getText().trim();

        if (trackingNumber.isEmpty()) {
            resultArea.setText("Please enter a tracking number.");
            return;
        }

        try {
            Parcel parcel = new CourierDAO().getParcelByTrackingNumber(trackingNumber);
            if (parcel != null) {
                resultArea.setText("Tracking Number: " + parcel.getTrackingNumber() + "\n" +
                        "Status: " + parcel.getStatus() + "\n" +
                        "Destination: " + parcel.getDestination() + "\n" +
                        "Estimated Delivery: " + parcel.getEstimatedDelivery());

                // Enable the map button once parcel details are retrieved
                mapButton.setEnabled(true);
            } else {
                resultArea.setText("Parcel not found with tracking number: " + trackingNumber);
                mapButton.setEnabled(false);  // Disable map button if no parcel is found
            }
        } catch (Exception ex) {
            resultArea.setText("An error occurred: " + ex.getMessage());
            mapButton.setEnabled(false);
        }
    }

    private void openMap() {
        try {
            String destination = resultArea.getText().split("\n")[2].split(":")[1].trim();  // Extract destination from resultArea
            String mapLink = "https://www.google.com/maps/search/?api=1&query=" + URLEncoder.encode(destination, "UTF-8");
            Desktop.getDesktop().browse(new URI(mapLink));  // Open map link in the default browser
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error opening map: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrackingGUI::new);
    }
}
