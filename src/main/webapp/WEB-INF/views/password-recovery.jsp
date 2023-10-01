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
    <h2>Podaj adres email do swojego konta</h2>
    <h3 style="font-size: 2rem">Link z możliwością zmiany hasła zostanie na niego dostarczony</h3>
    <c:choose>
        <c:when test="${emailSent!=true}">
            <form action="${pageContext.request.contextPath}/login/password-recovery"
                  method="POST">
                <div class="form-group">
                    <label>
                        <input type="email"
                               name="email"
                               placeholder="Podaj swój adres e-mail"
                               class="custom-form-input"/>
                    </label>
                </div>
                <div class="form-group form-group--buttons">
                    <button class="btn" type="submit">Wyślij link</button>
                </div>
            </form>
            <c:choose>
                <c:when test="${userExists==false}">
                    <p class="validation">
                        Sprawdź, czy podany email jest prawidłowy
                    </p>
                </c:when>
                <c:when test="${error==true}">
                    <p class="validation">
                        Coś poszło nie tak, spróbuj ponownie
                    </p>
                </c:when>
            </c:choose>
        </c:when>
        <c:when test="${emailSent==true}">
            <p class="success-message">
                Email z linkiem do zmiany hasła został wysłany na adres ${email}
            </p>
        </c:when>
    </c:choose>
</section>

<%-- FOOTER --%>
<span id="contact"></span>
<jsp:include page="footer.jsp"/>
<%-- END OF FOOTER --%>