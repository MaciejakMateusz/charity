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
    <h2>Załóż konto</h2>
    <p class="success-message">Rejestracja przebiegła pomyślnie.</p>
    <a href="${pageContext.request.contextPath}/login" class="btn btn--without-border">Zaloguj się</a>
</section>

<span id="contact"></span>
<%-- FOOTER --%>
<jsp:include page="footer.jsp"/>
<%-- END OF FOOTER --%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>

