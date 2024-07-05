<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Algorithm Details</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
            padding: 20px;
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
        ul li a {
            color: #3498db;
            text-decoration: none;
        }
        ul li a:hover {
            text-decoration: underline;
        }
        form {
            margin-top: 20px;
        }
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button {
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #2980b9;
        }
    </style>
    <script>
        $(document).ready(function() {
            var algorithmName = '<c:out value="${name}" />'; // Getting the algorithm name from the model attribute

            // Fetch algorithm details
            $.ajax({
                url: '/api/algorithms/' + algorithmName,
                method: 'GET',
                success: function(data) {
                    $('#algorithm-name').text(data.name);
                    $('#algorithm-description').text(data.description);
                    var links = data._links;
                    var relatedAlgorithms = '';
                    for (var key in links) {
                        if (key !== 'self') {
                            relatedAlgorithms += '<li><a href="/algorithm-details/' + key + '">' + key + '</a></li>';
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
                    url: '/api/algorithms/' + algorithmName + '/sort',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ array: array }),
                    success: function(response) {
                        $('#sorted-array').text('Sorted Array: ' + response.sortedArray.join(', '));
                    },
                    error: function(error) {
                        console.log('Error sorting array:', error);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container">
    <h1 id="algorithm-name"></h1>
    <p id="algorithm-description"></p>
    <h3>Related Algorithms</h3>
    <ul id="related-algorithms"></ul>
    <form id="sort-form">
        <input type="text" id="array-input" placeholder="Enter array elements separated by commas" required>
        <button type="submit">Sort</button>
    </form>
    <p id="sorted-array"></p>
    <a href="<c:url value='/'/>">Back to List</a>
</div>
</body>
</html>