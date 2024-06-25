<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - MediConnect</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-image: url('images/background.webp');
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
            width: 800px;
            height: 580px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(37, 36, 36, 0.5);
            background-color: #bedafe;
            border-radius: 5%;
            position: relative;
            top: 250px;
            border: 6px solid #16626e;
        }

        .header {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        h2 {
            color: black;
            font-weight: bold;
            font-size: 40px;
        }

        #admin {
            height: 190px;
            width: 190px;
            background-color: #16626e;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
            margin-right: 20px;
        }
        
        #admin img {
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
            width: 100%;
        }

        .action-buttons {
            display: flex;
            flex-wrap: wrap;
            flex-direction: row;
            justify-content: space-between;
            width: 280px;
            border: 2px dotted rgb(13, 82, 132);
            border-radius: 20px;
        }

        .action-buttons .button-container {
            text-align: center;
            margin: 10px;
        }

        .action-buttons button, .action-buttons a {
            width: 250px;
            height: 50px;
            border-radius: 25px;
            font-size: 18px;
            font-weight: 500;
            border: none;
            background-color: #10545f;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .action-buttons button:hover, .action-buttons a:hover {
            background-color: #138496;
            transform: scale(1.05);
        }

        #report {
            position: relative;
            bottom: 200px;
            left: 300px;
            border-radius: 20px;
            height: 150px;
        }

        .action-buttons-report {
            display: flex;
            flex-wrap: wrap;
            flex-direction: row;
            justify-content: space-between;
            width: 280px;
            border-left: 2px solid #0b2121;
            border-radius: 20px;
            position: relative;
            bottom: 400px;
            left: 470px;
        }
        
        

        .action-buttons-report .button-container {
            text-align: center;
            margin: 10px;
        }

        .action-buttons-report button, .action-buttons-report a {
            width: 250px;
            height: 50px;
            border-radius: 25px;
            font-size: 18px;
            font-weight: 500;
            border: none;
            background-color: #2a2b2b;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .action-buttons-report button:hover, .action-buttons-report a:hover {
            background-color: #4a4a4a;
            transform: scale(1.05);
        }

        .action-buttons-appointment {
            display: flex;
            flex-wrap: wrap;
            flex-direction: row;
            justify-content: space-between;
            width: 280px;
            border-radius: 20px;
            position: relative;
            top: 90px;
            left: 500px;
            border-left: 2px solid #2a2b2b;
            border-top: 2px solid #2a2b2b;
        }

        .action-buttons-appointment .button-container {
            text-align: center;
            margin: 10px;
        }

        .action-buttons-appointment button, .action-buttons-appointment a {
            width: 250px;
            height: 50px;
            border-radius: 25px;
            font-size: 18px;
            font-weight: 500;
            border: none;
            background-color: #10545f;
            color: white;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .action-buttons-appointment button:hover, .action-buttons-appointment a:hover {
            background-color: #138496;
            transform: scale(1.05);
        }
		
        .btn-logout {
            padding: 10px 20px;
            background-color: #2b2a2a;
            color: white;
            border: none;
            border-radius: 25px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out, transform 0.3s ease-in-out;
            position: relative;
            bottom: 450px;
            left: 830px;
            width: 160px;
            height: 50px;
            font-size: 20px;
            margin-top: 20px;
        }

        .btn-logout:hover {
            background-color: #343232;
            transform: scale(1.05);
        }

        #settings {
            position: relative;
            bottom: 90px;
            left: 30px;
            height: 100px;
        }
        #set {
            position: relative;
            bottom: 190px;
            left: 130px;
            height: 100px;
        }
        #graph {
            position: relative;
            bottom: 300px;
            left: 320px;
            height: 100px;
            width: 150px;
            box-shadow: 0 0 20px rgba(37, 36, 36, 0.5);
            border-radius: 20px;
        }
        
        #no-underline {
		    text-decoration: none;
		}
		
    </style>
</head>
<body>
    <div class="new">
        <% String role = (String) session.getAttribute("role"); %>
        <% if ("admin".equals(role)) { %>
            <div class="container dashboard-container">
                <div class="header">
                    <div id="admin">
                        <img src="images/admin.png" alt="admin">
                    </div>
                    <div>
                        <h2>ADMIN DASHBOARD</h2>
                        <hr>
                        <span id="h3">Welcome, Admin <%= session.getAttribute("username") %></span>
                    </div>
                </div>

                <div class="action-buttons">
                    <div class="button-container">
                        <a href="ViewAllPatientRecordsServlet" id="no-underline"><i class="fas fa-user-md"></i>&nbsp;&nbsp;View Patients Record</a>
                    </div>
                    <div class="button-container">
                        <a href="SearchPatientRecord.jsp" id="no-underline"><i class="fas fa-search"></i>&nbsp;&nbsp;Search Patient Record</a>
                    </div>
                    <div class="button-container">
                        <a href="DeletePatientRecord.jsp" id="no-underline"><i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Delete Patient Record</a>
                    </div>
                    
                </div>

                <div><img src="images/report.png" id="report"></div>

                <div class="action-buttons-report">
                    <div class="button-container">
                        <a href="ViewReportsServlet" id="no-underline"><i class="fas fa-file-medical"></i>&nbsp;&nbsp;View Patient Reports</a>
                    </div>
                    <div class="button-container">
                        <a href="SearchReport.jsp" id="no-underline"><i class="fas fa-search"></i>&nbsp;&nbsp;Search Patient Reports</a>
                    </div>
                    <div class="button-container">
                        <a href="DeleteReport.jsp" id="no-underline"><i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Delete Patient Reports</a>
                    </div>
         
                
                </div>
            </div>

            <div class="action-buttons-appointment">
                <div class="button-container">
                    <a href="ViewAppointmentsServlet" id="no-underline"><i class="fas fa-calendar-alt"></i>&nbsp;&nbsp;View Appointments</a>
                </div>
                <div class="button-container">
                    <a href="DeleteAppointment.jsp" id="no-underline"><i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Delete Appointment</a>
                </div>
            </div>

            <button class="btn-logout" onclick="location.href='Logout'"><i class="fas fa-sign-out-alt"></i> Logout</button>
            <div><img src="images/settings.png" id="settings"></div> 
            <div><img src="images/set.png" id="set"></div>
            <div><img src="images/graph.png" id="graph"></div>
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
