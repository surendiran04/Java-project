<%@ page import="java.util.List" %>
<%@ page import="com.courier.model.Parcel" %>

<html>
<head>
    <title>Parcel Management</title>
</head>
<body>
    <h1>Parcel Management</h1>
    <table border="1">
        <tr>
            <th>Tracking Number</th>
            <th>Status</th>
            <th>Destination</th>
            <th>Estimated Delivery</th>
            <th>Action</th>
        </tr>
        <%
            List<Parcel> parcels = (List<Parcel>) request.getAttribute("parcels");
            if (parcels != null) {
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
                        <option value="Processing">Processing</option>
                        <option value="Shipped">Shipped</option>
                        <option value="In Transit">In Transit</option>
                        <option value="Delivered">Delivered</option>
                    </select>
                    <input type="submit" value="Update">
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
</body>
</html>
