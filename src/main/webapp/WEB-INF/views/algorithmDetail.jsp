<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Algorithm Details</title>
</head>
<body>
<h1>${algorithm.name}</h1>
<p>${algorithm.description}</p>
<h3>Related Algorithms</h3>
<ul>
    <c:forEach var="link" items="${algorithm.links}">
        <c:if test="${!link.rel == 'self'}">
            <li>
                <a href="<c:url value='${link.href}'/>">${link.rel}</a>
            </li>
        </c:if>
    </c:forEach>
</ul>
<a href="<c:url value='/'/>">Back to List</a>
</body>
</html>
