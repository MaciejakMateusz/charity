<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                Zmień swoje dane
            </h1>
        </div>

        <div class="profile-form-container">
            <form:form action="/profile"
                       method="POST"
                       modelAttribute="user">
                <div class="form-section form-section--columns">

                    <div class="form-section--column">
                        <h4>Edytuj dane osobiste</h4>
                        <div class="form-group form-group--inline">
                            <label>Imię
                                <form:input path="name"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>Nazwisko
                                <form:input path="surname"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>E-mail
                                <form:input path="email"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Zmień hasło</h4>
                        <div class="form-group form-group--inline">
                            <label>Nowe hasło
                                <form:password path="password"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>Powtórz nowe hasło
                                <form:password path="repeatedPassword"/>
                            </label>
                        </div>
                    </div>


                    <div class="form-section--column address-div">
                        <h4>Zmień dane adresowe</h4>
                        <div class="form-group form-group--inline">
                            <label id="street-input">Ulica
                                <form:input path="street"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label id="city-input">Miasto
                                <form:input path="city"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label id="zipcode-input">Kod pocztowy
                                <form:input path="zipCode"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label id="phone-input">Numer telefonu
                                <form:input type="phone" path="phoneNumber"/>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons profile-form-buttons">
                    <a href="${pageContext.request.contextPath}/">
                        <button type="button" class="btn">Wstecz</button>
                    </a>
                    <c:choose>
                        <c:when test="${formSubmitted==true}">
                            <p class="success-message">Dane zmienione pomyślnie</p>
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
<jsp:include page="../footer.jsp"/>
<%-- END OF HEADER --%>

