<%-- 
    Document   : follows
    Created on : Jun 15, 2024, 12:06:17 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ THEO DÕI</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/follow" />">Thêm lượt theo dõi</a>
</div>


<table class="table table-striped mt-2">
    <tr class="text-center">
        <th>Id</th>
        <th>Follower</th>
        <th>Landlord</th>
        <th>Followed</th>
        <th></th>
    </tr>
    <c:forEach items="${follows}" var="f">
        <tr>
            <td>${f.id}</td>
            <td>${f.followerId.username}</td>
            <td>${f.landlordId.username}</td>
            <td>${f.followed}</td>
            <td>
                <c:url value="/delete/follows/${f.id}" var="url" />
                <a class="btn btn-info " href="<c:url value="/update/follows/${f.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>
            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>
