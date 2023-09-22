<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">

<%-- HEADER --%>
<jsp:include page="../head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header class="header--form-page">
    <%-- MENU --%>
    <jsp:include page="../menu.jsp"/>
    <%-- END OF MENU --%>
        <div class="slogan container container--90">
            <h2>
                Dziękujemy za przesłanie formularza.<br>
                Na maila prześlemy wszelkie informacje o odbiorze.
            </h2>
        </div>
</header>



<%-- HEADER --%>
<span id="contact"></span>
<jsp:include page="../footer.jsp"/>
<%-- END OF HEADER --%>