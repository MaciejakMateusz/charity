<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                    <form style="all: unset;"
                          method="POST"
                          action="${pageContext.request.contextPath}/app/edit">
                        <button
                                type="submit"
                                style="outline: none; font-size: 1.1rem;"
                                class="button-list"
                                name="id"
                                value="<c:out value='${admin.id}'/>">
                            <i class="fas fa-download fa-sm text-white-50"></i>
                            Edytuj użytkownika
                        </button>
                    </form>
                </div>
                <!-- /.container-fluid -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Szczegóły użytkownika</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <table class="table table-bordered dataTable" id="dataTable" width="100%"
                                               cellspacing="0" role="grid" aria-describedby="dataTable_info"
                                               style="width: 100%; border-collapse: collapse; border-left: none;">
                                            <tbody>
                                            <tr>
                                                <td><strong>Id</strong></td>
                                                <td><c:out value='${empty admin.id  ? "-" : admin.id}'/></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Imię</strong></td>
                                                <td><c:out value='${empty admin.name ? "-" : admin.name}'/></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Nazwisko</strong></td>
                                                <td><c:out value='${empty admin.surname ? "-" : admin.surname}'/></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Ulica</strong></td>
                                                <td><c:out value='${empty admin.street ? "-" : admin.street}'/></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Miasto</strong></td>
                                                <td><c:out value='${empty admin.city ? "-" : admin.city}'/></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Kod pocztowy</strong></td>
                                                <td><c:out value='${empty admin.zipCode ? "-" : admin.zipCode}'/></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Email</strong></td>
                                                <td><c:out value='${empty admin.email ? "-" : admin.email}'/></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Data utworzenia</strong></td>
                                                <td><c:out value='${empty admin.created ? "-" : admin.created}'/></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Ostatnio edytowano</strong></td>
                                                <td><c:out value='${empty admin.updated ? "Nigdy" : admin.updated}'/></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <a href="${pageContext.request.contextPath}/admin/dashboard">
                                            <button style="outline: none; font-size: 1.1rem;"
                                                    class="button-list">
                                                Powrót
                                            </button>
                                        </a>
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
        <%@ include file="../footer.jsp" %>
        <!-- End of Footer -->

        <!-- End of Content -->
    </div>
    <!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->

</body>

</html>