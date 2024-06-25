<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Make Appointment</title>
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
            height: 430px;
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
        .btn-primary {
            background-color: #144848;
            border: none;
            border-radius: 15px;
            padding: 7px 0;
            width: 100%;
            font-size: 17px;
            font-weight: bolder;
            font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            position: relative;
            top: 20px;
        }
        .btn-primary:hover {
            background-color: #257b7b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Make Appointment</h2>
        <form action="MakeAppointmentServlet" method="POST">
            <div class="form-group">
                <label for="appointment_date">Date:</label>
                <input type="date" class="form-control" id="appointment_date" name="appointment_date" required>
            </div>
            <div class="form-group">
                <label for="appointment_time">Time:</label>
                <input type="time" class="form-control" id="appointment_time" name="appointment_time" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <input type="text" class="form-control" id="description" name="description" required>
            </div>
            <button type="submit" class="btn btn-primary">Make Appointment</button>
        </form>
    </div>
</body>
</html>
