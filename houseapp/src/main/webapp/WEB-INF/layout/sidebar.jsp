<%-- 
    Document   : sidebar
    Created on : Jun 5, 2024, 10:35:29 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside id="sidebar">
    <div class="d-flex">
        <button class="toggle-btn" type="button">
            <i class="lni lni-grid-alt"></i>
        </button>
        <div class="sidebar-logo">
            <a href="<c:url value="/" />">NiceHouse</a>
        </div>
    </div>
    <c:if test="${pageContext.request.userPrincipal.name != null && userLogin.role == 'ROLE_ADMIN'}">
        <ul class="sidebar-nav">
            <li class="sidebar-item">
                <c:url value="/users" var="actionUs" />
                <a href="${actionUs}" class="sidebar-link">

                    <span>Người dùng</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/landlords" var="actionLa" />
                <a href="${actionLa}" class="sidebar-link">

                    <span>Chủ nhà trọ</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/type-images" var="actionTyI" />
                <a href="${actionTyI}" class="sidebar-link">

                    <span>Loại hình ảnh</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/image-profiles" var="actionImgP" />
                <a href="${actionImgP}" class="sidebar-link">

                    <span>Hình ảnh chủ trọ</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/posts" var="actionPo" />
                <a href="${actionPo}" class="sidebar-link">

                    <span>Bài đăng</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/tenant-posts" var="actionTeP" />
                <a href="${actionTeP}" class="sidebar-link">

                    <span>Bài đăng người thuê trọ</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/rooms" var="actionR" />
                <a href="${actionR}" class="sidebar-link">

                    <span>Phòng trọ</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/landlord-posts" var="actionLaP" />
                <a href="${actionLaP}" class="sidebar-link">

                    <span>Bài đăng chủ trọ</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/room-images" var="actionImg" />
                <a href="${actionImg}" class="sidebar-link">

                    <span>Hình ảnh trọ</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/comments" var="actionCmt" />
                <a href="${actionCmt}" class="sidebar-link">

                    <span>Bình luận</span>
                </a>
            </li>
            <li class="sidebar-item">
                <c:url value="/follows" var="actionFl" />
                <a href="${actionFl}" class="sidebar-link">

                    <span>Lượt theo dõi</span>
                </a>
            </li>

        </ul>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}" >
        <div class="sidebar-footer">
            <a href="<c:url value="/logout" />" class="sidebar-link ">
                <i class="lni lni-exit"></i>
                <span>Logout</span>
            </a>
        </div>
    </c:if>
</aside>