<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Header -->
<%@ include file="../header.jsp" %>
<!-- End of Header -->

<div id="content-wrapper" class="d-flex flex-column">
    <!-- Main Content -->
    <div id="content">
        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            <!-- Sidebar Toggle (Topbar) -->
            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                <i class="fa fa-bars"></i>
            </button>
        </nav>
        <!-- End of Topbar -->
        <!-- Begin Page Content -->
        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <a href="${pageContext.request.contextPath}/admin/institutions">
                    <button type="submit"
                            style="outline: none; font-size: 1.1rem;"
                            class="button-list">
                        Powrót
                    </button>
                </a>
            </div>
            <!-- /.container-fluid -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Edytuj dane fundacji</h6>
                </div>
                <div class="card-body">
                    <form:form method="POST"
                               action="/admin/institutions/update"
                               modelAttribute="institution">
                        <form:hidden path="id"/>
                        <form:hidden path="created"/>
                        <div class="form-group">
                            <label for="name">Nazwa <br>
                                <form:input path="name"
                                            class="form-control"/>
                            </label>
                            <form:errors path="name" cssClass="validation"/>
                        </div>
                        <div class="form-group">
                            <label for="name">Opis <br>
                                <form:input path="description"
                                            class="form-control"/>
                            </label>
                            <form:errors path="description" cssClass="validation"/>
                        </div>
                        <div class="form-group">
                            <label for="email">Email <br>
                                <form:input  path="email"
                                             class="form-control"/>
                            <form:errors path="email"/>
                        </div>
                        <div class="form-group">
                            <label for="email">Numer telefonu <br>
                                    <form:input  path="phoneNumber"
                                                 class="form-control"/>
                                    <form:errors path="phoneNumber"/>
                        </div>
                        <div class="form-group">
                            <label for="name">Ulica <br>
                                <form:input path="street"
                                            class="form-control"/>
                            </label>
                            <form:errors path="street" cssClass="validation"/>
                        </div>
                        <div class="form-group">
                            <label for="name">Miasto <br>
                                <form:input path="city"
                                            class="form-control"/>
                            </label>
                            <form:errors path="city" cssClass="validation"/>
                        </div>
                        <div class="form-group">
                            <label for="name">Kod pocztowy <br>
                                <form:input path="zipCode"
                                            class="form-control"/>
                            </label>
                            <form:errors path="zipCode" cssClass="validation"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Edytuj</button>
                    </form:form>
                    <c:if test="${isUpdated==true}">
                        <p class="confirmation">Dane fundacji zaktualizowane pomyślnie.</p>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- End of Main Content -->
    </div>

    <!-- Footer -->
    <%@ include file="../footer.jsp" %>
    <!-- End of Footer -->

    <!-- End of Content -->
</div>
<!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->

</body>
</html>