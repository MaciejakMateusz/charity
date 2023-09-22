<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<nav class="container container--70">
        <ul class="nav--actions">
            <sec:authorize access="isAnonymous()">
            <li><a href="${pageContext.request.contextPath}/login"
                   class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="${pageContext.request.contextPath}/register" class="btn btn--small btn--highlighted">Załóż
                konto</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="logged-user">
                    Zalogowany jako <sec:authentication property="principal.username"/>
                    <ul class="dropdown">
                        <li><a href="${pageContext.request.contextPath}/form">Przekaż dary</a></li>
                        <li><a href="#">Profil</a></li>
                        <li><a href="#">Moje zbiórki</a></li>
                        <li><a href="${pageContext.request.contextPath}/logout">Wyloguj</a></li>
                    </ul>
                </li>
            </sec:authorize>
        </ul>

    <ul>
        <li><a href="${pageContext.request.contextPath}/" class="btn btn--without-border active">Start</a></li>
        <li><a href="${pageContext.request.contextPath}/#steps" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="${pageContext.request.contextPath}/#about-us" class="btn btn--without-border">O nas</a></li>
        <li><a href="${pageContext.request.contextPath}/#help" class="btn btn--without-border">Fundacje i
            organizacje</a></li>
        <li><a href="${pageContext.request.contextPath}/#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>
