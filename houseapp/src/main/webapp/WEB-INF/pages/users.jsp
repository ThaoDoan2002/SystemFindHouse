<%-- 
    Document   : index
    Created on : Jun 5, 2024, 3:51:28 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h1 class="text-center text-info mt-1">QUẢN TRỊ NGƯỜI DÙNG</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/user" />">Thêm người dùng</a>
</div>

<div class="table-responsive">
    <table class="table table-sm table-bordered mt-2">
        <tr class="text-center">
            <th></th>
            <th>Id</th>
            <th>Tên đăng nhập</th>
            <th>Mật khẩu</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th>Quyền truy cập</th>
            <th>Trạng thái</th>
            <th></th>
        </tr>
        <c:forEach items="${users}" var="u">
            <tr>
                <td> <img class="card-img-top" src="${u.avatar}" alt="${u.username}" style="width:200px;"></td>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.password}</td>
                <td>${u.fullName}</td>
                <td>${u.email}</td>
                <td>${u.role}</td>
                <td>${u.isActive}</td>
                <td>
                    <c:url value="/delete/users/${u.id}" var="url" />
                    <a class="btn btn-info " href="<c:url value="/update/users/${u.id}" />"><i class="bi bi-pencil-square"></i></a>
                    <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<script src="<c:url value="/js/script.js"/>"></script>




