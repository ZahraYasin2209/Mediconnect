<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Report</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #d5ebff;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .modal-content {
            border-radius: 15px;
            box-shadow: 0 4px 18px rgba(0, 0, 0, 0.2);
        }
        .modal-header {
            background-color: #144848;
            color: white;
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
        }
        .modal-title {
            font-weight: bolder;
        }
        .form-group label {
            font-weight: bold;
            font-size: 17px;
        }
        .form-control {
            border-radius: 5px;
        }
        .btn-primary {
            background-color: #144848;
            border: none;
            border-radius: 15px;
            padding: 7px 0;
            width: 100%;
            font-size: 17px;
            font-weight: bolder;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .btn-primary:hover {
            background-color: #257b7b;
        }
    </style>
</head>
<body>
    <!-- Search Report Modal -->
    <div class="modal fade show" id="searchReportModal" tabindex="-1" role="dialog" aria-labelledby="searchReportModalLabel" aria-hidden="true" style="display: block;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="searchReportModalLabel">Search Report</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="SearchReportServlet" method="GET">
                        <div class="form-group">
                            <label for="searchReportId">Report ID:</label>
                            <input type="text" class="form-control" id="searchReportId" name="id" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap and jQuery scripts -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!-- Script to open the modal automatically for demonstration -->
    <script>
        $(document).ready(function() {
            $('#searchReportModal').modal('show');
        });
    </script>
</body>
</html>
