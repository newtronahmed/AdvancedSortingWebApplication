<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Algorithm Details</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Include SweetAlert2 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">

    <!-- Include SweetAlert2 JS -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

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
        #sorted-array {
            font-weight: bolder;
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

                if (!arrayInput) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Please enter an array to sort.'
                    });
                    return;
                }

                var array = [];
                try {
                    array = arrayInput.split(',').map(function(item) {
                        var trimmedItem = item.trim();
                        if (isNaN(trimmedItem)) {
                            Swal.fire({
                                icon: 'error',
                                title: 'Oops...',
                                text: 'All elements in the array must be numbers.'
                            });
                            throw new Error('Invalid input: Non-numeric value');
                        }
                        return parseInt(trimmedItem, 10);
                    });
                } catch (error) {
                    // Exit if input is invalid
                    return;
                }

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
                            if (links[key].rel !== 'self') {
                                relatedAlgorithms += '<li><button class="btn btn-success algorithm-button" data-url="' + links[key].href + '">' + links[key].rel + '</button></li>';
                            }
                        }
                        $('#related-algorithms').html(relatedAlgorithms);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status === 400) {
                            Swal.fire({
                                icon: 'error',
                                title: 'Client Error',
                                text: jqXHR.responseJSON.message
                            });
                        } else if (jqXHR.status === 500) {
                            Swal.fire({
                                icon: 'error',
                                title: 'Internal Server Error',
                                text: jqXHR.responseJSON.message
                            });
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: 'Unexpected Error',
                                text: textStatus
                            });
                        }
                        // $('#error').text('There was an error while processing your request, kindly check your input');
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
                        $('#sorted-array').html('<h3>Sorted Array: using '+ response.algorithm + ' <span style="color: blue">' + response.sortedArray.join(', ') + '</span></h3>');
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

    <form id="sort-form" class="mt-3">
        <input type="text" id="array-input" class="form-control mb-3" placeholder="Enter array elements separated by commas" >
        <button type="submit" class="btn btn-primary btn-block">Sort</button>
    </form>
    <h3 id="sorted-array"  class="my-4"></h3>
    <h6 class="my-4">Try With other algorithms</h6>
    <ul id="related-algorithms" style="display: flex"></ul>
<%--    <p id="sorted-array"  class="mt-3"></p>--%>


    <p id="error" class="text-red"></p>
    <a href="<c:url value='/'/>" class="btn btn-link">Back to List</a>
</div>
</body>
</html>
