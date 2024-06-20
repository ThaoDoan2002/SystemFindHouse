<%-- 
    Document   : imageProfiles
    Created on : Jun 12, 2024, 8:14:34 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ HÌNH ẢNH CHỦ TRỌ</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/image-profile" />">Thêm hình ảnh chủ trọ</a>
</div>


<table class="table table-striped mt-2">
    <tr>
        <th></th>
        <th>Id</th>
        <th>Loại hình</th>
        <th>Người dùng</th>
        <th></th>
    </tr>
    <c:forEach items="${imgs}" var="i">
        <tr>
            <td><img class="card-img-top" src="${i.image}" alt="${i.userId.username}" style="width:200px;"></td>
            <td>${i.id}</td>
            <td>${i.typeId.type}</td>
            <th>${i.userId.username}</th>
            <td>
                <c:url value="/delete/image-profiles/${i.id}" var="url" />
                <a class="btn btn-info " href="<c:url value="/update/image-profiles/${i.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>
            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>
