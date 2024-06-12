<%-- 
    Document   : landlords
    Created on : Jun 8, 2024, 9:32:15 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ CHỦ NHÀ TRỌ</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/landlord" />">Thêm chủ nhà trọ</a>
</div>
<table class="table table-striped mt-2">
    <tr>
        <th>Id</th>
        <th>Full Name</th>
        <th>Phone Number</th>
        <th>Province</th>
        <th>District</th>
        <th>Ward</th>
        <th>Street</th>
        <th>User</th>
        <th></th>
    </tr>
    <c:forEach items="${landlords}" var="l">
        <tr>
            <td>${l.id}</td>
            <td>${l.fullName}</td>
            <td>${l.phoneNumber}</td>
            <td>${l.provinceId.name}</td>
            <td>${l.districtId.name}</td>
            <td>${l.wardId.name}</td>
            <td>${l.street}</td>
            <td>${l.userId.username}</td>
            <td>
                <c:url value="/api/landlords/${l.id}" var="url" />

                <a class="btn btn-info " href="<c:url value="/landlords/${l.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>


            </td>
        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js"/>"></script> 
