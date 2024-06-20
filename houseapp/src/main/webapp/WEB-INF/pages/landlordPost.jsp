<%-- 
    Document   : landlordPost
    Created on : Jun 13, 2024, 5:10:38 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${lpost.id > 0}">
            CẬP NHẬT BÀI ĐĂNG CHỦ TRỌ
        </c:when>
        <c:otherwise>
            THÊM BÀI ĐĂNG CHỦ TRỌ
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/landlord-post" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="lpost" >
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>

    <div class="form-floating mb-3 mt-3">
        <c:url value="/landlord-posts/rooms/" var="url"/>
        <form:select class="form-select" id="postId"  path="postId" onchange="fetchRooms('${url}', this.value)">
            <c:choose>
                <c:when test="${lpost.id > 0}">
                      <option value="">Chọn bài đăng</option>
                    <option value="${lpost.postId.id}" selected>${lpost.postId.title} ${lpost.postId.title} - ${lpost.postId.userId.username} - ${lpost.postId.userId.role}</option>
                    <c:forEach items="${posts}" var="p">
                        <c:if test="${lpost.postId.id != p.id}"> <option value="${p.id}">${p.title} - ${p.userId.username} - ${p.userId.role}</option></c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option value="">Chọn bài đăng</option>
                    <c:forEach items="${posts}" var="p">
                        <option value="${p.id}">${p.title} - ${p.userId.username} - ${p.userId.role}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="postId" class="form-label">Bài đăng:</label>
    </div>

      <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="roomId" path="roomId">
            <option value="">Chọn phòng</option>
            <c:if test="${lpost.roomId.id > 0}">
                <option value="${lpost.roomId.id}" selected="">${lpost.roomId.name}</option>
            </c:if>
        </form:select>
        <label for="roomId" class="form-label">Phòng:</label>
    </div>


    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${lpost.id > 0}">
                    Cập nhật bài đăng chủ trọ
                </c:when>
                <c:otherwise>
                    Thêm bài đăng chủ trọ
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>
<script src="<c:url value="/js/room.js"/>"></script>

