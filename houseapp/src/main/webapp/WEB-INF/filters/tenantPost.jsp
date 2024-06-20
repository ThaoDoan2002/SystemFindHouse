<%-- 
    Document   : postTenant
    Created on : Jun 13, 2024, 1:32:18 AM
    Author     : doant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/tenant-posts" var="action" />
<div class="mb-4 row">
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="kw" type="search" placeholder="Nhập địa chỉ..." style="width: 300px" value="${param.kw}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="longitude" type="number" placeholder="Nhập kinh độ..." style="width: 300px" value="${param.longitude}">
        <input  class="form-control me-2" name="latitude" type="number" placeholder="Nhập vĩ độ..." style="width: 300px" value="${param.latitude}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>

</div>
