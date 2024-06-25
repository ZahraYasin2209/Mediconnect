<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Appointment</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #d5ebff;
            font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 4px 18px rgba(0, 0, 0, 0.2);
            padding: 20px;
            max-width: 500px;
            width: 100%;
            position: relative;
            height: 500px;
        }
        h2 {
            text-align: center;
            background-color: #144848;
            color: white;
            font-weight: bolder;
            margin-bottom: 15px;
   			padding: 10px;
            margin-top: 10px;
            border-radius: 10px;
        }
        .form-group {
            margin-bottom: 9px;
        }
        .form-group label {
            font-weight: bold;
            position: relative;
            top: 7px;
            font-size: 17px;
        }

        .form-control {
            border-radius: 5px;
        }
        .form-check-label {
            margin-left: 5px;
        }

        .btn-warning {
            background-color: #f0ad4e;
            border: none;
            border-radius: 15px;
            padding: 7px 0;
            width: 100%;
            font-size: 17px;
            font-weight: bolder;
            font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;

        }
        .btn-warning:hover {
            background-color: #ec971f;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Appointment</h2>
        <form action="UpdateAppointmentServlet" method="POST">
            <div class="form-group">
                <label for="appointment_id">Appointment ID:</label>
                <input type="text" class="form-control" id="appointment_id" name="appointment_id" required>
            </div>
            <div class="form-group">
                <label for="date">New Date:</label>
                <input type="date" class="form-control" id="date" name="date" required>
            </div>
            <div class="form-group">
                <label for="time">New Time:</label>
                <input type="time" class="form-control" id="time" name="time" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <input type="text" class="form-control" id="description" name="description" required>
            </div>
            <button type="submit" class="btn btn-warning">Update Appointment</button>
        </form>
    </div>
</body>
</html>
