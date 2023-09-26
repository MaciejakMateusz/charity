<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- HEADER --%>
<jsp:include page="../../head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header class="header--form-page profile-form-page">

    <%-- MENU --%>
    <jsp:include page="../../menu.jsp"/>
    <%-- END OF MENU --%>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Zarządzaj swoimi darami
            </h1>
        </div>

        <div class="donation-details-grid">

            <div class="institution-area">
                <p class="success-message">Potwierdziłeś odebranie daru</p>
            </div>

            <div class="buttons-area">
                <a href="${pageContext.request.contextPath}/donations">
                    <button type="submit" class="btn">Wróć</button>
                </a>
            </div>
        </div>
    </div>
</header>

<%-- HEADER --%>
<span id="contact"></span>
<jsp:include page="../../footer.jsp"/>
<%-- END OF HEADER --%>

