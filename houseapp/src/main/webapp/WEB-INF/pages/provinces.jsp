<%-- 
    Document   : provinces
    Created on : Jun 11, 2024, 4:43:30 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h1 class="text-center text-info mt-1">QUẢN TRỊ TỈNH, THÀNH PHỐ</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/province" />">Thêm tỉnh, thành phố</a>
</div>


<table class="table table-striped mt-2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th></th>
    </tr>
    <c:forEach items="${provinces}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>
        <c:url value="/api/provinces/${p.id}" var="url" />

        <a class="btn btn-info " href="<c:url value="/provinces/${p.id}" />"><i class="bi bi-pencil-square"></i></a>
        <button onclick="deleteAPI('${url}')" class="btn btn-danger mt-1"><i class="bi bi-trash3"></i></button>


        </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>

