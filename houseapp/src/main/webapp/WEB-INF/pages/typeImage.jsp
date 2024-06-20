<%-- 
    Document   : typeImage
    Created on : Jun 12, 2024, 6:41:08 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${typeImage.id > 0}">
            CẬP NHẬT LOẠI HÌNH ẢNH
        </c:when>
        <c:otherwise>
            THÊM LOẠI HÌNH ẢNH
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/type-image" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="typeImage">
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <div class="form-floating">
        <form:select class="form-select" id="type" name="type" path="type">
            <option value="ROW" <c:if test="${'ROW'.equals(user.role)}">selected</c:if>>Dãy trọ</option>
            <option value="ROOM" <c:if test="${'ROOM'.equals(user.role)}">selected</c:if>>Phòng trọ</option>

        </form:select>
        <label for="role" class="form-label">Loại hình ảnh:</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${typeImage.id > 0}">
                    Cập nhật loại hình ảnh
                </c:when>
                <c:otherwise>
                    Thêm loại hình ảnh
                </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>
</form:form>


