<%-- 
    Document   : header
    Created on : Jun 5, 2024, 10:30:33 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand px-4 py-3">

    <div class="navbar-collapse collapse">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item dropdown">

                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                          <a class="btn btn-primary" href="<c:url value="/login"/>" class="dropdown-item" >Đăng nhập</a>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <span class="m-2">
                            Xin chào <span  style="color:red">${pageContext.request.userPrincipal.name}</span>    </span>
                        <a href="<c:url value="/profile"/>" data-bs-toggle="dropdown" class="nav-icon pe-md-0">
                            <img src="<c:url value="/images/account.png"/> " class="avatar img-fluid" alt="">
                        </a>
                        <div class="dropdown-menu dropdown-menu-end rounded">
                            <a href="<c:url value="/logout"/>" class="dropdown-item">Đăng xuất</a>
                        </div>
                    </c:when>
                </c:choose>


            </li>
        </ul>
    </div>
</nav>
