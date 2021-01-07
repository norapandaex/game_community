<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="commutitle">
            <i class="fas fa-comments"></i><a>コミュニティー</a><br />
        </div>
        <div class="searchform">
            <form id="search" action="" method="">
                <input id="sbox" name="s" type="text" placeholder="キーワードを入力"/>
                <button type="submit" id="sbtn"><i class="fas fa-search"></i></button>
            </form>
            <input type="hidden" name="_token" value="${_token}" />
            <a href="<c:url value='/community/new' />" class="btn btn--orange btn--circle btn--circle-a btn--shadow">作成</a>
        </div>
        <div class="mycommunity">
            <div class="commuheader">
                <a>参加しているコミュニティ</a>
            </div>
            <c:if test="${sessionScope.login_account == null}">
                コミュニティの管理をするには<a href="<c:url value='/login' />">こちら</a>ログインしてください。
            </c:if>
            <c:if test="${sessionScope.login_account != null}">
                <a>参加しているコミュニティはありません。</a>
            </c:if>
        </div>
        <div class="searchcommuinty">
            <table>
                    <tbody>
                        <tr>
                            <th>コミュニティ一覧</th>
                        </tr>
                        <tr>
                            <td><a href="">サンプル</a></td>
                        </tr>
                    </tbody>
            </table>
        </div>
    </c:param>
</c:import>