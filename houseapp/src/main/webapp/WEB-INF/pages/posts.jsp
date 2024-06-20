<%-- 
    Document   : Posts
    Created on : Jun 12, 2024, 11:47:57 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ BÀI ĐĂNG</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/post" />">Thêm bài đăng</a>
</div>


<table class="table table-striped mt-2">
    <tr class="text-center">
        <th>Id</th>
        <th>Chủ đề</th>
        <th>Nội dung</th>
        <th>Ngày tạo</th>
        <th>Ngày sửa</th>
        <th>Người dùng</th>
        <th></th>
    </tr>
    <c:forEach items="${posts}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.title}</td>
            <td>${p.content}</td>
            <td>${p.createdAt}</td>
            <th>${p.updatedAt}</th>
            <td>${p.userId.username}</td>

            <td>
                <c:url value="/delete/posts/${p.id}" var="url" />

                <a class="btn btn-info " href="<c:url value="/update/posts/${p.id}" />"><i class="bi bi-pencil-square"></i></a>
                <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>


            </td>
        </tr>
    </c:forEach>
</table>


<script src="<c:url value="/js/script.js"/>"></script>





