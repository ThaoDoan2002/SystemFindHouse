<%-- 
    Document   : district
    Created on : Jun 11, 2024, 11:01:34 PM
    Author     : doant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/districts" var="action" />
<c:if test="${counter > 1}">
    <ul class="pagination mt-1" style=" justify-content: center;">
        <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>

        <c:forEach begin="1" end="${counter}" var="i">
            <c:url value="/districts" var="pageAction">
                <c:param name="page" value="${i}"/>
            </c:url>
            <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
        </c:forEach>

    </ul>
</c:if>