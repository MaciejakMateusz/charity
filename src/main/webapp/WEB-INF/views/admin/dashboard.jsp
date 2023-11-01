<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Header -->
<%@ include file="header.jsp" %>
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
                <a href="${pageContext.request.contextPath}/admin/user/add"
                   class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
                   style="font-size: 1.1rem;"><i
                        class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika
                </a>
            </div>
            <!-- /.container-fluid -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Lista użytkowników</h6>
                </div>
                <div class="card-body">
                    <div>
                        <p>Wyszukaj użytkownika po:</p>
                        <form style="display: inline-block;"
                              action="${pageContext.request.contextPath}/admin/dashboard/idFilter"
                              id="findById">
                            <label>
                                <input type="number"
                                       style="width: 80px;"
                                       min="1"
                                       name="id"
                                       placeholder="ID"
                                       form="findById">
                            </label>
                        </form>
                        <p style="display: inline-block; white-space: pre;"> lub </p>
                        <form action="${pageContext.request.contextPath}/admin/dashboard/emailFilter"
                              id="findByEmail"
                              style="display: inline-block;">
                            <label>
                                <input type="text"
                                       name="email"
                                       placeholder="Email"
                                       form="findByEmail">
                            </label>
                        </form>
                    </div>
                    <div class="table-responsive">
                        <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
                            <div class="row">
                                <div class="col-sm-12">
                                    <c:if test="${foundByEmail==true}">
                                        <a href="${pageContext.request.contextPath}/admin/dashboard">
                                            <button type="submit"
                                                    style="outline: none; font-size: 1.1rem;"
                                                    class="button-list">
                                                Powrót
                                            </button>
                                        </a>
                                        <p style="font-weight: bold; display: inline-block; margin-left: 2rem;">Wyniki
                                            dla wyszukiwania "${partialEmail}":</p>
                                    </c:if>
                                    <table class="table table-bordered dataTable" id="dataTable" width="100%"
                                           cellspacing="0" role="grid" aria-describedby="dataTable_info"
                                           style="width: 100%; border-collapse: collapse; border-left: none;">
                                        <thead>
                                        <tr role="row">
                                            <th tabindex="0" aria-controls="dataTable"
                                                rowspan="1" colspan="1"
                                                aria-label="Name: activate to sort column descending"
                                                style="width: auto; text-align: center" aria-sort="ascending">Id
                                            </th>
                                            <th tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                                aria-label="Position: activate to sort column ascending"
                                                style="width: auto">Nazwa użytkownika
                                            </th>
                                            <th tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1"
                                                aria-label="Office: activate to sort column ascending"
                                                style="width: auto">Email
                                            </th>
                                            <th tabindex="0" aria-controls="dataTable" rowspan="1"
                                                colspan="1" aria-label="Age: activate to sort column ascending"
                                                style="width: auto;">Akcja
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:choose>
                                            <c:when test="${idFilterOn==true && userNotFound!=true}">
                                                <tr role="row">
                                                    <td style="text-align: center"><c:out
                                                            value='${foundUser.id}'/></td>
                                                    <td><c:out value='${foundUser.username}'/></td>
                                                    <td><c:out value='${foundUser.email}'/></td>
                                                    <td>
                                                        <form style="all: unset;"
                                                              method="POST"
                                                              action="${pageContext.request.contextPath}/admin/user">
                                                            <input type="hidden" value="${foundUser.id}" name="id">
                                                            <button type="submit"
                                                                    style="outline: none;"
                                                                    class="button-list">
                                                                Pokaż
                                                            </button>
                                                        </form>
                                                        <form style="all: unset;"
                                                              method="POST"
                                                              action="${pageContext.request.contextPath}/admin/user/edit">
                                                            <button
                                                                    type="submit"
                                                                    style="outline: none;"
                                                                    class="button-list"
                                                                    name="id"
                                                                    value="${foundUser.id}">
                                                                Edytuj
                                                            </button>
                                                        </form>
                                                        <form style="all: unset;"
                                                              method="POST"
                                                              action="${pageContext.request.contextPath}/admin/user/delete">
                                                            <button
                                                                    type="submit"
                                                                    style="outline: none; background: tomato;"
                                                                    class="button-list"
                                                                    name="id"
                                                                    value="${foundUser.id}">
                                                                Usuń
                                                            </button>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:when>
                                            <c:when test="${idFilterOn!=true}">
                                                <c:forEach items="${users}" var="user">
                                                    <tr role="row">
                                                        <td style="text-align: center"><c:out
                                                                value='${user.id}'/></td>
                                                        <td><c:out value='${user.username}'/></td>
                                                        <td><c:out value='${user.email}'/></td>
                                                        <td>
                                                            <form style="all: unset;"
                                                                  method="POST"
                                                                  action="${pageContext.request.contextPath}/admin/user">
                                                                <input type="hidden" value="${user.id}" name="id">
                                                                <button type="submit"
                                                                        style="outline: none;"
                                                                        class="button-list">
                                                                    Pokaż
                                                                </button>
                                                            </form>
                                                            <form style="all: unset;"
                                                                  method="POST"
                                                                  action="${pageContext.request.contextPath}/admin/user/edit">
                                                                <button
                                                                        type="submit"
                                                                        style="outline: none;"
                                                                        class="button-list"
                                                                        name="id"
                                                                        value="${user.id}">
                                                                    Edytuj
                                                                </button>
                                                            </form>
                                                            <form style="all: unset;"
                                                                  method="POST"
                                                                  action="${pageContext.request.contextPath}/admin/user/delete">
                                                                <button
                                                                        type="submit"
                                                                        style="outline: none; background: tomato;"
                                                                        class="button-list"
                                                                        name="id"
                                                                        value="${user.id}">
                                                                    Usuń
                                                                </button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                        </tbody>
                                    </table>
                                    <c:if test="${userNotFound==true}">
                                        <p class="validation">Nie znaleziono wyników</p>
                                    </c:if>
                                    <c:if test="${idFilterOn==true || userNotFound==true}">
                                        <a href="${pageContext.request.contextPath}/admin/dashboard">
                                            <button type="submit"
                                                    style="outline: none; font-size: 1.1rem;"
                                                    class="button-list">
                                                Powrót
                                            </button>
                                        </a>
                                    </c:if>
                                    <c:if test="${pageable.hasPrevious()}">
                                        <form action="${pageContext.request.contextPath}/admin/dashboard/decrementPage"
                                              method="POST" style="display: inline-block;">
                                            <button type="submit"
                                                    class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                                <
                                            </button>
                                        </form>
                                    </c:if>
                                    <c:if test="${totalPages!=0}">
                                        <%-- SMALL RECORDS AMOUNT SCENARIO --%>
                                        <c:if test="${totalPages != 1 && totalPages < 20}">
                                            <c:forEach var="iteration" begin="0" end="${totalPages - 1}">
                                                <form action="${pageContext.request.contextPath}/admin/dashboard/page"
                                                      method="POST"
                                                      style="display: inline-block;">
                                                    <input type="hidden" name="pageNumber" value="${iteration}">
                                                    <button type="submit"
                                                            class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                                            ${iteration}
                                                    </button>
                                                </form>
                                            </c:forEach>
                                        </c:if>

                                        <%-- LARGE RECORDS AMOUNT SCENARIO --%>
                                        <c:if test="${totalPages != 0 && totalPages > 20}">
                                            <c:forEach var="iteration" begin="0" end="10">
                                                <form action="${pageContext.request.contextPath}/admin/dashboard"
                                                      style="display: inline-block;">
                                                    <input type="hidden" name="page" value="${iteration}">
                                                    <button type="submit"
                                                            class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                                            ${iteration}
                                                    </button>
                                                </form>
                                            </c:forEach>
                                            <button
                                                    class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                                ...
                                            </button>
                                            <c:forEach var="iteration" begin="${totalPages - 8}"
                                                       end="${totalPages - 1}">
                                                <form action="${pageContext.request.contextPath}/admin/dashboard"
                                                      style="display: inline-block;">
                                                    <input type="hidden" name="page" value="${iteration}">
                                                    <button type="submit"
                                                            class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                                            ${iteration}
                                                    </button>
                                                </form>
                                            </c:forEach>
                                            <form action="${pageContext.request.contextPath}/admin/dashboard/incrementPage"
                                                  method="POST"
                                                  style="display: inline-block;">
                                                <button type="submit"
                                                        class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                                                    >
                                                </button>
                                            </form>
                                            <p style="display:inline-block; white-space: pre;"> Strona </p>
                                            <form action="${pageContext.request.contextPath}/admin/dashboard"
                                                  style="display: inline-block;">
                                                <label>
                                                    <input
                                                            type="number"
                                                            style="width: 4rem;"
                                                            name="page"
                                                            min="1"
                                                            max="${totalPages - 1}"
                                                            value="${pageable.pageNumber}">
                                                </label>
                                            </form>
                                        </c:if>
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
    <%@ include file="footer.jsp" %>
    <!-- End of Footer -->

    <!-- End of Content -->
</div>
<!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->

</body>

</html>