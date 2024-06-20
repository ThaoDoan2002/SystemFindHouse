<%-- 
    Document   : room
    Created on : Jun 13, 2024, 11:45:05 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${room.id > 0}">
            CẬP NHẬT PHÒNG TRỌ
        </c:when>
        <c:otherwise>
            THÊM PHÒNG TRỌ
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/room" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="room" >
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Nhập tên phòng..."  />
        <label for="name">Tên phòng</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="longitude" id="longitude" placeholder="Nhập kinh độ..."  />
        <label for="longitude">Kinh độ</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="latitude" id="latitude" placeholder="Nhập vĩ độ..."  />
        <label for="latitude">Vĩ độ</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Nhập địa chỉ..."  />
        <label for="address">Địa chỉ</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="maxOccupants" id="maxOccupants" placeholder="Nhập số lượng người ở..."  />
        <label for="maxOccupants">Số lượng người ở</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="area" id="area" placeholder="Nhập diện tích..."  />
        <label for="area">Diện tích (m&sup2;)</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="price" id="price" placeholder="Nhập giá phòng..."  />
        <label for="price">Giá phòng</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="userId"  path="userId">
            <c:choose>
                <c:when test="${room.id > 0}">
                    <option value="${room.userId.id}" selected>${room.userId.username} - ${room.userId.role}</option>
                    <c:forEach items="${users}" var="u">
                        <c:if test="${room.userId.id != u.id}"><option value="${u.id}">${u.username} - ${u.role}</option></c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${users}" var="u">
                        <option value="${u.id}">${u.username} - ${u.role}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="userId" class="form-label">Người dùng:</label>
    </div>


    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${room.id > 0}">
                    Cập nhật phòng trọ
                </c:when>
                <c:otherwise>
                    Thêm phòng trọ
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>
