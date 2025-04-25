<%@ page import="com.courier.model.Parcel" %>
<%@ page import="java.io.IOException" %>
<html>
<head>
    <title>Add Parcel</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            padding: 40px;
        }
        h1 {
            color: #222;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        .form-container label {
            font-weight: bold;
        }
        .form-container input, .form-container select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .submit-btn {
            background-color: #007acc;
            color: white;
            border: none;
            padding: 12px 24px;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.2s ease-in-out;
        }
        .submit-btn:hover {
            background-color: #005fa3;
        }
    </style>
</head>
<body>
    <h1>Add New Parcel</h1>
    <div class="form-container">
        <form action="addParcel" method="post">
            <label for="trackingNumber">Tracking Number:</label>
            <input type="text" id="trackingNumber" name="trackingNumber" required>

            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="Processing">Processing</option>
                <option value="Shipped">Shipped</option>
                <option value="In Transit">In Transit</option>
                <option value="Delivered">Delivered</option>
            </select>

            <label for="destination">Destination:</label>
            <input type="text" id="destination" name="destination" required>

            <!-- Updated to include date picker for Estimated Delivery -->
            <label for="estimatedDelivery">Estimated Delivery:</label>
            <input type="date" id="estimatedDelivery" name="estimatedDelivery" required>

            <!-- Added Sender and Receiver Name fields -->
            <label for="senderName">Sender Name:</label>
            <input type="text" id="senderName" name="senderName" required>

            <label for="senderContact">Sender Contact:</label>
            <input type="text" id="senderContact" name="senderContact" required>

            <label for="receiverName">Receiver Name:</label>
            <input type="text" id="receiverName" name="receiverName" required>

            <label for="receiverContact">Receiver Contact:</label>
            <input type="text" id="receiverContact" name="receiverContact" required>

            <input class="submit-btn" type="submit" value="Add Parcel">
        </form>
    </div>
</body>
</html>
