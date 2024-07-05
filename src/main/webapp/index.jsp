<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sorting Algorithms</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
        a:hover {
            color: #0056b3;
        }
        .link-button {
            display: inline-block;
            padding: 10px 20px;
            border: 1px solid #007bff;
            border-radius: 5px;
            background-color: #007bff;
            color: #fff;
            transition: background-color 0.3s, border-color 0.3s;
        }
        .link-button:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sorting Algorithms</h1>
    <ul class="" id="algorithm-list">
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Bubble Sort</a></li>--%>
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Selection Sort</a></li>--%>
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Insertion Sort</a></li>--%>
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Merge Sort</a></li>--%>
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Quick Sort</a></li>--%>
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Heap Sort</a></li>--%>
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Radix Sort</a></li>--%>
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Bucket Sort</a></li>--%>
<%--        <li><a class="link-button" href="${pageContext.request.contextPath}/api/hello">Shell Sort</a></li>--%>

    </ul>
</div>
<script>
    $(document).ready(function() {
        // Make an AJAX call to the /api/algorithms endpoint
        $.ajax({
            url: '/api/algorithms',
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                // Iterate over the list of algorithms and append them to the UL
                var algorithmList = $('#algorithm-list');
                data.forEach(function(algorithm) {
                    algorithmList.append('<li><a class="link-button" href="/algorithmDetails/' + algorithm.name + '">' + algorithm.name + '</a></li>');
                });
            },
            error: function(xhr, status, error) {
                console.error('Error fetching algorithms:', error);
            }
        });
    });
</script>
</body>
</html>

