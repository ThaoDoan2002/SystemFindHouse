<%-- 
    Document   : rooms
    Created on : Jun 13, 2024, 11:36:29 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ PHÒNG TRỌ</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/room" />">Thêm phòng trọ</a>
</div>


<table class="table table-striped mt-2">
    <tr class="text-center">
        <th>Id</th>
        <th>Tên</th>
        <th>Kinh độ</th>
        <th>Vĩ độ</th>
        <th>Địa chỉ</th>
        <th>Số lượng người ở</th>
        <th>Giá tiền</th>
        <th>Diện tích (m&sup2;)</th>
        <th>Người dùng</th>
        <th></th>
    </tr>
    <c:forEach items="${rooms}" var="r">
        <tr>
            <td>${r.id}</td>
            <td>${r.name}</td>
            <td>${r.longitude}</td>
            <td>${r.latitude}</td>
            <td>${r.address}</td>
            <th>${r.maxOccupants}</th>
            <td>${r.price}</td>
            <td>${r.area}</td>
            <th>${r.userId.username}</th>
            <td>
                <c:url value="/delete/rooms/${r.id}" var="url" />

                <a class="btn btn-info " href="<c:url value="/update/rooms/${r.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>


            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>


