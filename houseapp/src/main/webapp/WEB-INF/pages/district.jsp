<%-- 
    Document   : district
    Created on : Jun 12, 2024, 12:12:16 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${district.id > 0}">
            CẬP NHẬT QUẬN, HUYỆN
        </c:when>
        <c:otherwise>
            THÊM QUẬN, HUYỆN
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/district" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="district">
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Nhập tên quận, huyện..." />
        <label for="name">Quận, huyện</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="provinceId"  path="provinceId">
            <c:choose>
                <c:when test="${district.id > 0}">
                    <option value="${district.provinceId}" selected>${district.provinceId}</option>
                    <c:forEach items="${provinces}" var="p">
                        <c:if test="${p.id != district.provinceId}"><option value="${p.id}">${p.id} - ${p.name}</option></c:if>  
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${provinces}" var="p">
                       <option value="${p.id}">${p.id} - ${p.name}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="provinceId" class="form-label">Mã Thành phố, tỉnh</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${district.id > 0}">
                    Cập nhật quận, huyện
                </c:when>
                <c:otherwise>
                    Thêm quận, huyện
                </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>
</form:form>


