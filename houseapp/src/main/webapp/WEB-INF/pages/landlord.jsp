<%-- 
    Document   : landlord
    Created on : Jun 8, 2024, 1:41:18 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h1 class="text-center text-info mt-1">    <c:choose>
        <c:when test="${landlord.id > 0}">
            CẬP NHẬT CHỦ NHÀ TRỌ
        </c:when>
        <c:otherwise>
            THÊM CHỦ NHÀ TRỌ
        </c:otherwise>
    </c:choose></h1>
    <c:url value="/landlord" var="action"/>
    <form:form method="post" action="${action}" modelAttribute="landlord">
        <%--<form:errors path="*" element="div" cssClass="alert alert-danger"/>--%>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="fullName" id="fullName" placeholder="Nhập họ tên..." />
        <label for="fullName">Họ tên</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="phoneNumber" id="phoneNumber" placeholder="Nhập số điện thoại..."  />
        <label for="phoneNumber">Số điện thoại</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <c:url value="/api/districts/" var="url" />
        <form:select class="form-select" id="provinceId" path="provinceId" onchange="fetchDistricts('${url}', this.value)">
            <c:choose>
                <c:when test="${landlord.id > 0}">
                    <option value="${landlord.provinceId.id}" selected>${landlord.provinceId.name}</option>
                    <c:forEach items="${provinces}" var="p">
                        <c:if test="${p.id != landlord.provinceId.id}"> <option value="${p.id}">${p.name}</option> </c:if> 
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option value="">Select Province</option>
                    <c:forEach items="${provinces}" var="p">
                        <option value="${p.id}">${p.name}</option>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </form:select>

        <label for="provinceId" class="form-label">Province</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <c:url value="/api/wards/" var="url" />
        <form:select class="form-select" id="districtId"  path="districtId"  onchange="fetchWards('${url}',this.value)">

            <c:choose>
                <c:when test="${landlord.id > 0}">
                    <option value="${landlord.districtId.id}" selected>${landlord.districtId.name}</option>
                    <c:forEach items="${districts}" var="d">
                        <option value="${d.id}">${d.name}</option>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option value="">Select District</option>

                </c:otherwise>
            </c:choose>
        </form:select>

        <label for="district" class="form-label">District</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="wardId"  path="wardId">
            <c:choose>
                <c:when test="${landlord.id > 0}">
                    <option value="${landlord.wardId.id}" selected>${landlord.wardId.name}</option>
                    <c:forEach items="${wards}" var="w">
                        <option value="${w.id}">${w.name}</option>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <option value="">Select Ward</option>
                </c:otherwise>
            </c:choose>
        </form:select>

        <label for="wardId" class="form-label">Ward</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="street" id="street" placeholder="Nhập số tên đường..."  />
        <label for="street">Đường</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="userId"  path="userId">
            <c:choose>
                <c:when test="${landlord.id > 0}">
                    <option value="${landlord.userId.id}" selected>${landlord.userId.username}</option>
                    <c:forEach items="${users}" var="u">
                        <option value="${u.id}">${u.username}</option>
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

    <div class="form-floating">
        <button type="submit" class="btn btn-info mt-2">
            <c:choose>
                <c:when test="${landlord.id > 0}">
                    Cập nhật chủ nhà trọ
                </c:when>
                <c:otherwise>
                    Thêm chủ nhà trọ
                </c:otherwise>
            </c:choose>

        </button>
        <form:hidden path="id" />
        <!--can phai gui id luc chinh sua-->
    </div>


</form:form>


<script src="<c:url value="/js/address.js"/>"></script>
