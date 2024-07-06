<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Algorithm Details</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
        }
        h1 {
            color: #333;
        }
        p {
            color: #555;
        }
        h3 {
            color: #444;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        ul li {
            margin: 5px 0;
        }
        form {
            margin-top: 20px;
        }
        .text-red {
            color: red;
            padding: 5px;
        }
        .algorithm-button {
            margin: 5px;
            padding: 10px 20px;
            background-color: #2ecc71;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .algorithm-button:hover {
            background-color: #27ae60;
        }
    </style>
    <script>
        $(document).ready(function() {
            var algorithmName = '<c:out value="${name}" />'; // Getting the algorithm name from the model attribute

            // Fetch algorithm details
            $.ajax({
                url: '/api/' + algorithmName,
                method: 'GET',
                success: function(data) {
                    $('#algorithm-name').text(data.name);
                    $('#algorithm-description').html(data.description);
                    var links = data.links;
                    var relatedAlgorithms = '';
                    for (var key in links) {
                        if (key !== 'self') {
                            relatedAlgorithms += '<li><button class="btn btn-success algorithm-button" data-url="' + links[key].href + '">' + key + '</button></li>';
                        }
                    }
                    $('#related-algorithms').html(relatedAlgorithms);
                },
                error: function(error) {
                    console.log('Error fetching algorithm details:', error);
                }
            });

            // Handle form submission
            $('#sort-form').submit(function(event) {
                event.preventDefault();
                var arrayInput = $('#array-input').val();
                var array = arrayInput.split(',').map(function(item) {
                    return item.trim();
                });
                $.ajax({
                    url: '/api/sort/' + algorithmName,
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ array: array }),
                    success: function(response) {
                        $('#sorted-array').text('Sorted Array: ' + response.sortedArray.join(', '));

                        var links = response.links;
                        var relatedAlgorithms = '';
                        for (var key in links) {
                            if (key !== 'self') {
                                relatedAlgorithms += '<li><button class="btn btn-success algorithm-button" data-url="' + links[key].href + '">' + links[key].rel + '</button></li>';
                            }
                        }
                        $('#related-algorithms').html(relatedAlgorithms);
                    },
                    error: function(error) {
                        $('#error').text('There was an error while processing your request, kindly check your input');
                    }
                });
            });

            // Handle algorithm button clicks
            $(document).on('click', '.algorithm-button', function() {
                var arrayInput = $('#array-input').val();
                var array = arrayInput.split(',').map(function(item) {
                    return item.trim();
                });
                var url = $(this).data('url');
                $.ajax({
                    url: url,
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ array: array }),
                    success: function(response) {
                        $('#sorted-array').text('Sorted Array: using '+ response.algorithm + ' ' + response.sortedArray.join(', '));
                    },
                    error: function(error) {
                        $('#error').text('There was an error while processing your request, kindly check your input');
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container">
    <h1 id="algorithm-name" class="text-center"></h1>
    <p id="algorithm-description"></p>
    <h3>Related Algorithms</h3>
    <ul id="related-algorithms"></ul>
    <form id="sort-form" class="mt-3">
        <input type="text" id="array-input" class="form-control mb-3" placeholder="Enter array elements separated by commas" required>
        <button type="submit" class="btn btn-primary btn-block">Sort</button>
    </form>
    <p id="sorted-array" class="mt-3"></p>
    <p id="error" class="text-red"></p>
    <a href="<c:url value='/'/>" class="btn btn-link">Back to List</a>
</div>
</body>
</html>
