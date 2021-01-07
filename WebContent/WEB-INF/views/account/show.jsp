<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="name">
            <div class="subheader">
                <h2>アカウント名<br /><c:out value="${sessionScope.login_account.name}" /></h2>
            </div>
        </div>
        <div class="community">
            <div class="subheader">
                <a>参加しているコミュニティ</a>
            </div>
         </div>
    </c:param>
</c:import>