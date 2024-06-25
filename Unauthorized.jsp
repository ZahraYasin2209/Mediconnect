<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unauthorized Access</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8d7da;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
            padding: 30px;
            max-width: 600px;
            width: 100%;
            text-align: center;
        }
        h2 {
            color: #721c24;
            font-weight: bolder;
            margin-bottom: 20px;
        }
        .alert {
            font-size: 18px;
            margin-top: 20px;
        }
        .alert-link {
            font-weight: bold;
            color: #004085;
        }
        .alert-link:hover {
            color: #002752;
            text-decoration: underline;
        }
        .btn-login {
            margin-top: 20px;
            background-color: #721c24;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-login:hover {
            background-color: #561417;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Unauthorized Access<br> or <br>Null Session</h2>
        <div class="alert alert-danger" role="alert">
            You do not have permission to access this page. Please <a href="Login.html" class="alert-link">login</a> with the appropriate credentials.
        </div>
        <button class="btn-login" onclick="location.href='Login.html'">Login</button>
    </div>
</body>
</html>
