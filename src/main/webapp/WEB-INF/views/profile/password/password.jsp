<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- HEADER --%>
<jsp:include page="/WEB-INF/views/head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header class="header--form-page profile-form-page">

    <%-- MENU --%>
    <jsp:include page="/WEB-INF/views/menu.jsp"/>
    <%-- END OF MENU --%>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zmień swoje hasło
            </h1>
        </div>

        <div class="profile-form-container">
            <form:form action="/profile/password"
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
                <form:hidden path="enabled"/>
                <form:hidden path="roles"/>
                <div class="form-section form-section--columns">

                    <div class="form-section--column">
                        <h4>Zmień hasło</h4>
                        <div class="form-group form-group--inline">
                            <label>Stare hasło
                                <form:password path="oldPassword"/>
                            </label>
                            <c:if test="${invalidPassword==true}">
                                <p class="profile-validation">Niepoprawne hasło, spróbuj ponownie</p>
                            </c:if>
                        </div>
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
                    <a href="${pageContext.request.contextPath}/profile">
                        <button type="button" class="btn">Wstecz</button>
                    </a>
                    <c:choose>
                        <c:when test="${formSubmitted==true}">
                            <p class="success-message">Hasło zmienione pomyślnie</p>
                        </c:when>
                        <c:when test="${formSubmitted!=true}">
                            <button type="submit" class="btn">Zapisz</button>
                        </c:when>
                    </c:choose>
                </div>
            </form:form>
        </div>
    </div>
</header>

<%-- HEADER --%>
<span id="contact"></span>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
<%-- END OF HEADER --%>

