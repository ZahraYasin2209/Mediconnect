<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Report</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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
            padding: 30px;
            max-width: 500px;
            width: 100%;
            position: relative;
            height: 450px;
        }
        h2 {
            text-align: center;
            background-color: #144848;
            color: white;
            font-weight: bolder;
            margin-bottom: 10px;
            padding-top: 12px;
            border-radius: 10px;
            height: 70px;
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
        }
        .btn-primary:hover {
            background-color: #257b7b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Report</h2>
        <form action="AddReportServlet" method="POST">
            <div class="form-group">
                <label for="patient_id">Patient ID:</label>
                <input type="text" class="form-control" id="patient_id" name="patient_id" required>
            </div>
            <div class="form-group">
                <label for="report_date">Date:</label>
                <input type="date" class="form-control" id="report_date" name="report_date" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Add Report</button>
        </form>
    </div>
</body>
</html>
