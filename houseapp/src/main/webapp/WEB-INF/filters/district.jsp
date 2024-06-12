<%-- 
    Document   : district
    Created on : Jun 11, 2024, 11:00:29 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/districts" var="action"/>
<div class="mb-4 row">
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="kw" type="search" placeholder="Nhập tên quận, huyện..." style="width: 300px" value="${param.kw}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
    <form action="${action}" class="d-flex col-md-9 col-12">
        <select name="province" id="provinceId" class="form-select mx-2" style="width: 300px" onchange="fetchDistricts('${url}', this.value)">
            <option value="">Select Province</option>
            <c:forEach items="${provinces}" var="p" >
                <option value="${p.id}" <c:if test="${p.id == param.province}">selected</c:if>>${p.name}</option>
            </c:forEach>
        </select>
        <button class="btn btn-primary" type="submit">OK</button>
    </form>

</div>