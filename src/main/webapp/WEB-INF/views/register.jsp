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
    <h2>Załóż konto</h2>
    <form:form action="/register"
               method="POST"
               modelAttribute="user">
        <div class="form-group">
            <label>
                <form:input type="email"
                            placeholder="Email"
                            path="email"
                            cssClass="custom-form-input"/>
                <form:errors path="email" cssClass="validation"/>
                <c:if test="${emailExists==true}">
                    <p class="validation">Użytkownik o podanym adresie e-mail już istnieje</p>
                </c:if>
            </label>
        </div>
        <div class="form-group">
            <label>
                <form:password path="password"
                               placeholder="Hasło"
                               cssClass="custom-form-input"/>
                <form:errors path="password" cssClass="validation"/>
            </label>
        </div>
        <div class="form-group">
            <label>
                <form:password path="repeatedPassword"
                               placeholder="Powtórz hasło"
                               cssClass="custom-form-input"/>
                <c:if test="${passwordsNotMatch==true}">
                    <p class="validation">Hasła nie są identyczne</p>
                </c:if>
            </label>
        </div>
        <c:if test="${error==true}">
            <p class="validation">Coś poszło nie tak, spróbuj ponownie</p>
        </c:if>
        <c:choose>
            <c:when test="${activationSent!=true}">
                <div class="form-group form-group--buttons">
                    <a href="${pageContext.request.contextPath}/login" class="btn btn--without-border">Zaloguj się</a>
                    <button class="btn" type="submit">Załóż konto</button>
                </div>
            </c:when>
            <c:when test="${activationSent==true}">
                <p class="success-message">Link aktywacyjny został wysłany na adres ${email}</p>
            </c:when>
        </c:choose>
    </form:form>
</section>

<%-- FOOTER --%>
<span id="contact"></span>
<jsp:include page="footer.jsp"/>
<%-- END OF FOOTER --%>

