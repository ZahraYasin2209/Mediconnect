<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patient Dashboard - MediConnect</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #969686;
            background-size: cover;
            background-position: center;
            height: 100vh;
            margin: 0;
            display: flex;
            font-family: 'Roboto', sans-serif;
            justify-content: center;
            align-items: center;
            overflow: hidden;
        }

        .red-circle {
            width: 25px;
            height: 25px;
            background-color: red;
            border-radius: 50%;
            margin-right: 15px;
            position: relative;
            bottom: 3px;
        }

        .dashboard-container {
            width: 700px;
            border-radius: 10px;
            padding-top: 20px;
            max-height: 580px;
            margin-top: 35px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            background-color: rgb(235, 235, 235);
            border-radius: 5%;
            position: relative;
            bottom: 15px;
            box-shadow: 1px 1px 5px 5px rgba(53, 48, 48, 0.4);
            
        }

        .header {
            display: flex;
            align-items: center;
        }

        h2 {
            color: black;
            font-weight: bold;
            font-size: 40px;
        }

        .sub-header {
            display: flex;
            align-items: center;
            margin-top: 10px;
        }

        .blue-circle {
            width: 15px;
            height: 15px;
            background-color: rgb(150, 50, 50);
            border-radius: 50%;
            margin-right: 15px;
            position: relative;
            bottom: 3px;
            left: 3px;
        }

        h3 {
            color: black;
            font-weight: bold;
            font-size: 23px;
            padding-left: 10px;
        }

        .action-buttons {
            display: flex;
            flex-direction: row;
            align-items: center;
            margin-top: 20px;
            position: relative;
            bottom: 930px;
            width: 490px;
            
        }

        .action-buttons .button-container {
            text-align: center;
        }

        .action-buttons button {
            width: 150px;
            height: 40px;
            margin: 10px 5px;
            padding: 5px;
            border-radius: 25px;
            font-size: 18px;
            font-weight: 500;
            border: none;
            background-color: #188697;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .action-buttons button:hover {
            background-color: #138496;
            transform: scale(1.05);
        }

        .action-buttons h4 {
            margin-bottom: 10px;
            font-size: 16px;
            font-weight: bold;
            color: black;
            border-bottom: 2px solid grey;
            border-radius: 20px;;
        }

        #symbol {
            position: relative;
            right: 270px;
            height: 300px;
            width: 260px;
            top: 10px;
        }

        #icon {
            position: relative;
            right: 120px;
            height: 95px;
            width: 95px;
        }

        #pin {
            position: relative;
            left: 730px;
            bottom: 380px;
            height: 385px;
            width: 140px;
        }

        #wheel {
            width: 150px;
            height: 150px;
            position: relative;
            bottom: 880px;
            left: 470px;
        }
        
        #buttons
        {
            width: 160px;
            height: 290px;
            position: relative;
            bottom: 928px;
            box-shadow: 0 0 5px 0 rgba(53, 48, 48, 0.4);
            border-radius: 20px;;
            
        }


        .action-buttons-appointments {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            margin-top: 20px;
            position: relative;
            bottom: 1220px;
            left: 140px;
            width: 390px;
            justify-content: center;
        }


        .action-buttons-appointments .button-container {
            text-align: center;
        }

        .action-buttons-appointments button {
            width: 150px;
            height: 40px;
            margin: 10px 5px;
            padding: 5px;
            border-radius: 25px;
            font-size: 18px;
            font-weight: 500;
            border: none;
            background-color: #3e4040;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .action-buttons-appointments button:hover {
            background-color: #a09a9a;
            transform: scale(1.05);
            color: black;
        }

        .action-buttons-appointments h4 {
            margin-bottom: 10px;
            font-size: 16px;
            font-weight: bold;
            color: black;
            border-bottom: 2px solid grey;
            border-radius: 20px;;
        }

        #mini-buttons
        {
            height: 60px;
            width: 210px;
            position: relative;
            bottom: 1220px;
            left: 230px;
        }

        .action-buttons-report {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            margin-top: 20px;
            position: relative;
            bottom: 1420px;
            left: 510px;
            width: 160px;
            justify-content: center;
            border-left: 2px solid #16626e;
            border-bottom: 2px solid #16626e;
            border-top: 2px solid #16626e;
            border-radius: 20px;
        }


        .action-buttons-report .button-container {
            text-align: center;
        }

        .action-buttons-report button {
            width: 150px;
            height: 80px;
            margin: 10px 5px;
            padding: 5px;
            border-radius: 25px;
            font-size: 18px;
            font-weight: 500;
            border: none;
            background-color: #16626e;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 50px;
        }

        .action-buttons-report button:hover {
            background-color: #138496;
            transform: scale(1.05);
        }

        .action-buttons-report h4 {
            margin-bottom: 10px;
            font-size: 16px;
            font-weight: bold;
            border-radius: 20px;;
        }

        #blue-buttons
        {
            width: 140px;
            height: 140px;
            position: relative;
            bottom: 1760px;
            left: 510px;
            box-shadow: 0 0 5px 0 rgba(53, 48, 48, 0.4);
            border-radius: 20px;;
        }

        .btn-logout {
            padding: 10px 20px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 25px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            position: relative;
            left: 360px;
            width: 160px;
            height: 50px;
            top: 50px;
            font-size: 20px;
        }

        .btn-logout:hover {
            background-color: #c82333;
            transform: scale(1.05);
        }

    </style>
