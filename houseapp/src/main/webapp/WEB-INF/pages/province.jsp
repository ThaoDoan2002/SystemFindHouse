<%-- 
    Document   : province
    Created on : Jun 11, 2024, 5:29:35 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${province.id > 0}">
            CẬP NHẬT TỈNH, THÀNH PHỐ
        </c:when>
        <c:otherwise>
            THÊM TỈNH, THÀNH PHỐ
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/province" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="province">
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Nhập tên tỉnh, thành phố..." />
        <label for="name">Tỉnh, thành phố</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${province.id > 0}">
                    Cập nhật tỉnh, thành phố
                </c:when>
                <c:otherwise>
                    Thêm tỉnh, thành phố
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>
</form:form>


