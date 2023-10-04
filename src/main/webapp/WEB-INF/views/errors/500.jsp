<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- HEADER --%>
<jsp:include page="../head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header>
    <%-- MENU --%>
    <jsp:include page="../menu.jsp"/>
    <%-- END OF MENU --%>
</header>

<section class="login-page">
    <h2>Wystąpił błąd</h2>
    <h2>Wewnętrzny błąd serwera</h2>
</section>

<%-- FOOTER --%>
<span id="contact"></span>
<jsp:include page="../footer.jsp"/>
<%-- END OF FOOTER --%>