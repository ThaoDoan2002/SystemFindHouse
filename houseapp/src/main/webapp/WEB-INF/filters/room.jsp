<%-- 
    Document   : room
    Created on : Jun 13, 2024, 11:33:15 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/rooms" var="action" />
<div class="mb-4 row">
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="kw" type="search" placeholder="Nhập địa chỉ..." style="width: 300px" value="${param.kw}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
    <form action="${action}" class="d-flex col-md-3 col-12">
        <select name="user" class="form-select mx-2" style="width: 300px">
            <option value="">Select User</option>
            <c:forEach items="${users}" var="u" >
                <option value="${u.id}" <c:if test="${u.id == param.user}">selected</c:if>>${u.username}</option>
            </c:forEach>
        </select>

        <button class="btn btn-primary" type="submit">OK</button>
    </form>
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="longitude" type="number" placeholder="Nhập kinh độ..." style="width: 300px" value="${param.longitude}">
        <input  class="form-control me-2" name="latitude" type="number" placeholder="Nhập vĩ độ..." style="width: 300px" value="${param.latitude}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
</div>
<div class="mb-4 row">
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="maxOccupants" type="number" placeholder="Nhập số lượng người ở..." style="width: 300px" value="${param.maxOccupants}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="priceMin" type="number" placeholder="Giá tiền tối thiếu..." style="width: 300px" value="${param.priceMin}">
        <input  class="form-control me-2" name="priceMax" type="number" placeholder="Giá tiền tối đa..." style="width: 300px" value="${param.priceMax}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
</div>
