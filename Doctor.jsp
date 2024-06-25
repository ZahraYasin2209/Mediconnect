<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctor Dashboard</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #c6c6b1;
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

        .dashboard-container {
            width: 700px;
            border-radius: 10px;
            padding-top: 5px;
            max-height: 580px;
            margin-top: 35px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            background-color: rgb(235, 235, 235);
            border-radius: 5%;
            position: relative;
            box-shadow: 1px 1px 5px 5px rgba(53, 48, 48, 0.4);
            border: 10px solid #0d3d45;
            top: 8px;
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

        #doctor {
            height: 190px;
            width: 190px;
            background-color: #16626e;
            border-radius: 70%;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
        }
        
        #doctor img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-radius: 50%;
        }

        #h3 {
            color: black;
            font-weight: bold;
            font-size: 23px;
            position: relative;
            bottom: 20px;
        }

        hr {
            border: 1px solid grey;
        }

        .action-buttons {
            display: flex;
            flex-direction: row;
            align-items: center;
            margin-top: 10px;
            position: relative;
            bottom: 800px;
            width: 200px;
            border-radius: 20px;
            position: relative;
            left: 10px;
        }

        .action-buttons .button-container {
            text-align: center;
        }

        .action-buttons button, .action-buttons a {
            width: 230px;
            height: 50px;
            border-radius: 20px;
            font-size: 18px;
            font-weight: 500;
            border: none;
            background-color: #10545f;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
            text-decoration: none;
        }

        .action-buttons button:hover, .action-buttons a:hover {
            background-color: #138496;
            transform: scale(1.05);
        }

        #doc-pic {
            position: relative;
            right: 285px;
            height: 430px;
            width: 260px;
            bottom: 120px;
        }

        #tablet {
            position: relative;
            left: 675px;
            bottom: 480px;
            height: 385px;
            width: 200px;
        }

        .action-buttons-appointments {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            margin-top: 20px;
            padding-top: 20px;
            position: relative;
            bottom: 790px;
            left: 3px;
            width: 245px;
            justify-content: center;
            border: 2px dotted rgb(114, 108, 108);
            border-radius: 25px;
        }

        .action-buttons-appointments .button-container {
            text-align: center;
        }

        .action-buttons-appointments button, .action-buttons-appointments a {
            width: 230px;
            height: 50px;
            border-radius: 20px;
            font-size: 18px;
            font-weight: 500;
            border: none;
            background-color: #3e4040;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
            text-decoration: none;
        }

        .action-buttons-appointments button:hover, .action-buttons-appointments a:hover {
            background-color: #a09a9a;
            transform: scale(1.05);
            color: black;
        }
        
        #emoji {
            height: 170px;
            width: 230px;
            position: relative;
            bottom: 1130px;
            left: 290px;
            background-color: rgb(180, 180, 180);
            border-radius: 20px;
            border: 2px solid grey;
        }

        .action-buttons-report {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            margin-top: 20px;
            position: relative;
            bottom: 1120px;
            left: 225px;
            width: 450px;
            margin: 10px;
            justify-content: center;
        }

        .action-buttons-report .button-container {
            text-align: center;
            margin: 10px;
        }

        .action-buttons-report button, .action-buttons-report a {
            width: 190px;
            height: 50px;
            border-radius: 20px;
            font-size: 17px;
            font-weight: 500;
            border: none;
            background-color: #16626e;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 50px;
            text-decoration: none;
        }

        .action-buttons-report button:hover, .action-buttons-report a:hover {
            background-color: #138496;
            transform: scale(1.05);
        }

        #circle {
            position: relative;
            bottom: 1440px;
            left: 540px;
            height: 120px;
            width: 100px;
        }

        #car {
            position: relative;
            bottom: 1675px;
            left: 520px;
            height: 90px;
            width: 130px;
        }

        .btn-logout {
            padding: 10px 20px;
            background-color: #393536;
            color: white;
            border: none;
            border-radius: 25px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            position: relative;
            left: 740px;
            width: 160px;
            height: 50px;
            bottom: 500px;
            font-size: 20px;
        }

        .btn-logout:hover {
            background-color: #343232;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <div class="new">
        <% String role = (String) session.getAttribute("role"); %>
        <% if ("doctor".equals(role)) { %>
        <div class="container dashboard-container">
            <div class="header">
                <div id="doctor">
                    <img src="images/doctor-rounded.png" alt="doctor">
                </div>
                <h2>&nbsp;&nbsp;DOCTOR DASHBOARD<br><hr><span id="h3">&nbsp;&nbsp;&nbsp;&nbsp;Welcome, Dr. <%= session.getAttribute("username") %></span></h2>
            </div>

            <div><img src="images/doc-pic.png" alt="Symbol" id="doc-pic"></div>
            <div><img src="images/tablet.png" alt="Pin" id="tablet"></div>

            <div class="action-buttons">
                <div class="button-container">
                    <a href="ViewAllPatientRecordsServlet" class="btn btn-info">&nbsp;&nbsp;View Patients Record</a>
                </div>
            </div>

            <div class="action-buttons-appointments">
                <div class="button-container">
                    <a href="ViewAppointmentsServlet" class="btn btn-info">View Appointments</a>
                </div>
                <div class="button-container">
                    <a href="UpdateAppointment.jsp" class="btn btn-info">Update Appointment</a>
                </div>
                <div class="button-container">
                    <a href="DeleteAppointment.jsp" class="btn btn-info">Delete Appointment</a>
                </div>
            </div>

            <div><img src="images/emoji.png" alt="Emoji" id="emoji"></div>

            <div class="action-buttons-report">
                    <div class="button-container">
                    	<a href="AddReport.jsp" class="btn btn-info">Add Patient Reports</a>
                	</div>
                    <div class="button-container">
                    	<a href="ViewReportsServlet" class="btn btn-info">View Patient Reports</a>
                	</div>
                	<div class="button-container">
                    	<a href="UpdateReport.jsp" class="btn btn-info">Update Patient Report</a>
                	</div>
                    
                    <div class="button-container">
                    	<a href="DeleteReport.jsp" class="btn btn-info">Delete Patient Reports</a>
                	</div>
            </div>

            <div><img src="images/circle.png" alt="Emoji" id="circle"></div>
            <div><img src="images/car.png" alt="Ambulance" id="car"></div>
        </div>

        <button class="btn-logout" onclick="location.href='Logout'"><i class="fas fa-sign-out-alt"></i> Logout</button>
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
