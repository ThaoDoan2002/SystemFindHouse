<%-- 
    Document   : image
    Created on : Jun 14, 2024, 8:56:21 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${img.id > 0}">
            CẬP NHẬT HÌNH ẢNH TRỌ
        </c:when>
        <c:otherwise>
            THÊM HÌNH ẢNH TRỌ
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/room-image" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="img" enctype="multipart/form-data">
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" />
        <label for="file">Ảnh</label>
        <c:if test="${img.id > 0}">
            <img src="${img.image}" width="200" class="img-fluid"/>
            <input type="hidden" name="currImg" value="${img.image}" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="roomId"  path="roomId">
            <c:choose>
                <c:when test="${img.id > 0}">
                    <option>Chọn phòng trọ</option>
                    <option value="${img.roomId.id}" selected>${img.roomId.name}</option>
                    <c:forEach items="${rooms}" var="r">
                        <c:if test="${img.roomId.id != r.id}"> <option value="${r.id}">${r.id} - ${r.name}</option></c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option>Chọn phòng trọ</option>
                    <c:forEach items="${rooms}" var="r">
                        <option value="${r.id}">${r.name}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="userId" class="form-label">Phòng trọ:</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${img.id > 0}">
                    Cập nhật hình ảnh trọ
                </c:when>
                <c:otherwise>
                    Thêm hình ảnh trọ
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>
