<%-- 
    Document   : comments
    Created on : Jun 14, 2024, 5:35:37 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ BÌNH LUẬN</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/comment" />">Thêm bình luận</a>
</div>


<table class="table table-striped mt-2">
    <tr class="text-center">
        <th>Id</th>
        <th>User</th>
        <th>Post</th>
        <th>Content</th>
        <th></th>
    </tr>
    <c:forEach items="${cmts}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.userId.username} - ${c.userId.role}</td>
            <td>${c.postId.title} - ${c.postId.userId.role}</td>
            <td>${c.content}</td>
            <td>
                <c:url value="/delete/comments/${c.id}" var="url" />
                <a class="btn btn-info " href="<c:url value="/update/comments/${c.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>
            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>

