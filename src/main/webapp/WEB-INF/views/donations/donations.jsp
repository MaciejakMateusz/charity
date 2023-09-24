<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- HEADER --%>
<jsp:include page="../head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header class="header--form-page profile-form-page">

    <%-- MENU --%>
    <jsp:include page="../menu.jsp"/>
    <%-- END OF MENU --%>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zarządzaj swoimi darami
            </h1>
        </div>

        <div class="profile-form-container">
            <ul>
                <c:forEach items="${donations}" var="donation">
                    <li>
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
                                    <p>Odebrano</p>
                                </c:when>
                                <c:when test="${donation.pickedUp==false}">
                                    <p>Nie odebrano</p>
                                </c:when>
                            </c:choose>
                        </div>
                    </li>
                    <form:form action="/donations"
                               method="POST"
                               modelAttribute="user">
                        <form:hidden path="id"/>
                        <form:hidden path="donations"/>
                        <p>Potwierdź odebranie daru</p>
                    </form:form>
                </c:forEach>
            </ul>
        </div>
    </div>
</header>

<%-- HEADER --%>
<span id="contact"></span>
<jsp:include page="../footer.jsp"/>
<%-- END OF HEADER --%>

