<%@ page import="com.courier.model.Parcel" %>
<html>
<head>
    <title>Track Parcel</title>
</head>
<body>
    <h1>Track Your Parcel</h1>
    <form method="get" action="../track">

        <label for="trackingNumber">Enter Tracking Number:</label>
        <input type="text" id="trackingNumber" name="trackingNumber" required />
        <input type="submit" value="Track" />
    </form>

    <%
        // Check if there's an error message and display it
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color:red;">Error: <%= error %></p>
    <%
        } else {
            // Display the parcel details if found
            Parcel trackedParcel = (Parcel) request.getAttribute("trackedParcel");
            if (trackedParcel != null) {
    %>
                <h3>Status: <%= trackedParcel.getStatus() %></h3>
                <p>Destination: <%= trackedParcel.getDestination() %></p>
                <p>Estimated Delivery: <%= trackedParcel.getEstimatedDelivery() %></p>
    <%
            } else {
    %>
                <p>No parcel found for the provided tracking number.</p>
    <%
            }
        }
    %>
</body>
</html>
