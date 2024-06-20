<%-- 
    Document   : landlordPosts
    Created on : Jun 13, 2024, 4:49:33 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ BÀI ĐĂNG CHỦ TRỌ</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/landlord-post" />">Thêm bài đăng chủ trọ</a>
</div>


<table class="table table-striped mt-2">
    <tr class="text-center">
        <th>Id</th>
        <th>Bài đăng</th>
        <th>Phòng trọ</th>
        <th></th>
    </tr>
    <c:forEach items="${lposts}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.postId.id} - ${p.postId.title} - ${p.postId.userId.username}</td>
            <td>${p.roomId.id} - ${p.roomId.name} - ${p.roomId.userId.username}</td>

            <td>
                <c:url value="/landlord-posts/${p.id}" var="url" />

                <a class="btn btn-info " href="<c:url value="/landlord-posts/${p.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>


            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>
