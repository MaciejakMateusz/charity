<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- HEADER --%>
<jsp:include page="../../head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header class="header--form-page donations-list-page">

    <%-- MENU --%>
    <jsp:include page="../../menu.jsp"/>
    <%-- END OF MENU --%>

    <div class="donations-list container">
        <div class="slogan--item">
            <h1>
                Zarządzaj swoimi darami
            </h1>
        </div>
    </div>
</header>

<div class="profile-form-container">
    <div class="donation-types-buttons">
        <a href="${pageContext.request.contextPath}/donations">
            <button class="btn">Aktywne donacje</button>
        </a>
        <a href="${pageContext.request.contextPath}/archived-donations">
            <button class="btn">Zakończone donacje</button>
        </a>
    </div>
    <table>
        <thead>
        <tr>
            <th>Dla fundacji</th>
            <th>Data odbioru</th>
            <th>Czas odbioru</th>
            <th>Status</th>
            <th>Zarządzaj donacją</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${archivedDonations}" var="donation">
            <tr>
                <td>${donation.institution.name}</td>
                <td>${donation.pickUpDate}</td>
                <td>${donation.pickUpTime}</td>
                <c:choose>
                    <c:when test="${donation.pickedUp==true}">
                        <td>Odebrano</td>
                    </c:when>
                    <c:when test="${donation.pickedUp==false}">
                        <td>Nie odebrano</td>
                    </c:when>
                </c:choose>
                <td>
                    <form action="${pageContext.request.contextPath}/donations/details"
                          method="POST">
                        <input type="hidden" name="id" value="${donation.id}">
                        <button type="submit">Szczegóły</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%-- HEADER --%>
<span id="contact"></span>
<jsp:include page="../../footer.jsp"/>
<%-- END OF HEADER --%>

