<%-- 
    Document   : imageProfile
    Created on : Jun 12, 2024, 8:22:18 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${img.id > 0}">
            CẬP NHẬT HÌNH CHỦ TRỌ
        </c:when>
        <c:otherwise>
            THÊM HÌNH CHỦ TRỌ
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/image-profile" var="action"/>
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
        <form:select class="form-select" id="typeId"  path="typeId">
            <c:choose>
                <c:when test="${img.id > 0}">
                    <option value="${img.typeId.id}" selected>${img.typeId.type}</option>
                    <c:forEach items="${types}" var="t">
                        <c:if test="${img.typeId.id != t.id}"> <option value="${t.id}">${t.type}</option></c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${types}" var="t">
                        <option value="${t.id}">${t.type}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="userId" class="form-label">Loại ảnh:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="userId"  path="userId">
            <c:choose>
                <c:when test="${img.id > 0}">
                    <option value="${img.userId.id}" selected>${img.userId.username}</option>
                    <c:forEach items="${users}" var="u">
                        <c:if test="${img.userId.id != u.id}"> <option value="${u.id}">${u.username}</option></c:if> 
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
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${img.id > 0}">
                    Cập nhật hình ảnh chủ trọ
                </c:when>
                <c:otherwise>
                    Thêm hình ảnh chủ trọ
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>

