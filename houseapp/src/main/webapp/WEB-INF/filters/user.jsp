<%-- 
    Document   : FilterUser
    Created on : Jun 10, 2024, 11:26:15 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/users" var="action"/>
<div class="mb-4 row">

    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="kw" type="search" placeholder="Nhập tên đăng nhập..." style="width: 300px" value="${param.kw}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
    <form action="${action}" class="d-flex col-md-9 col-12">
        <select name="role" class="form-select mx-2" style="width: 300px">
            <option value="">Chọn quyền truy cập</option>
            <option value="ADMIN" <c:if test="${'ADMIN'.equals(param.role)}">selected</c:if>>Quản trị viên</option>
            <option value="LANDLORD" <c:if test="${'LANDLORD'.equals(param.role)}">selected</c:if>>Chủ nhà trọ</option>
            <option value="TENANT" <c:if test="${'TENANT'.equals(param.role)}">selected</c:if>>Người thuê trọ</option>
        </select>
        <button class="btn btn-primary" type="submit">OK</button>
    </form>

</div>
