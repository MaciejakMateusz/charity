<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">


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
    <h2>Zaloguj się</h2>
    <form:form action="/login"
               method="POST"
               modelAttribute="user">
        <div class="form-group">
            <form:input path="username" type="email" placeholder="Email"/>
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

<%-- FOOTER --%>
<span id="contact"></span>
<jsp:include page="footer.jsp"/>
<%-- END OF FOOTER --%>