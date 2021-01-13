<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="content">

            <c:if test="${sessionScope.login_account == null}">
                <a href="<c:url value='/login' />">こちら</a>からログインしてください。
            </c:if>
            <c:if test="${sessionScope.login_account != null}">
                <h2><c:out value="${sessionScope.login_account.name}" />&nbsp;さんのホーム</h2>
                <div class="commutitle">
                    <i class="fas fa-home"></i><a>ホーム</a><br />
                </div>
                <div class="contribution">
                    <textarea name="content" rows="2" cols="50">${report.content}</textarea>

                    <button type="submit" class="image"><i class="far fa-image"></i></button>
                    <input type="hidden" name="_token" value="${_token}" />
                    <button class="con_button">投稿</button>
                </div>
                <div class="mycommunity">
                    <table>
                            <tbody>
                                <tr>
                                    <th>参加しているコミュニティ</th>
                                </tr>
                                <c:if test="${sessionScope.login_account == null}">
                                <tr>
                                    <td>コミュニティの管理をするには<a href="<c:url value='/login' />">こちら</a>からログインしてください。</td>
                                </tr>
                                </c:if>
                                <c:if test="${sessionScope.login_account != null}">
                                <tr>
                                    <td><a href="">サンプル</a></td>
                                </tr>
                                </c:if>
                            </tbody>
                    </table>
                </div>
                <div class="timeline">
                    <hr class="hr1" />
                    <a>タイムライン</a>
                </div>
            </c:if>
        </div>
    </c:param>
</c:import>