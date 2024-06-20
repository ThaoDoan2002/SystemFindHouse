<%-- 
    Document   : follow
    Created on : Jun 15, 2024, 12:09:36 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${follow.id > 0}">
            CẬP NHẬT LƯỢT THEO DÕI
        </c:when>
        <c:otherwise>
            THÊM BÌNH LƯỢT THEO DÕI
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/follow" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="follow" >
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>

    <div class="form-floating mb-3 mt-3">
        <c:url value="/follows/users/" var="url"/>
        <form:select class="form-select" id="followerId"  path="followerId"  onchange="fetchLandlords('${url}', this.value)">
            <c:choose>
                <c:when test="${follow.id > 0}">
                    <option>Chọn người theo dõi</option>
                    <option value="${follow.followerId.id}" selected>${follow.followerId.username} - ${follow.followerId.role}</option>
                    <c:forEach items="${users}" var="u">
                        <c:if test="${(follow.followerId.id != u.id) && (u.role != 'ADMIN')}"> <option value="${u.id}">${u.username} - ${u.role}</option></c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option>Chọn người theo dõi</option>
                    <c:forEach items="${users}" var="u">
                        <option value="${u.id}">${u.username} - ${u.role}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </form:select>

        <label for="followerId" class="form-label">Người theo dõi:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <%--<c:url value="/api/follows/posts/" var="url"/>--%>
        <form:select class="form-select" id="landlordId"  path="landlordId">
            <option>Chọn chủ trọ</option>
            <c:if test="${follow.landlordId.id > 0}">
                <option value="${follow.landlordId.id}" selected="">${follow.landlordId.username} - ${follow.landlordId.role}</option>
            </c:if>
        </form:select>

        <label for="landlordId" class="form-label">Chủ trọ:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${follow.id > 0}">
                    Cập nhật lượt theo dõi
                </c:when>
                <c:otherwise>
                    Thêm lượt theo dõi
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>
<script src="<c:url value="/js/follow.js"/>"></script>


