<%-- 
    Document   : index
    Created on : Jun 5, 2024, 3:51:28 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h1 class="text-center text-info mt-1">QUẢN TRỊ NGƯỜI DÙNG</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/user" />">Thêm người dùng</a>
</div>


<table class="table table-striped mt-2">
    <tr>
        <th></th>
        <th>Id</th>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
        <th>Role</th>
        <th>Active</th>

        <th></th>
    </tr>
    <c:forEach items="${users}" var="u">
        <tr>
            <td> <img class="card-img-top" src="${u.avatar}" alt="${u.username}" style="width:200px;"></td>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.password}</td>
            <td>${u.email}</td>
            <td>${u.role}</td>
            <td>${u.isActive}</td>

            <td>
                <c:url value="/api/users/${u.id}" var="url" />

                <a class="btn btn-info " href="<c:url value="/users/${u.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger mt-1"><i class="bi bi-trash3"></i></button>


            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>




