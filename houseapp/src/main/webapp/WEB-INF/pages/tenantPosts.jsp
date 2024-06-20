<%-- 
    Document   : tenantPosts
    Created on : Jun 13, 2024, 1:39:35 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">QUẢN TRỊ BÀI ĐĂNG NGƯỜI THUÊ TRỌ</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/add/tenant-post" />">Thêm bài đăng người thuê trọ</a>
</div>

<div class="table-responsive">
    <table class="table table-sm table-bordered mt-2">
        <tr class="text-center">
            <th>Id</th>
            <th>Địa chỉ</th>
            <th>Kinh độ</th>
            <th>Vĩ độ</th>
            <th>Địa chỉ</th>
            <th>Giá tiền tối thiểu</th>
            <th>Giá tiền tối đa</th>
            <th>Phạm vi (km&sup2;)</th>
            <th>Diện tích (m&sup2;)</th>
            <th>Số lượng người ở</th>
            <th>Bài đăng</th>
            <th></th>
        </tr>
        <c:forEach items="${tposts}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.address}</td>
                <td>${p.longitude}</td>
                <td>${p.latitude}</td>
                <td>${p.address}</td>
                <td>${String.format("%,f", p.minPrice)}</td>
                <td>${String.format("%,f", p.maxPrice)}</td>
                <td>${p.scope}</td>
                <td>${p.area}</td>
                <td>${p.maxOccupants}</td>
                <td>${p.postId.title}</th>
                <td>
                    <c:url value="/delete/tenant-posts/${p.id}" var="url" />

                    <a class="btn btn-info " href="<c:url value="/update/tenant-posts/${p.id}" />"><i class="bi bi-pencil-square"></i></a>
                    <button onclick="deleteAPI('${url}')" class="btn btn-danger"><i class="bi bi-trash3"></i></button>


                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<script src="<c:url value="/js/script.js"/>"></script>



