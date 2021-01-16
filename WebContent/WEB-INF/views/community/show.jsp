<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${community != null}">
                <div id="commushow">
                    <table>
                        <tbody>
                            <tr>
                                <th colspan="2">
                                    <div id="btn">
                                        <input type="hidden" name="_token" value="${_token}" />
                                        <a href="<c:url value='/community/edit?id=${community.id}' />" class="btn2">編集</a>
                                        <c:if test="${login_account != null}">
                                            <input type="checkbox" id="popup01" class="popupCheck">
                                            <div class="wrapper" align="center">
                                                <div class="button small">
                                                    <label for="popup01">参加</label>
                                                </div>
                                                <div id="popup01Con" class="popupWrap">
                                                    <div class="popupBg">
                                                        <label for="popup01" class="popup_Close"></label>
                                                    </div>
                                                    <div class="popupCon">
                                                        <div class="popupInner">
                                                                <div class="popupText">
                                                                    <p>
                                                                        <p>このコミュニティに参加しますか？</p>
                                                                        <a href="<c:url value='/member/add' />" id="yes">はい</a>
                                                                        <label for="popup01" class="popup_Close"><a id="no">いいえ</a></label>
                                                                    </p>
                                                                </div>
                                                        </div>
                                                    </div>
                                                </div>
                                             </div>
                                        </c:if>
                                    </div>
                                    <div id="communame">
                                        <c:out value="${community.name}" />
                                        <a id="commumember"><br /><i class="fas fa-male"></i>〜人が参加しています</a>
                                    </div>
                                </th>
                            </tr>
                            <tr>
                                <td><a class="commusub">作品名<br /></a><c:out value="${community.game}" /></td>
                                <td>
                                    <a class="commusub">概要<br /></a><pre><c:out value="${community.content}" /></pre>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                    <div class="ccontribution">
                        <c:choose>
                            <c:when test="${login_account == null}">
                                <h1 id="conlogin">投稿するには<a class="here" href="<c:url value='/login' />">ログイン</a>してこのコミュニティに参加してください。</h1>
                            </c:when>
                            <c:otherwise>
                            <c:choose>
                                <c:when test="${community_account == null}">
                                    <h1 id="conlogin">投稿するにはこのコミュニティに参加してください。</h1>
                                </c:when>
                                <c:otherwise>
                                    <form method="POST" action="<c:url value='/communitycontribution/create' />">
                                        <textarea name="content" rows="2" cols="50">${report.content}</textarea>

                                        <button type="submit" class="image"><i class="far fa-image"></i></button>
                                        <input type="hidden" name="_token" value="${_token}" />
                                        <button class="con_button">投稿</button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="ctimeline">
                        <hr class="hr1" />
                        <div class="searchcommuinty">
                            <table>
                                    <tbody>
                                        <tr>
                                            <th class="reload"><a class="reload" href="<c:url value='/community/show?id' />"><i class="fas fa-redo-alt"></i>更新</a></th>
                                        </tr>
                                        <c:forEach var="communitycontribution" items="${contributions}" varStatus="status">
                                            <tr>
                                                <td>
                                                <c:out value="${communitycontribution.account.name}" />@<c:out value="${communitycontribution.account.id}"></c:out>
                                                <a class="con" href="<c:url value='/community/show?id=${communitycontribution.id}' />"><c:out value="${communitycontribution.content}" /><br /></a>
                                                <a class="time"><c:out value="${communitycontribution.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                            </table>
                        </div>
                    </div>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <c:if test="${search_flag == 1}">
            <p><a href="<c:url value='/community/search' />">検索結果に戻る</a></p>
        </c:if>
    </c:param>
</c:import>