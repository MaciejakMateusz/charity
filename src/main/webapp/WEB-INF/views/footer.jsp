<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<body>
<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form class="form--contact">
            <div class="form-group form-group--50">
                <label>
                    <input type="text" name="name" placeholder="Imię"/>
                </label>
            </div>
            <div class="form-group form-group--50">
                <label>
                    <input type="text" name="surname" placeholder="Nazwisko"/>
                </label>
            </div>

            <div class="form-group">
                <label>
                    <textarea name="message" placeholder="Wiadomość" rows="1"></textarea>
                </label>
            </div>

            <button class="btn" type="submit">Wyślij</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img
                    src="<c:url value="/resources/images/icon-facebook.svg"/>"
                    alt="facebook-icon"/></a>
            <a href="#"
               class="btn btn--small"><img
                    src="<c:url value="/resources/images/icon-instagram.svg"/>"
                    alt="instagram-icon"/></a>
        </div>
    </div>
</footer>
</body>
</html>
