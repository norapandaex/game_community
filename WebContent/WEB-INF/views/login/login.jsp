<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <div class="form">
            <div class="subheader">
                <a>ログイン</a>
            </div>
            <c:if test="${hasError}">
                <div id="flush_error">
                    idかパスワードが間違っています。
                </div>
            </c:if>
            <form method="POST" action="<c:url value='/login' />">
                <div class="cp_iptxt">
                    <label class="ef">
                        <input type="text" name="id" placeholder="ID" value="${id}"/><br /><br />
                        <input type="password" name="password" placeholder="パスワード" /><br /><br />
                    </label>
                </div>
                <input type="hidden" name="_token" value="${_token}" />
                <button class="registration" type="submit">ログイン</button><br /><br />
                <a href="<c:url value='/account/new' />">アカウントを作成していない方はこちら</a>
            </form>
         </div>
    </c:param>
</c:import>