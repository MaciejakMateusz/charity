<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- HEADER --%>
<jsp:include page="../../head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header class="header--form-page profile-form-page">

    <%-- MENU --%>
    <jsp:include page="../../menu.jsp"/>
    <%-- END OF MENU --%>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zarządzaj swoimi darami
            </h1>
        </div>

        <div class="profile-form-container">
            <p>Donacja dla fundacji ${donation.institution.name}</p>
            <p>Zawiera ${donation.quantity} sztuk(i) worków z kategorii:</p>
            <ul>
                <c:forEach items="${donation.categories}" var="category">
                    <li>
                        <p>${category.name}</p>
                    </li>
                </c:forEach>
            </ul>
            <div>
                <p>Odbiór z adresu:</p>
                <p>${donation.street}</p>
                <p>${donation.city}</p>
                <p>${donation.zipCode}</p>
                <p>${donation.phoneNumber}</p>
            </div>
            <div>
                <p>Czasu odbioru:</p>
                <p>${donation.pickUpDate} o godzinie ${donation.pickUpTime}</p>
            </div>
            <div>
                <p>Status donacji:</p>
                <c:choose>
                    <c:when test="${donation.pickedUp==true}">
                        <p>Odebrano ${donation.pickedUpDate} o godzinie ${donation.pickedUpTime}</p>
                    </c:when>
                    <c:when test="${donation.pickedUp==false}">
                        <p>Nie odebrano</p>
                    </c:when>
                </c:choose>
            </div>
            <c:if test="${donation.pickedUp==false}">
                <form action="${pageContext.request.contextPath}/donations/details/update-status"
                      method="POST">
                    <input type="hidden" name="id" value="${donation.id}"/>
                    <button type="submit" class="btn">Potwierdź odebranie daru</button>
                </form>
            </c:if>
        </div>
    </div>
</header>

<%-- HEADER --%>
<span id="contact"></span>
<jsp:include page="../../footer.jsp"/>
<%-- END OF HEADER --%>

