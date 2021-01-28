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
                                                <c:choose>
                                                    <c:when test="${community_account.community != community}">
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
                                                                                <p>このコミュニティに参加しますか？</p>
                                                                                <a href="<c:url value='/member/add' />" id="yes">はい</a>
                                                                                <label for="popup01" class="popup_Close"><a id="no">いいえ</a></label>
                                                                        </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                     </c:when>
                                                     <c:otherwise>
                                                          <div class="button small">
                                                            <label for="popup01">参加済み</label>
                                                        </div>
                                                     </c:otherwise>
                                                 </c:choose>
                                             </div>
                                        </c:if>
                                    </div>
                                    <div id="communame">
                                        <c:out value="${community.name}" />
                                        <a id="commumember" href="<c:url value='/member/show?id=${community.id}' />"><br />&nbsp;<i class="fas fa-male"></i>&nbsp;${members_count}人が参加しています</a>
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
                        <table>
                                    <tbody>
                                        <tr>
                                            <th class="reload"><a class="reload" href="<c:url value='/ccreply/new?id=${contribution.id}' />"><i class="fas fa-redo-alt"></i>更新</a></th>
                                        </tr>
                                        <tr>
                                            <td class="repcon">
                                                <a class="conname" href="<c:url value='/account/show?id=${contribution.account.id}' />"><c:out value="${contribution.account.name}" />@<c:out value="${contribution.account.code}"></c:out></a>
                                                <pre><a class="con" href="<c:url value='/acreply/new?id=${contribution.id}' />"><c:out value="${contribution.content}" /><br /></a></pre>
                                                <c:if test="${contribution.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?cid=${contribution.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?cid=${contribution.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/ccreply/new?tid=${contribution.account.id}' />"><i class="fas fa-reply"></i></a>
                                                <c:choose>
                                                <c:when test="${login_account != null}">
                                                <c:set var="f" value="${0}"/>
                                                <c:forEach var="favorite" items="${fav}" varStatus="status">
                                                    <c:if test="${contribution.id == favorite.accountcontribution.id}">
                                                        <c:set var="f" value="${1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${f == 1}">
                                                    <a class="fav" href="<c:url value='/favorite/take?crid=${accountcontribution.id}' />"><i class="fas fa-star"></i></a>
                                                </c:if>
                                                <c:if test="${f == 0}">
                                                    <a class="fav" href="<c:url value='/favorite/add?crid=${accountcontribution.id}' />"><i class="far fa-star"></i></a>
                                                </c:if>
                                                </c:when>
                                                </c:choose>
                                                <a class="time"><c:out value="${contribution.created_at}"></c:out></a>
                                             </td>
                                        </tr>
                                        <c:forEach var="communityreply" items="${creplies}" varStatus="status">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${communityreply.account.id}' />"><c:out value="${communityreply.account.name}" />@<c:out value="${communityreply.account.code}" />︎</a>
                                                <a class="repname" href="<c:url value='/account/show?id=${communityreply.to_account.id}' />">To&nbsp;<c:out value="${communityreply.to_account.name}" />@<c:out value="${communityreply.to_account.code}"></c:out></a>
                                                <pre class="con"><c:out value="${communityreply.content}" /><br /></pre>
                                                <c:if test="${communityreply.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?crid=${communityreply.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?crid=${communityreply.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/ccreply/new?tid=${communityreply.account.id}' />"><i class="fas fa-reply"></i></a>
                                                <c:set var="f" value="${0}"/>
                                                <c:forEach var="favorite" items="${fav}" varStatus="status">
                                                    <c:if test="${communityreply.id == favorite.communityreply.id}">
                                                        <c:set var="f" value="${1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${f == 1}">
                                                    <a class="fav" href="<c:url value='/favorite/take?crid=${communityreply.id}' />"><i class="fas fa-star"></i></a>
                                                </c:if>
                                                <c:if test="${f == 0}">
                                                    <a class="fav" href="<c:url value='/favorite/add?crid=${communityreply.id}' />"><i class="far fa-star"></i></a>
                                                </c:if>
                                                <a class="time"><c:out value="${communityreply.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                            </table>
                    </div>
                    <div class="ctimeline">
                        <hr class="hr1" />
                        <div class="searchcommuinty">
                        <c:choose>
                            <c:when test="${login_account == null}">
                                <h1 id="conlogin">コメントするには<a class="here" href="<c:url value='/login?id=${community.id}' />">ログイン</a>してこのコミュニティに参加してください。</h1>
                            </c:when>
                            <c:otherwise>
                            <c:choose>
                                <c:when test="${community_account.community != community}">
                                    <h1 id="conlogin">コメントするにはこのコミュニティに参加してください。</h1>
                                </c:when>
                                <c:otherwise>
                                    To&nbsp;<c:out value="${sessionScope.to_account.name}" />@<c:out value="${sessionScope.to_account.code}" />
                                    <form method="POST" enctype="multipart/form-data" action="<c:url value='/ccreply/create' />">
                                        <textarea name="content" rows="2" cols="50"></textarea>
                                        <input type="hidden" name="_token" value="${_token}" />
                                        <label for="image" class="imageicon">
                                            <i class="far fa-image"></i>
                                            <input type="file" id="image" name="image" accept="image/*">
                                        </label>
                                        <input type="submit" class="con_button" value="投稿">
                                    </form>
                                    <p id="status"></p>
                                    <div>
                                      <img id="output">
                                    </div>
                                    <script>
                                      const status = document.getElementById('status');
                                      const output = document.getElementById('output');
                                      if (window.FileList && window.File && window.FileReader) {
                                        document.getElementById('image').addEventListener('change', event => {
                                          output.src = '';
                                          status.textContent = '';
                                          const file = event.target.files[0];
                                          if (!file.type) {
                                            status.textContent = 'Error: The File.type property does not appear to be supported on this browser.';
                                            return;
                                          }
                                          if (!file.type.match('image.*')) {
                                            status.textContent = 'Error: The selected file does not appear to be an image.'
                                            return;
                                          }
                                          const reader = new FileReader();
                                          reader.addEventListener('load', event => {
                                            output.src = event.target.result;
                                          });
                                          reader.readAsDataURL(file);
                                        });
                                      }
                                    </script>
                                </c:otherwise>
                            </c:choose>
                            </c:otherwise>
                        </c:choose>
                        </div>
                    </div>
            </c:when>
        </c:choose>
    </c:param>
</c:import>