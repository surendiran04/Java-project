<html>
<head><title>Add Parcel</title></head>
<body>
    <h2>Add New Parcel</h2>
    <form method="post" action="../admin">

        <input type="hidden" name="action" value="add">
        Tracking Number: <input type="text" name="trackingNumber" required><br>
        Status:
        <select name="status">
            <option value="Processing">Processing</option>
            <option value="Shipped">Shipped</option>
        </select><br>
        Destination: <input type="text" name="destination" required><br>
        Estimated Delivery: <input type="date" name="estimatedDelivery" required><br>
        <input type="submit" value="Add Parcel">
    </form>
</body>
</html>
