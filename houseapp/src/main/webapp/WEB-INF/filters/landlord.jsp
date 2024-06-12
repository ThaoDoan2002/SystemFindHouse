<%-- 
    Document   : landlord
    Created on : Jun 10, 2024, 12:57:51 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/landlords" var="action" />
<div class="mb-4 row">
    <form action="${action}" class="d-flex col-md-3 col-12">
        <input  class="form-control me-2" name="kw" type="search" placeholder="Nhập họ và tên..." style="width: 300px" value="${param.kw}">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
    <form action="${action}" class="d-flex col-md-9 col-12">
        <c:url value="/api/districts/" var="url" />
        <select name="province" id="provinceId" class="form-select mx-2" style="width: 300px" onchange="fetchDistricts('${url}', this.value)">
            <option value="">Select Province</option>
            <c:forEach items="${provinces}" var="p" >
                <option value="${p.id}" <c:if test="${p.id == param.province}">selected</c:if>>${p.name}</option>
            </c:forEach>
        </select>
        <c:url value="/api/wards/" var="url" />
        <select name="district" id="districtId" class="form-select mx-2" style="width: 300px" onchange="fetchWards('${url}', this.value)">
            <c:choose>
                <c:when test="${not empty dt}">
                    <option value="">Select District</option>
                    <option value="${dt.id}" selected>${dt.name}</option>
                    <c:forEach items="${districts}" var="d" >

                        <option value="${d.id}" >${d.name}</option>

                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <option value="">Select District</option>
                </c:otherwise>
            </c:choose>
        </select>

        <select name="ward" id="wardId" class="form-select mx-2" style="width: 300px">

            <c:choose>
                <c:when test="${not empty war}">
                    <option value="">Select Ward</option>
                    <option value="${war.id}" selected>${war.name}</option>
                    <c:forEach items="${wards}" var="w" >

                        <option value="${w.id}" >${w.name}</option>

                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <option value="">Select Ward</option>
                </c:otherwise>
            </c:choose>
        </select>
        <button class="btn btn-primary" type="submit">OK</button>
    </form>

</div>

<script src="<c:url value="/js/address.js"/>"></script>