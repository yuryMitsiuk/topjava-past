<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 8/19/17
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://mycompany.com" prefix="f" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" type="text/css" href="css/mystyle.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table width="80%" align="center">
    <tr>
        <th>Description</th>
        <th>Date</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${mealWithExceeds}" var="meal">
        <tr class="${meal.exceed ? 'red' : 'green'}">
            <td>
                <c:out value="${meal.description}"/>
            </td>
            <td>
                <c:out value="${f:formatLocalDateTime(meal.dateTime)}"/>
            </td>
            <td>
                <c:out value="${meal.calories}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
