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
    <title>Calories Management</title>
    <link rel="stylesheet" type="text/css" href="css/mystyle.css">
</head>
<body>
<div align="center">
    <h1>Meals</h1>
    <h2>
        <a href="index.html">Home</a>
        &nbsp;&nbsp;&nbsp;
        <a href="meal">Add New Meal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="meals">List All Meal</a>
    </h2>
</div>
<table width="80%" align="center">
    <tr>
        <th>Description</th>
        <th>Date</th>
        <th>Calories</th>
        <th colspan="2">Actions</th>
    </tr>
    <c:forEach items="${mealWithExceeds}" var="meal">
        <tr class="${meal.exceed ? 'red' : 'green'}">
            <td style="display: none"><c:out value="${meal.id}"/></td>
            <td>
                <c:out value="${meal.description}"/>
            </td>
            <td>
                <c:out value="${f:formatLocalDateTime(meal.dateTime)}"/>
            </td>
            <td>
                <c:out value="${meal.calories}"/>
            </td>
            <td><a href="get?id=<c:out value="${meal.id}"/>"/>Update</td>
            <td><a href="delete?id=<c:out value='${meal.id}'/>"/>Delete</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
