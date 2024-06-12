<%-- 
    Document   : districts
    Created on : Jun 11, 2024, 11:01:00 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ QUẬN, HUYỆN</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/district" />">Thêm quận, huyện</a>
</div>


<table class="table table-striped mt-2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Province</th>
        <th></th>
    </tr>
    <c:forEach items="${districts}" var="d">
        <tr>
            <td>${d.id}</td>
            <td>${d.name}</td>
            <th>${d.provinceId}</th>
            <td>
                <c:url value="/api/districts/${d.id}" var="url" />
                <a class="btn btn-info " href="<c:url value="/districts/${d.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger mt-1"><i class="bi bi-trash3"></i></button>
            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>


