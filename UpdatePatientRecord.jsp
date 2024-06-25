<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Patient Record</title>
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
            padding: 20px;
            max-width: 500px;
            width: 100%;
            position: relative;
        }
        h2 {
            text-align: center;
            background-color: #144848;
            color: white;
            font-weight: bolder;
            margin-bottom: 7px;
            margin-top: 0px;
            border-radius: 10px;;
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
        <h2>Update Patient Record</h2>
        <form action="UpdatePatientRecordServlet" method="POST">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="<%= session.getAttribute("username") %>" readonly>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= session.getAttribute("email") %>" readonly>
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input type="number" class="form-control" id="age" name="age" required>
            </div>
            <div class="form-group">
                <label for="gender">Gender:</label>
                <div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="male" value="Male" required>
                        <label class="form-check-label" for="male">Male</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="female" value="Female" required>
                        <label class="form-check-label" for="female">Female</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="contact">Contact:</label>
                <input type="text" class="form-control" id="contact" name="contact" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" id="address" name="address" required>
            </div>
            <button type="submit" class="btn btn-warning">Update</button>
        </form>
    </div>
</body>
</html>
