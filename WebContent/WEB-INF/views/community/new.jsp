<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="form">
            <div class="subheader">
                <a>コミュニティ作成</a>
            </div>
            <form method="POST" action="<c:url value='/community/create' />">
                <c:import url="_form.jsp" />
            </form><br /><br />
        </div>
    </c:param>
</c:import>