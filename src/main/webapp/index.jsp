<html>
<<<<<<< HEAD
<head><title>Courier Tracking</title></head>
<body>
    <h1>Welcome to Courier Tracking System</h1>
    <ul>
        <!-- Link to Admin Dashboard -->
        <li><a href="admin/dashboard.jsp">Admin Dashboard</a></li>
        <!-- Link to Add Parcel Page -->
        <li><a href="admin/addParcel.jsp">Add Parcel</a></li>
        <!-- Link to Track Parcel Page -->
        <li><a href="admin/trackParcel.jsp">Track Parcel</a></li>
    </ul>
=======
<head>
    <title>Parcel Management</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            padding: 40px;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            justify-content: space-between;
        }
        h1 {
            color: #222;
            text-align: center;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        }
        th, td {
            padding: 14px 18px;
            text-align: center;
            border-bottom: 1px solid #e0e0e0;
        }
        th {
            background-color: #007acc;
            color: #fff;
            text-transform: uppercase;
            font-size: 14px;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        select, input[type="submit"] {
            padding: 8px 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .update-btn {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            transition: background-color 0.2s ease-in-out;
        }
        .update-btn:hover {
            background-color: #218838;
        }
        /* Add New Parcel Button Style */
        .add-btn {
            background-color: #007acc; /* Blue color */
            color: white;
            border: none;
            padding: 12px 24px;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
            transition: background-color 0.2s ease-in-out;
            align-self: center;
            margin-top: 30px;
        }
        .add-btn:hover {
            background-color: #005fa3;
        }
    </style>
</head>
<body>
    <h1>Parcel Management</h1>
    <table>
        <tr>
            <th>Tracking Number</th>
            <th>Status</th>
            <th>Destination</th>
            <th>Estimated Delivery</th>
            <th>Action</th>
        </tr>
        <%
            List<Parcel> parcels = (List<Parcel>) request.getAttribute("parcels");

            if (parcels != null && !parcels.isEmpty()) {
                for (Parcel parcel : parcels) {
        %>
        <tr>
            <td><%= parcel.getTrackingNumber() %></td>
            <td><%= parcel.getStatus() %></td>
            <td><%= parcel.getDestination() %></td>
            <td><%= parcel.getEstimatedDelivery() %></td>
            <td>
                <form method="post" action="admin">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="trackingNumber" value="<%= parcel.getTrackingNumber() %>">
                    <select name="status">
                        <option value="Processing" <%= "Processing".equals(parcel.getStatus()) ? "selected" : "" %>>Processing</option>
                        <option value="Shipped" <%= "Shipped".equals(parcel.getStatus()) ? "selected" : "" %>>Shipped</option>
                        <option value="In Transit" <%= "In Transit".equals(parcel.getStatus()) ? "selected" : "" %>>In Transit</option>
                        <option value="Delivered" <%= "Delivered".equals(parcel.getStatus()) ? "selected" : "" %>>Delivered</option>
                    </select>
                    <input class="update-btn" type="submit" value="Update">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No parcels available</td>
        </tr>
        <% } %>
    </table>
    <!-- Add Parcel Button -->
    <a href="addParcel.jsp">
        <button class="add-btn">Add New Parcel</button>
    </a>
>>>>>>> aa6d3e57eeaf0ce585099c4b74f455564c5f0a1d
</body>
</html>
