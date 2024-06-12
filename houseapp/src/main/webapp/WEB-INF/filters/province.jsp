<%-- 
    Document   : province
    Created on : Jun 11, 2024, 4:44:13 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<c:url value="/provinces" var="action"/>
<div class="mb-4 row">
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="kw" type="search" placeholder="Nhập tên tỉnh, thành phố..." style="width: 300px" value="${param.kw}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
</div>
