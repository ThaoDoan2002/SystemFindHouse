<%-- 
    Document   : typeImages
    Created on : Jun 12, 2024, 6:34:19 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ LOẠI HÌNH ẢNH</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/type-image" />">Thêm loại hình ảnh</a>
</div>


<table class="table table-striped mt-2">
    <tr class="text-center">
        <th>Id</th>
        <th>Loại</th>
        <th></th>
    </tr>
    <c:forEach items="${typeImages}" var="t">
        <tr>
            <td>${t.id}</td>
            <td>${t.type}</td>
            <td>
                <c:url value="/delete/type-images/${t.id}" var="url" />
                <a class="btn btn-info " href="<c:url value="/update/type-images/${t.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>
            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>



