<%-- 
    Document   : user
    Created on : Jun 6, 2024, 3:58:42 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${user.id > 0}">
            CẬP NHẬT NGƯỜI DÙNG
        </c:when>
        <c:otherwise>
            THÊM NGƯỜI DÙNG
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/user" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="username" id="username" placeholder="Nhập tên đăng nhập..." />
        <label for="username">Tên đăng nhập</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="password" id="password" placeholder="Nhập mật khẩu..."  />
        <label for="password">Mật khẩu</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="fullName" id="fullName" placeholder="Nhập họ tên..." />
        <label for="fullName">Họ tên</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" />
        <label for="file">Ảnh đại diện </label>
        <c:if test="${user.id > 0}">
            <img src="${user.avatar}" width="200" class="img-fluid"/>
            <input type="hidden" name="currentAvatar" value="${user.avatar}" />

        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="email" id="email" placeholder="Nhập tên email..." />
        <label for="email">Email</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="role" name="role" path="role">
            <option value="ROLE_ADMIN" <c:if test="${'ROLE_ADMIN'.equals(user.role)}">selected</c:if>>Quản trị viên</option>
            <option value="ROLE_LANDLORD" <c:if test="${'ROLE_LANDLORD'.equals(user.role)}">selected</c:if>>Chủ nhà trọ</option>
            <option value="ROLE_TENANT" <c:if test="${'ROLE_TENANT'.equals(user.role)}">selected</c:if>>Người thuê trọ</option>
        </form:select>
        <label for="role" class="form-label">Quyền truy cập:</label>
    </div>

    <c:if test="${user.id > 0}">
        <div class="form-floating mb-3 mt-3">
            <form:checkbox class="form-check-input" path="isActive" id="isActive"/> Active

        </div>
    </c:if>



    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${user.id > 0}">
                    Cập nhật người dùng
                </c:when>
                <c:otherwise>
                    Thêm người dùng
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>
