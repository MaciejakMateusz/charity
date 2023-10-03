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

        <div class="donation-details-grid">

            <div class="institution-area">
                <p>Donacja dla fundacji<br> ${donation.institution.name}</p>
            </div>

            <div class="contents-area">
                <p>Zawiera ${donation.quantity} sztuk(i) worków z kategorii:</p>
                <ul>
                    <c:forEach items="${donation.categories}" var="category">
                        <li>
                            <p>${category.name}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="address-area">
                <div>
                    <p>Odbiór z adresu:</p>
                    <p>${donation.street}</p>
                    <p>${donation.city}</p>
                    <p>${donation.zipCode}</p>
                    <p>${donation.phoneNumber}</p>
                </div>
            </div>

            <div class="date-time-area">
                <p>Czasu odbioru:</p>
                <p>${donation.pickUpDate} o godzinie ${donation.pickUpTime}</p>
                <c:if test="${donation.archived==true}">
                    <p>Czasu archiwizacji:</p>
                    <p>${donation.archivedDate} o godzinie ${donation.archivedTime}</p>
                </c:if>
            </div>

            <div class="status-area">
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
            <div class="buttons-area">
                <c:if test="${donation.archived!=true}">
                    <c:choose>
                        <c:when test="${donation.pickedUp==false}">
                            <form:form action="/donations/details/update-status"
                                       method="POST"
                                       modelAttribute="donation">
                                <form:hidden path="id"/>
                                <button type="submit" class="btn">Potwierdź odebranie</button>
                            </form:form>
                        </c:when>
                        <c:when test="${donation.pickedUp==true}">
                            <form:form action="/donations/details/archive"
                                       method="POST"
                                       modelAttribute="donation">
                                <form:hidden path="id"/>
                                <button type="submit" class="btn">Archiwizuj</button>
                            </form:form>
                        </c:when>
                    </c:choose>
                </c:if>
            </div>
            <c:choose>
                <c:when test="${donation.archived==false}">
                    <a href="${pageContext.request.contextPath}/donations">
                        <button class="btn">Wróć</button>
                    </a>
                </c:when>
                <c:when test="${donation.archived==true}">
                    <a href="${pageContext.request.contextPath}/donations/archived">
                        <button class="btn">Wróć</button>
                    </a>
                </c:when>
            </c:choose>

        </div>
    </div>
</header>

<%-- HEADER --%>
<span id="contact"></span>
<jsp:include page="../../footer.jsp"/>
<%-- END OF HEADER --%>