</head>
<body>
    <div class="new">
        <% String role = (String) session.getAttribute("role"); %>
        <% if ("patient".equals(role)) { %>
        <div class="container dashboard-container">
        <div class="header">
            <div class="red-circle"></div>
            <h2>Patient Dashboard</h2>
            <button class="btn-logout" onclick="location.href='Logout'"><i class="fas fa-sign-out-alt"></i> Logout</button>
        </div>
        <div class="sub-header">
            <div class="blue-circle"></div>
            <h3>Welcome, <%= session.getAttribute("username") %></h3>
        </div>
        
        <div><img src="images/icon.png" alt="Icon" id="icon"></div>
        <div><img src="images/symbol.png" alt="Symbol" id="symbol"></div>
        <div><img src="images/pin.png" alt="Pin" id="pin"></div>
        <div><img src="images/wheel.png" alt="Wheel" id="wheel"></div>

        <div class="action-buttons">
            <div class="button-container">
                <button onclick="location.href='InsertPatientRecord.jsp'"><i class="fas fa-plus-circle"></i>&nbsp;&nbsp;Insert</button>
                <h4>Inserting<br> Patient<br> Record</h4>
            </div>
            <div class="button-container">
                <button onclick="location.href='UpdatePatientRecord.jsp'"><i class="fas fa-edit"></i>&nbsp;&nbsp;Update</button>
                <h4>Updating<br> Patient<br> Record</h4>
                
            </div>
            <div class="button-container">
                <button onclick="location.href='ViewPatientRecordServlet'"><i class="fas fa-eye"></i>&nbsp;&nbsp;View</button>
                <h4>Viewing<br> Patient<br> Record</h4>
            </div>
        </div>

        <div><img src="images/multibuttons.png" alt="buttons" id="buttons"></div>

        <div class="action-buttons-appointments">
            <div class="button-container">
                <button onclick="location.href='MakeAppointment.jsp'"><i class="fas fa-calendar-plus"></i>&nbsp;&nbsp;Make</button>
                <h4>Making<br> Appointment</h4>
            </div>
            <div class="button-container">
                <button onclick="location.href='ViewAppointmentsServlet'"><i class="fas fa-eye"></i>&nbsp;&nbsp;View</button>
                <h4>Viewing<br> Appointments</h4>
            </div>
            <div class="button-container">
                <button onclick="location.href='UpdateAppointment.jsp'"><i class="fas fa-edit"></i>&nbsp;&nbsp;Update</button>
                <h4>Updating<br> Appointment</h4>
            </div>
            <div class="button-container">
                <button onclick="location.href='DeleteAppointment.jsp'"><i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Delete</button>
                <h4>Deleting<br> Appointment</h4>
            </div>
        </div>

        <div><img src="images/minibuttons.png" alt="buttons" id="mini-buttons"></div>

        <div class="action-buttons-report">
            <div class="button-container">
                <button onclick="location.href='ViewPatientReportsServlet'"><i class="fas fa-file-medical-alt"></i>View<br> &nbsp;&nbsp;Reports</button>
                <h4>View<br>My Reports</h4>
            </div>
        </div>

        <div><img src="images/btnblue.png" alt="buttons" id="blue-buttons"></div>
    </div>
    <% } else { %>
        <%
            response.sendRedirect("Login.html");
        %>
    <% } %>
</div>

    <!-- Include jQuery and Bootstrap JS for better compatibility and functionality -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
