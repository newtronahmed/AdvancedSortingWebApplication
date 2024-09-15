<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Algorithm Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
</head>
<body>
<div class="container">
    <h1 class="text-center">${algorithm.name}</h1>
    <p>${algorithm.description}</p>

    <h3>Related Algorithms</h3>
    <ul id="related-algorithms">
        <c:forEach var="relatedAlgorithm" items="${relatedAlgorithms}">
            <li>
                <a href="<c:url value='/algorithms/${relatedAlgorithm.name}'/>" class="btn btn-success algorithm-button">
                        ${relatedAlgorithm.name}
                </a>
            </li>
        </c:forEach>
    </ul>

    <form id="sort-form" class="mt-3">
        <input type="text" id="array-input" class="form-control mb-3" placeholder="Enter array elements separated by commas">
        <button type="submit" class="btn btn-primary btn-block">Sort</button>
    </form>

    <h3 id="sorted-array" class="my-4"></h3>

    <p id="error" class="text-red"></p>
    <a href="<c:url value='/'/>" class="btn btn-link">Back to List</a>
</div>

<script>
    // Handle form submission on the client-side
    document.getElementById('sort-form').addEventListener('submit', function(event) {
        event.preventDefault();

        var arrayInput = document.getElementById('array-input').value;
        var array = arrayInput.split(',').map(Number);

        // Validate array input
        if (array.some(isNaN)) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Please enter a valid array of numbers.'
            });
            return;
        }

        // Send sorting request
        fetch('/api/sort/${algorithm.name}', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ array: array })
        })
            .then(response => response.json())
            .then(data => {
                document.getElementById('sorted-array').innerHTML = 'Sorted Array: ' + data.sortedArray.join(', ');
            })
            .catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Something went wrong!'
                });
                console.error('Error:', error);
            });
    });
</script>
</body>
</html>
