<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>


    </div>
</c:if>
    <div class="cp_iptxt">
        <label class="ef">
            <input type="text" name="id" placeholder="ID"/><br /><br />
            <input type="text" name="name" placeholder="アカウント名"/><br /><br />
            <input type="password" name="password" placeholder="パスワード"/><br /><br />
            <input type="password" name="vpassword" placeholder="パスワード確認"/><br /><br />
        </label>
    </div>
<input type="hidden" name="_token" value="${_token}" />
<button class="registration" type="submit">登録</button>