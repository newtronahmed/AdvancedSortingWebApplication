<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sorting Algorithms</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        p {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
        }
        .link-button {
            display: inline-block;
            padding: 10px 20px;
            border: 1px solid #007bff;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            transition: background-color 0.3s, border-color 0.3s;
        }
        .link-button:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .card {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sorting Algorithms</h1>
    <p>Explore different sorting algorithms and visualize how they work. Select an algorithm to sort an array of numbers in various ways.</p>
    <div class="list-group" id="algorithm-list">
    </div>
    <button type="button" class="btn btn-primary mt-3" data-toggle="modal" data-target="#addAlgorithmModal">
        Add Algorithm
    </button>
</div>

<!-- Add Algorithm Modal -->
<div class="modal fade" id="addAlgorithmModal" tabindex="-1" role="dialog" aria-labelledby="addAlgorithmModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addAlgorithmModalLabel">Add New Algorithm</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="add-algorithm-form">
                    <div class="form-group">
                        <label for="algorithm-name">Algorithm Name</label>
                        <input type="text" class="form-control" id="algorithm-name" required>
                    </div>
                    <div class="form-group">
                        <label for="algorithm-description">Description</label>
                        <textarea class="form-control" id="algorithm-description" rows="3" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Algorithm</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        // Load algorithms on page load
        loadAlgorithms();

        // Handle form submission
        $('#add-algorithm-form').on('submit', function(event) {
            event.preventDefault();
            var algorithmName = $('#algorithm-name').val();
            var algorithmDescription = $('#algorithm-description').val();

            $.ajax({
                url: '/api/addAlgorithm',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    name: algorithmName,
                    description: algorithmDescription
                }),
                success: function(data) {
                    $('#addAlgorithmModal').modal('hide');
                    loadAlgorithms();
                },
                error: function(xhr, status, error) {
                    console.error('Error adding algorithm:', error);
                }
            });
        });

        // Function to load algorithms
        function loadAlgorithms() {
            $.ajax({
                url: '/api',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    var algorithmList = $('#algorithm-list');
                    algorithmList.empty();
                    data.forEach(function(algorithm) {
                        var algorithmCard = '<div class="card"><div class="card-body"><h5 class="card-title">' + algorithm.name + '</h5><p class="card-text">' + algorithm.description + '</p><a class="link-button" href="/algorithmDetails/' + algorithm.name + '">Try Now</a></div></div>';
                        algorithmList.append(algorithmCard);
                    });
                },
                error: function(xhr, status, error) {
                    console.error('Error fetching algorithms:', error);
                }
            });
        }
    });
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
