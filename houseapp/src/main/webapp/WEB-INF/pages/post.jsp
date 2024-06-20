<%-- 
    Document   : post
    Created on : Jun 12, 2024, 11:53:11 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${post.id > 0}">
            CẬP NHẬT BÀI ĐĂNG
        </c:when>
        <c:otherwise>
            THÊM BÀI ĐĂNG
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/post" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="post">
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="title" id="title" placeholder="Nhập chủ đề..." />
        <label for="title">Chủ đề</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="content" id="content" placeholder="Nhập nội dung..."  />
        <label for="content">Nội dung</label>
    </div>
   
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="userId"  path="userId">
            <c:choose>
                <c:when test="${post.id > 0}">
                    <option value="${post.userId.id}" selected>${post.userId.username} - ${post.userId.role}</option>
                    <c:forEach items="${users}" var="u">
                        <c:if test="${(post.userId.id != u.id) && (u.role != 'ROLE_ADMIN')}"> <option value="${u.id}">${u.username} - ${u.role}</option></c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${users}" var="u">
                        <c:if test="${u.role != 'ROLE_ADMIN'}"> <option value="${u.id}">${u.username} - ${u.role}</option></c:if>  
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="userId" class="form-label">Người dùng:</label>
    </div>
  

    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${post.id > 0}">
                    Cập nhật bài đăng
                </c:when>
                <c:otherwise>
                    Thêm bài đăng
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>

