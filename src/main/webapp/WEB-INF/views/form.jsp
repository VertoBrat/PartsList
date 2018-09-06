<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.09.2018
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/form.css">

    <title>Добавить запчасть</title>
    <style>
        .error{color: red}
    </style>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Добавить запчасть</h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <form:form modelAttribute="newPart" action="/part/addPartToDataBase" method="post">
            <form:hidden path="id"/>
           (*) - поле, обязательное для заполнения
            <table id="form">
                <tbody>
                <tr>
                    <td><label>Наименование(*)</label></td>
                    <td><form:input path="computePart"/></td>
                    <td><form:errors path="computePart" cssClass="error"/> </td>
                </tr>
                <tr>
                    <td><label>Количество</label></td>
                    <td><form:input path="quantity"/></td>

                </tr>
                <tr>
                    <td><label>Необходимость</label></td>
                    <td><form:checkbox path="isNeeded" value="1"/> </td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Добавить" class="add-button"/></td>
                </tr>
                </tbody>
            </table>
            <p>
                <a href="/part/list">Вернуться к списку</a>
            </p>
        </form:form>

    </div>
</div>

</body>
</html>
