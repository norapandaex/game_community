<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <input id="cinput" type="text" name="name" placeholder="コミュニティ名" value="${community.name}"/><br /><br />
        <input id="cinput" type="text" name="game" placeholder="作品名" value="${community.game}"/><br /><br />
        <textarea class="commucontent" name="content" rows="10" cols="50" placeholder="コミュニティの概要" >${community.content}</textarea><br /><br />
    </label>
</div>

<input type="hidden" name="_token" value="${_token}" />
<button class="registration" type="submit">作成</button>