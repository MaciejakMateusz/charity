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
            <button id="sidebarToggleTop"
                    class="btn btn-link d-md-none rounded-circle mr-3">
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
                    <h6 class="m-0 font-weight-bold text-primary">Potwierdź usunięcie tej fundacji</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                            <div class="row">
                                <div class="col-sm-12">
                                    <table class="table table-bordered dataTable" id="dataTable" width="100%"
                                           cellspacing="0" role="grid" aria-describedby="dataTable_info"
                                           style="width: 100%; border-collapse: collapse; border-left: none;">
                                        <c:if test="${isRemoved==true}">
                                            <p style="color: green">Fundacja została usunięta.</p>
                                        </c:if>
                                        <c:if test="${isRemoved==null}">
                                        <tbody>
                                        <tr>
                                            <td><strong>Id</strong></td>
                                            <td><c:out value='${empty institution.id  ? "-" : institution.id}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Nazwa</strong></td>
                                            <td><c:out value='${empty institution.name ? "-" : institution.name}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Opis</strong></td>
                                            <td><c:out value='${empty institution.description ? "-" : institution.description}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Ulica</strong></td>
                                            <td><c:out value='${empty institution.street ? "-" : institution.street}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Miasto</strong></td>
                                            <td><c:out value='${empty institution.city ? "-" : institution.city}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Kod pocztowy</strong></td>
                                            <td><c:out value='${empty institution.zipCode ? "-" : institution.zipCode}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Email</strong></td>
                                            <td><c:out value='${empty institution.email ? "-" : institution.email}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Numer telefonu</strong></td>
                                            <td><c:out value='${empty institution.phoneNumber ? "-" : institution.phoneNumber}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Data utworzenia</strong></td>
                                            <td><c:out value='${empty institution.created ? "-" : institution.created}'/></td>
                                        </tr>
                                        <tr>
                                            <td><strong>Ostatnio edytowano</strong></td>
                                            <td><c:out value='${empty institution.updated ? "Nigdy" : institution.updated}'/></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <p style="color: tomato;">Czy potwierdzasz usunięcie tej
                                        fundacji?<br></p>
                                    <a href="${pageContext.request.contextPath}/admin/institutions">
                                        <button
                                                style="outline: none; font-size: 1.1rem;"
                                                class="button-list">
                                            Nie
                                        </button>
                                    </a>
                                    <form:form method="POST"
                                               action="/admin/institutions/remove"
                                               modelAttribute="institution"
                                               cssStyle="display: inline-block">
                                        <form:hidden path="id"/>
                                        <button type="submit"
                                                style="outline: none; font-size: 1.1rem; background: tomato; border-color: tomato;"
                                                class="button-list">
                                            Tak
                                        </button>
                                    </form:form>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End of Main Content -->
    </div>

    <!-- Footer -->
    <c:if test="${isRemoved==false}">
        <%@ include file="../footer.jsp" %>
    </c:if>
    <!-- End of Footer -->

    <!-- End of Content -->
</div>
<!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->

</body>

</html>