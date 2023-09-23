<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- HEADER --%>
<jsp:include page="../head.jsp"/>
<%-- END OF HEADER --%>

<body>
<header class="header--form-page">

    <%-- MENU --%>
    <jsp:include page="../menu.jsp"/>
    <%-- END OF MENU --%>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form:form action="/form"
                   method="POST"
                   modelAttribute="donation"
                   id="donations-form">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz, co chcesz oddać:</h3>

                <div class="checkbox-container">
                    <form:checkboxes path="categories"
                                     items="${categories}"
                                     itemValue="id"
                                     itemLabel="name"
                                     cssClass="custom-checkbox"/>
                </div>
                <span class="form-validation d-none" id="category-validation">Przynajmniej jedna kategoria jest wymagana</span>
                <form:errors path="categories" cssClass="step-4-validation date-time-valid"/>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60 l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label id="quantityInput">
                        Liczba 60 l worków:
                        <form:input type="number" path="quantity" step="1" min="1"/>
                    </label>
                </div>
                <span class="form-validation d-none" id="quantity-validation">Przynajmniej jeden worek jest wymagany</span>
                <form:errors path="quantity" cssClass="step-4-validation date-time-valid"/>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 4 -->
            <div data-step="3">
                <h3>Wybierz organizację, której chcesz pomóc:</h3>

                <div class="checkbox-container">
                    <c:forEach items="${institutions}" var="institution" varStatus="loop">
                        <div class="radio-wrapper" id="institution-input">
                            <form:radiobutton path="institution" value="${institution.id}" id="radio-${loop.index}"/>
                            <div class="radio-labels-container">
                                <span class="radio-name-label">Fundacja ${institution.name}</span>
                                <span class="radio-description-label">Cel i misja: ${institution.description}</span>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label id="street-input">Ulica
                                <form:input path="street"/>
                            </label>
                            <p class="step-4-validation d-none" id="street-validation">Ulica jest wymagana</p>
                            <form:errors path="street" cssClass="step-4-validation date-time-valid"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label id="city-input">Miasto
                                <form:input path="city"/>
                            </label>
                            <p class="step-4-validation d-none" id="city-validation">Miasto jest wymagane</p>
                            <form:errors path="city" cssClass="step-4-validation date-time-valid"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label id="zipcode-input">Kod pocztowy
                                <form:input path="zipCode"/>
                            </label>
                            <p class="step-4-validation d-none" id="zipcode-validation">Kod pocztowy jest wymagany</p>
                            <form:errors path="zipCode" cssClass="step-4-validation date-time-valid"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label id="phone-input">Numer telefonu
                                <form:input type="phone" path="phoneNumber"/>
                            </label>
                            <p class="step-4-validation d-none" id="phone-validation">Numer telefonu jest wymagany</p>
                            <form:errors path="phoneNumber" cssClass="step-4-validation date-time-valid"/>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label id="date-input">Data
                                <form:input type="date" path="pickUpDate"/>
                            </label>
                            <p class="step-4-validation date-time-valid d-none" id="date-validation">Data odbioru jest wymagana</p>
                            <p class="step-4-validation date-time-valid d-none" id="future-date-validation">Data odbioru musi być w przyszłości</p>
                            <form:errors path="pickUpDate" cssClass="step-4-validation date-time-valid"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label id="time-input">Godzina
                                <form:input type="time" path="pickUpTime"/>
                            </label>
                            <p class="step-4-validation date-time-valid d-none" id="time-validation">Czas odbioru jest wymagany</p>
                            <form:errors path="pickUpTime" cssClass="step-4-validation date-time-valid"/>
                        </div>

                        <div class="form-group form-group--inline">
                            <label id="comment-input">Uwagi dla kuriera
                                <form:textarea path="pickUpComment" rows="5"/>
                            </label>
                            <form:errors path="pickUpComment" cssClass="step-4-validation date-time-valid"/>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text"
                                      id="bags-summary">4 worki ubrań w dobrym stanie dla dzieci</span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id="foundation-summary">Dla fundacji "Mam marzenie" w Warszawie</span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li id="street-summary">Prosta 51</li>
                                <li id="city-summary">Warszawa</li>
                                <li id="zipcode-summary">99-098</li>
                                <li id="phone-summary">123 456 789</li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li id="date-summary">13/12/2018</li>
                                <li id="time-summary">15:40</li>
                                <li id="comment-summary">Brak uwag dla kuriera</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <c:if test="${validFormInputs==false}">
                    <div>
                        <p class="validation">Popraw wymagane pola</p>
                    </div>
                </c:if>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<%-- HEADER --%>
<span id="contact"></span>
<jsp:include page="../footer.jsp"/>
<%-- END OF HEADER --%>

