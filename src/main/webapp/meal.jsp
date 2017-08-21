<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 8/21/17
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
        <a href="new">Add New Meal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="meals">List All Meal</a>
    </h2>
    <div align="center">
    <jsp:useBean id="meal" scope="request" class="ru.javawebinar.topjava.model.Meal"/>
        <c:if test="${meal.id == 0}">
            <form action="new" method="post">
        </c:if>
        <c:if test="${meal.id != 0}">
            <form action="update" method="post">
        </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2><c:if test="${meal.id == 0}">Add new meal.</c:if></h2>
                    <h2><c:if test="${meal.id != 0}">Update meal.</c:if></h2>
                </caption>
                <c:if test="${meal.id != 0}">
                    <input type="hidden" name="id" value="<c:out value="${meal.id}"/>"/>
                </c:if>
                <tr>
                    <th>Description: </th>
                    <td>
                        <input name="description" value="<c:out value='${meal.description}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Date: </th>
                    <td>
                        <input type="datetime-local" name="datetime" value="<c:out value='${meal.dateTime}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Description: </th>
                    <td>
                        <input type="number" min="0" step="1" name="calories" value="<c:out value='${meal.calories}' />"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
            </form>
    </div>
</div>
</body>
</html>
