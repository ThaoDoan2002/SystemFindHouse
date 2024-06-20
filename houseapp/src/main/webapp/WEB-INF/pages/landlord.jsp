<%-- 
    Document   : landlord
    Created on : Jun 8, 2024, 1:41:18 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${landlord.id > 0}">
            CẬP NHẬT CHỦ NHÀ TRỌ
        </c:when>
        <c:otherwise>
            THÊM CHỦ NHÀ TRỌ
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/landlord" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="landlord" >
        <%--<form:errors path="*" element="div" cssClass="alert alert-danger"/>--%>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="phoneNumber" id="phoneNumber" placeholder="Nhập số điện thoại..."  />
        <label for="phoneNumber">Số điện thoại</label>
    </div>
  
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Nhập số địa chỉ..."  />
        <label for="address">Địa chỉ</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="userId"  path="userId">
            <c:choose>
                <c:when test="${landlord.id > 0}">
                    <option value="${landlord.userId.id}" selected>${landlord.userId.username}</option>
                    <c:forEach items="${users}" var="u">
                        <option value="${u.id}">${u.username}</option>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${users}" var="u">
                        <option value="${u.id}">${u.username}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="userId" class="form-label">Người dùng:</label>
    </div>

    <div class="form-floating">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${landlord.id > 0}">
                    Cập nhật chủ nhà trọ
                </c:when>
                <c:otherwise>
                    Thêm chủ nhà trọ
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
   
    </div>


</form:form>

