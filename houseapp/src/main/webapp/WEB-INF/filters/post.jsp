<%-- 
    Document   : post
    Created on : Jun 12, 2024, 11:40:59 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/posts" var="action" />
<div class="mb-4 row">
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="kw" type="search" placeholder="Nhập nội dung, chủ đề..." style="width: 300px" value="${param.kw}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
    <form action="${action}" class="d-flex col-md-8 col-12">
        <select name="user" class="form-select mx-2" style="width: 300px">
            <option value="">Select User</option>
            <c:forEach items="${users}" var="u" >
                <c:if test="${u.role != 'ADMIN'}"></c:if>   <option value="${u.id}" <c:if test="${u.id == param.user}">selected</c:if>>${u.username}</option>
            </c:forEach>
        </select>

        <button class="btn btn-primary" type="submit">OK</button>
    </form>

</div>


