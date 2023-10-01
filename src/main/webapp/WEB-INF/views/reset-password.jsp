<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- HEADER --%>
<jsp:include page="head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header>
    <%-- MENU --%>
    <jsp:include page="menu.jsp"/>
    <%-- END OF MENU --%>
</header>

<section class="login-page">
    <h2>Zmień hasło do swojego konta</h2>
    <form:form action="/login/reset-password"
               method="POST"
               modelAttribute="user">
        <form:hidden path="id"/>
        <form:hidden path="name"/>
        <form:hidden path="surname"/>
        <form:hidden path="street"/>
        <form:hidden path="city"/>
        <form:hidden path="zipCode"/>
        <form:hidden path="username"/>
        <form:hidden path="email"/>
        <form:hidden path="phoneNumber"/>
        <form:hidden path="created"/>
        <div class="form-section form-section--columns">

            <div class="form-section--column">
                <h4>Zmień hasło</h4>
                <div class="form-group form-group--inline">
                    <label>Nowe hasło
                        <form:password path="password"/>
                    </label>
                    <form:errors path="password" cssClass="profile-validation"/>
                </div>

                <div class="form-group form-group--inline">
                    <label>Powtórz nowe hasło
                        <form:password path="repeatedPassword"/>
                    </label>
                    <c:if test="${passwordsNotMatch==true}">
                        <p class="profile-validation">Hasła nie są identyczne</p>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="form-group form-group--buttons profile-form-buttons">
            <c:choose>
                <c:when test="${isReset==true}">
                    <a href="${pageContext.request.contextPath}/login">
                        <button type="button" class="btn">Zaloguj się</button>
                    </a>
                    <p class="success-message">Hasło zmienione pomyślnie</p>
                </c:when>
                <c:when test="${isReset!=true}">
                    <button type="submit" class="btn">Zapisz</button>
                </c:when>
            </c:choose>
        </div>
    </form:form>
</section>

<%-- FOOTER --%>
<span id="contact"></span>
<jsp:include page="footer.jsp"/>
<%-- END OF FOOTER --%>

