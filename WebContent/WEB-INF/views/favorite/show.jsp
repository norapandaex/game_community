<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="mypage">
            <c:choose>
                <c:when test="${sessionScope.login_account.id == account.id}">
                    <i class="fas fa-user-cog"></i>マイページ
                </c:when>
                <c:otherwise>
                    <i class="fas fa-user"></i>アカウント詳細
                </c:otherwise>
            </c:choose>
        </div>
        <div id="myaccount">
            <table>
                <tr>
                    <td id="name">アカウント名<br /> <a class="acname"><c:out
                                value="${account.name}" />@<c:out value="${account.code}" /></a> <c:if
                            test="${sessionScope.login_account.id != account.id}">
                            <input type="checkbox" id="popup01" class="popupCheck">
                            <div class="followcon" align="center">
                                <c:choose>
                                    <c:when test="${sessionScope.login_account.id != account.id}">
                                        <c:choose>
                                            <c:when test="${follow_check == 0  || follow_check == null}">
                                                <label for="popup01" class="followbutton">フォロー</label>
                                            </c:when>
                                            <c:otherwise>
                                                <label for="popup01" class="followbutton">フォロー中</label>
                                            </c:otherwise>
                                        </c:choose>
                                        <div id="popup01Con" class="popupWrap">
                                            <div class="popupBg">
                                                <label for="popup01" class="popup_Close"></label>
                                            </div>
                                            <div class="popupCon">
                                                <div class="popupInner">
                                                    <div class="popupText">
                                                        <c:choose>
                                                            <c:when test="${sessionScope.login_account.id != null}">
                                                                <c:choose>
                                                                    <c:when test="${follow_check == 0}">
                                                                        <p>このアカウントをフォローしますか？</p>
                                                                        <a
                                                                            href="<c:url value='/follow/add?id=${account.id}' />"
                                                                            id="yes">はい</a>
                                                                        <label for="popup01" class="popup_Close"><a
                                                                            id="no">いいえ</a></label>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <p>このアカウントのフォローを外しますか？</p>
                                                                        <a
                                                                            href="<c:url value='/follow/take?id=${account.id}' />"
                                                                            id="yes">はい</a>
                                                                        <label for="popup01" class="popup_Close"><a
                                                                            id="no">いいえ</a></label>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <p>フォローするにはログインしてください。</p>
                                                                <a href="<c:url value='/login' />" id="yes">ログイン</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="button small">
                                            <label for="popup01">フォロー中</label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:if>
                    </td>
                    <td id="follow">フォロー<br /> <input type="checkbox" id="popup"
                        class="popupCheck"> <a href="<c:url value='/follow/show?id=${account.id}' />" class="acname"><c:out
                                value="${followsC}" /></a>
                    </td>
                    <td id="follower">フォロワー<br /> <a href="<c:url value='/follower/show?id=${account.id}' />" class="acname"><c:out
                                value="${followersC}" /></a></td>
                    <td id="fav">お気に入り<br />
                    <a href="<c:url value='/favorite/show?id=${account.id}' />" class="acname"><c:out value="${favoritesC}" /></a></td>
                </tr>
                <tr>
                    <td id="profile" colspan="4">プロフィール<br /> <pre
                            id="procontent">
                            <a><c:out value="${account.profile}" /></a>
                        </pre> <input type="hidden" name="_token"
                        value="${_token}" /> <c:if
                            test="${sessionScope.login_account.id == account.id}">
                            <a href="<c:url value='/account/edit?id=${account.id}' />"
                                id="btn4">編集</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mycommunity">
            <table>
                <tbody>
                    <tr>
                        <th>参加しているコミュニティ</th>
                    </tr>
                    <c:if test="${sessionScope.login_account == null}">
                        <tr>
                            <td>コミュニティの管理をするには<a class="here"
                                href="<c:url value='/login' />">こちら</a>からログインしてください。
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${sessionScope.login_account != null}">
                        <c:choose>
                            <c:when test="${mycommu == null}">
                                <tr>
                                    <td><a>参加しているコミュニティはありません。</a></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="communitymember" items="${mycommu}"
                                    varStatus="status">
                                    <tr>
                                        <td><a
                                            href="<c:url value='/community/show?id=${communitymember.community.id}' />"><c:out
                                                    value="${communitymember.community.name}" /></a></td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </tbody>
            </table>
        </div>
        <div class="flist">
            <c:choose>
                <c:when test="${fav != null}">
                    <table>
                        <tbody>
                            <tr>
                                <th><a id=""><i class="fas fa-star"></i>&nbsp;お気に入り一覧</a></th>
                            </tr>
                            <c:forEach var="favorite" items="${fav}" varStatus="status">
                            <c:if test="${favorite.accountcontribution != null}">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${favorite.accountcontribution.account.id}' />"><c:out value="${favorite.accountcontribution.account.name}" />@<c:out value="${favorite.accountcontribution.account.code}"></c:out></a>
                                                <pre><a class="con" href="<c:url value='/acreply/new?id=${favorite.accountcontribution.id}' />"><c:out value="${favorite.accountcontribution.content}" /><br /></a></pre>
                                                <c:if test="${favorite.accountcontribution.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?aid=${favorite.accountcontribution.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?aid=${favorite.accountcontribution.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/acreply/new?id=${favorite.accountcontribution.id}' />"><i class="fas fa-reply"></i></a>
                                                    <a class="fav" href="<c:url value='/favorite/take?aid=${favorite.accountcontribution.id}' />"><i class="fas fa-star"></i></a>
                                                <a class="time"><c:out value="${favorite.accountcontribution.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                         </c:if>
                                         <c:if test="${favorite.accountreply != null}">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${favorite.accountreply.account.id}' />"><c:out value="${favorite.accountreply.account.name}" />@<c:out value="${favorite.accountreply.account.code}"></c:out></a>
                                                <pre><a class="con" href="<c:url value='/acreply/new?id=${favorite.accountreply.id}' />"><c:out value="${favorite.accountreply.content}" /><br /></a></pre>
                                                <c:if test="${favorite.accountreply.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?arid=${favorite.accountreply.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?arid=${favorite.accountreply.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/acreply/new?id=${favorite.accountreply.id}' />"><i class="fas fa-reply"></i></a>
                                                    <a class="fav" href="<c:url value='/favorite/take?aid=${favorite.accountreply.id}' />"><i class="fas fa-star"></i></a>
                                                <a class="time"><c:out value="${favorite.accountreply.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                         </c:if>
                                         <c:if test="${favorite.communitycontribution != null}">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${favorite.communitycontribution.account.id}' />"><c:out value="${favorite.communitycontribution.account.name}" />@<c:out value="${favorite.communitycontribution.account.code}"></c:out></a>
                                                <pre><a class="con" href="<c:url value='/acreply/new?id=${favorite.communitycontribution.id}' />"><c:out value="${favorite.communitycontribution.content}" /><br /></a></pre>
                                                <c:if test="${favorite.communitycontribution.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?cid=${favorite.communitycontribution.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?cid=${favorite.communitycontribution.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/acreply/new?id=${favorite.communitycontribution.id}' />"><i class="fas fa-reply"></i></a>
                                                    <a class="fav" href="<c:url value='/favorite/take?aid=${favorite.communitycontribution.id}' />"><i class="fas fa-star"></i></a>
                                                <a class="time"><c:out value="${favorite.communitycontribution.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                         </c:if>
                                         <c:if test="${favorite.communityreply != null}">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${favorite.communityreply.account.id}' />"><c:out value="${favorite.communityreply.account.name}" />@<c:out value="${favorite.communityreply.account.code}"></c:out></a>
                                                <pre><a class="con" href="<c:url value='/acreply/new?id=${favorite.communityreply.id}' />"><c:out value="${favorite.communityreply.content}" /><br /></a></pre>
                                                <c:if test="${favorite.communityreply.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?crid=${favorite.communityreply.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?crid=${favorite.communityreply.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/acreply/new?id=${favorite.communityreply.id}' />"><i class="fas fa-reply"></i></a>
                                                    <a class="fav" href="<c:url value='/favorite/take?aid=${favorite.communityreply.id}' />"><i class="fas fa-star"></i></a>
                                                <a class="time"><c:out value="${favorite.communityreply.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                         </c:if>
                                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <table>
                        <tbody>
                            <tr>
                                <th class="reload"><a>お気に入りの投稿はありません。</a></th>
                            </tr>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </c:param>
</c:import>