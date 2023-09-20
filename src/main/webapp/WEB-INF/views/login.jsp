<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">

<%-- HEADER --%>
<jsp:include page="header.jsp"/>
<%-- END OF HEADER --%>

<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="${pageContext.request.contextPath}/login">Zaloguj</a></li>
            <li class="highlighted"><a href="${pageContext.request.contextPath}/register">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="${pageContext.request.contextPath}/" class="btn btn--without-border active">Start</a></li>
            <li><a href="${pageContext.request.contextPath}/#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="${pageContext.request.contextPath}/#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="${pageContext.request.contextPath}/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form:form action="/login"
               method="POST"
               modelAttribute="user">
        <div class="form-group">
            <form:input path="email" type="email" placeholder="Email"/>
        </div>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło"/>
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>
        <c:if test="${param.error != null}">
            <p class="validation">
                Niepoprawny email lub hasło, spróbuj ponownie
            </p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p class="success-message">
                Wylogowano pomyślnie
            </p>
        </c:if>
        <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}/register" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
    </form:form>
</section>

<span id="contact"></span>
<%-- FOOTER --%>
<jsp:include page="footer.jsp"/>
<%-- END OF FOOTER --%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>