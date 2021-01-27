<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${account != null}">
                <div class="form">
                    <div class="subheader">
                        <a>アカウント編集</a>
                    </div>
                    <form method="POST" action="<c:url value='/account/update' />">
                        <c:import url="_form2.jsp" />
                    </form><br /><br />
                </div>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/account/show?id=${account.id}'/>">戻る</a></p>
    </c:param>
</c:import>