<%-- 
    Document   : tenantPost
    Created on : Jun 13, 2024, 1:44:01 AM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${tpost.id > 0}">
            CẬP NHẬT BÀI ĐĂNG NGƯỜI THUÊ TRỌ
        </c:when>
        <c:otherwise>
            THÊM BÀI ĐĂNG NGƯỜI THUÊ TRỌ
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/add/tenant-post" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="tpost" >
        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
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
        <form:input type="text" class="form-control" path="scope" id="scope" placeholder="Nhập phạm vi..."  />
        <label for="scope">Phạm vi (km&sup2;)</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="area" id="area" placeholder="Nhập diện tích..."  />
        <label for="area">Diện tích (m&sup2;)</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="minPrice" id="minPrice" placeholder="Nhập giá tiền tối thiểu..."  />
        <label for="minPrice">Giá tối thiểu</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="maxPrice" id="maxPrice" placeholder="Nhập giá tiền tối đa..."  />
        <label for="maxPrice">Giá tối đa</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="maxOccupants" id="maxOccupants" placeholder="Nhập số lượng người ở..."  />
        <label for="maxOccupants">Số lượng người ở</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="postId"  path="postId">
            <c:choose>
                <c:when test="${tpost.id > 0}">
                    <option value="${tpost.postId.id}" selected>${tpost.postId.title}</option>
                    <c:forEach items="${posts}" var="p">
                        <c:if test="${tpost.postId.id != p.id}"> <option value="${p.id}">${p.title}</option></c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${posts}" var="p">
                        <option value="${p.id}">${p.title}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="postId" class="form-label">Bài đăng:</label>
    </div>


    <div class="form-floating mb-3 mt-3">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${tpost.id > 0}">
                    Cập nhật bài đăng người thuê trọ
                </c:when>
                <c:otherwise>
                    Thêm bài đăng người thuê trọ
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>
