<%-- 
    Document   : imageProfile
    Created on : Jun 12, 2024, 8:10:02 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/image-profiles" var="action" />
<div class="mb-4 row">
    <form action="${action}" class="d-flex col-md-4 col-12">
        <select name="type" class="form-select mx-2" style="width: 300px">
            <option value="">Select Type</option>
            <c:forEach items="${types}" var="t" >
                <option value="${t.id}" <c:if test="${t.id == param.type}">selected</c:if>>${t.type}</option>
            </c:forEach>
        </select>

        <button class="btn btn-primary" type="submit">OK</button>
    </form>
    <form action="${action}" class="d-flex col-md-8 col-12">
        <select name="user" class="form-select mx-2" style="width: 300px">
            <option value="">Select User</option>
            <c:forEach items="${users}" var="u" >
                <option value="${u.id}" <c:if test="${u.id == param.user}">selected</c:if>>${u.username}</option>
            </c:forEach>
        </select>

        <button class="btn btn-primary" type="submit">OK</button>
    </form>

</div>


