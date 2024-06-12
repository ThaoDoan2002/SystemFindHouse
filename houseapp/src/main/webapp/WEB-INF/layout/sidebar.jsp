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
            <a href="#">NiceHouse</a>
        </div>
    </div>
    <ul class="sidebar-nav">
        <li class="sidebar-item">
            <c:url value="/users" var="actionUs" />
            <a href="${actionUs}" class="sidebar-link">
                <i class="lni lni-user"></i>
                <span>Người dùng</span>
            </a>
        </li>
        <li class="sidebar-item">
            <c:url value="/landlords" var="actionLa" />
            <a href="${actionLa}" class="sidebar-link">
                <i class="lni lni-agenda"></i>
                <span>Chủ nhà trọ</span>
            </a>
        </li>
        <li class="sidebar-item">
            <c:url value="/provinces" var="actionPr" />
            <a href="${actionPr}" class="sidebar-link">
                <i class="lni lni-agenda"></i>
                <span>Tỉnh, thành phố</span>
            </a>
        </li>
        <li class="sidebar-item">
            <c:url value="/districts" var="actionDi" />
            <a href="${actionDi}" class="sidebar-link">
                <i class="lni lni-agenda"></i>
                <span>Quận, huyện</span>
            </a>
        </li>

    </ul>
    <div class="sidebar-footer">
        <a href="#" class="sidebar-link">
            <i class="lni lni-exit"></i>
            <span>Logout</span>
        </a>
    </div>
</aside>