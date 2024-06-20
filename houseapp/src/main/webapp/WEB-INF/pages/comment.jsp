<%-- 
    Document   : comment
    Created on : Jun 14, 2024, 5:51:04 PM
    Author     : doant
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${cmt.id > 0}">
            CẬP NHẬT BÌNH LUẬN
        </c:when>
        <c:otherwise>
            THÊM BÌNH LUẬN
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/comment" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="cmt" >
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>

    <div class="form-floating mb-3 mt-3">
        <c:url value="/comments/posts/" var="url"/>
        <form:select class="form-select" id="userId"  path="userId" onchange="fetchPosts('${url}', this.value)">
            <c:choose>
                <c:when test="${cmt.id > 0}">
                    <option>Chọn người dùng</option>
                    <option value="${cmt.userId.id}" selected>${cmt.userId.username}- ${cmt.userId.role}</option>
                    <c:forEach items="${users}" var="u">
                        <c:if test="${u.role != 'ROLE_ADMIN' && cmt.userId.id != u.id}"> <option value="${u.id}">${u.username} - ${u.role}</option></c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option>Chọn người dùng</option>
                    <c:forEach items="${users}" var="u">
                        <c:if test="${u.role != 'ROLE_ADMIN'}"><option value="${u.id}">${u.username} - ${u.role}</option></c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </form:select>

        <label for="userId" class="form-label">Người dùng:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="postId"  path="postId">

            <option>Chọn bài đăng</option>
            <c:if test="${cmt.postId.id > 0}">
                <option value="${cmt.postId.id}" selected="">${cmt.postId.title}</option>
            </c:if>
        </form:select>

        <label for="postId" class="form-label">Bài đăng:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="content" id="content" placeholder="Nhập nội dung..." />
        <label for="content">Nội dung</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${img.id > 0}">
                    Cập nhật bình luận
                </c:when>
                <c:otherwise>
                    Thêm bình luận
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>

<script src="<c:url value="/js/post.js"/>"></script>
