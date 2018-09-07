<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.09.2018
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>Компьютерные запчасти</title>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>Список запчастей</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">
            <input type="button" value="Добавить деталь" onclick="window.location.href='showFormForAdd';return false;"
                   class="add-button"/>
            <br/>
            <form:form action="/part/search" method="post">
                Искать деталь: <input type="text" name="searchName"/>
                <input type="submit" value="Поиск" class="add-button"/>
            </form:form>
            <br/>
            <c:url value="/part/filterPerUse" var="useList">
                <c:param name="filterParam" value="use"/>
            </c:url>
            <c:url value="/part/filterPerUse" var="notUseList">
                <c:param name="filterParam" value="notUse"/>
            </c:url>
            <c:url value="/part/list" var="list"/>
            Фильтр: <a href="${list}">Все</a>  ||   <a href="${useList}">Для сборки</a>   ||   <a href="${notUseList}">Опция</a>
            <table>
                <tbody>
                <tr>
                    <th>Наименование</th>
                    <th>Необходимость</th>
                    <th>Количество</th>
                    <th>Действие</th>
                </tr>

                <c:forEach var="part" items="${listOfparts}">
                    <c:url var="deletePart" value="/part/deletePart">
                        <c:param name="partId" value="${part.id}"/>
                    </c:url>

                    <c:url var="updatePart" value="/part/updatePart">
                        <c:param name="partId" value="${part.id}"/>
                    </c:url>
                    <tr>
                        <td id="leftMargin">${part.computePart}</td>
                        <td>
                            <c:if test="${part.isNeeded==null}">нет</c:if>
                            <c:if test="${part.isNeeded!=null}">да</c:if>
                        </td>
                        <td>${part.quantity}</td>
                        <td><a href="${deletePart}">Удалить</a>||<a href="${updatePart}">Редактировать</a></td>
                    </tr>
                </c:forEach>
                <c:if test="${isCount != null}">
                    <tr><td></td><td></td><td></td><td></td></tr>
                    <tr>
                        <td>
                            Можно собрать
                        </td>
                        <td>${count}</td>
                        <td>компьютеров</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <br/>
        <c:if test="${isPage!=null}">
            <c:choose>

                <c:when test="${currentPage == 1}">
                    <a href="#" class="disabled">Начало</a>
                    <a href="#" class="disabled">Предыдущая</a>
                </c:when>
                <c:otherwise>
                    <c:url var="firstPageLink" value="/part/list">
                        <c:param name="pageNumber" value="1" />
                    </c:url>
                    <a href="${firstPageLink}">Начало</a>

                    <c:url var="prevPageLink" value="/part/list">
                        <c:param name="pageNumber" value="${currentPage - 1}" />
                    </c:url>
                    <a href="${prevPageLink}">Предыдущая</a>
                </c:otherwise>

            </c:choose>


            <c:forEach begin="1" end="${totalCustomerCount}" step="${pageSize}" varStatus="loop">
                <c:choose>

                    <c:when test="${loop.count > (currentPage-5) && loop.count <= (currentPage+5) || (currentPage <= 5 && loop.count <= 10) || totalPages <=10}">

                        <c:url var="pageLink" value="/part/list">
                            <c:param name="pageNumber" value="${loop.count}" />
                        </c:url>

                        <c:choose>
                            <c:when test="${loop.count == currentPage}">
                                <a href="${pageLink}" class="selected-page">${loop.count}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageLink}">${loop.count}</a>
                            </c:otherwise>
                        </c:choose>

                    </c:when>

                </c:choose>
            </c:forEach>


            <c:choose>
                <c:when test="${currentPage < (totalPages-5) && (totalPages > 10)}">
                    ...
                </c:when>
            </c:choose>


            <c:choose>

                <c:when test="${currentPage == totalPages}">
                    <a href="#" class="disabled">Следующая</a>
                    <a href="#" class="disabled">Конец</a>
                </c:when>

                <c:otherwise>
                    <c:url var="nextPageLink" value="/part/list">
                        <c:param name="pageNumber" value="${currentPage + 1}" />
                    </c:url>
                    <a href="${nextPageLink}">Следующая</a>

                    <c:url var="lastPageLink" value="/part/list">
                        <c:param name="pageNumber" value="${totalPages}" />
                    </c:url>
                    <a href="${lastPageLink}">Конец</a>
                </c:otherwise>

            </c:choose>
        </c:if>
        </div>
    </div>

</body>
</html>
