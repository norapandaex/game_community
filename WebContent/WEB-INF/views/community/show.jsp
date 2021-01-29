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
                                                    </c:when>
                                                    <c:otherwise>
                                                          <div class="button small">
                                                            <label for="popup01">参加済み</label>
                                                        </div>
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
                                                                        <c:when test="${community_account.community != community}">
                                                                                <p>このコミュニティに参加しますか？</p>
                                                                                <a href="<c:url value='/member/add?id=${community.id}' />" id="yes">はい</a>
                                                                                <label for="popup01" class="popup_Close"><a id="no">いいえ</a></label>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                                <p>このコミュニティを抜けますか？</p>
                                                                                <a href="<c:url value='/member/take?id=${community.id}' />" id="yes">はい</a>
                                                                                <label for="popup01" class="popup_Close"><a id="no">いいえ</a></label>
                                                                        </c:otherwise>
                                                                        </c:choose>
                                                                        </div>
                                                                </div>
                                                            </div>
                                                        </div>


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
                        <c:choose>
                            <c:when test="${login_account == null}">
                                <h1 id="conlogin">投稿するには<a class="here" href="<c:url value='/login?id=${community.id}' />">ログイン</a>してこのコミュニティに参加してください。</h1>
                            </c:when>
                            <c:otherwise>
                            <c:choose>
                                <c:when test="${community_account.community != community}">
                                    <h1 id="conlogin">投稿するにはこのコミュニティに参加してください。</h1>
                                </c:when>
                                <c:otherwise>
                                    <form method="POST" enctype="multipart/form-data" action="<c:url value='/communitycontribution/create' />">
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
                    <div class="ctimeline">
                        <hr class="hr1" />
                        <div id="commuintytimeline">
                            <c:choose>
                            <c:when test="${contributions.size() != 0}">
                            <table>
                                    <tbody>
                                        <tr>
                                            <th class="reload"><a class="reload" href="<c:url value='/community/show' />"><i class="fas fa-redo-alt"></i>更新</a></th>
                                        </tr>
                                        <c:forEach var="communitycontribution" items="${contributions}" varStatus="status">
                                        <c:if test="${communitycontribution.delete_flag == 0}">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${communitycontribution.account.id}' />"><c:out value="${communitycontribution.account.name}" />@<c:out value="${communitycontribution.account.code}" /></a>
                                                <pre><a class="con" href="<c:url value='/community/show?id=${communitycontribution.id}' />"><c:out value="${communitycontribution.content}" /><br /></a></pre>
                                                <c:if test="${communitycontribution.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?cid=${communitycontribution.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?cid=${communitycontribution.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/ccreply/new?id=${communitycontribution.id}' />"><i class="fas fa-reply"></i></a>
                                                <c:choose>
                                                <c:when test="${login_account != null}">
                                                    <c:set var="f" value="${0}"/>
                                                <c:forEach var="favorite" items="${fav}" varStatus="status">
                                                    <c:if test="${communitycontribution.id == favorite.communitycontribution.id}">
                                                        <c:set var="f" value="${1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${f == 1}">
                                                    <a class="fav" href="<c:url value='/favorite/take?cid=${communitycontribution.id}' />"><i class="fas fa-star"></i></a>
                                                </c:if>
                                                <c:if test="${f == 0}">
                                                    <a class="fav" href="<c:url value='/favorite/add?cid=${communitycontribution.id}' />"><i class="far fa-star"></i></a>
                                                </c:if>
                                                <c:if test="${login_account.id == communitycontribution.account.id}">
                                                    <a class="fav" href="<c:url value='/communitycontribution/destroy?cid=${communitycontribution.id}' />"><i class="far fa-trash-alt"></i></a>
                                                </c:if>
                                                </c:when>
                                                </c:choose>
                                                <a class="time"><c:out value="${communitycontribution.created_at}"></c:out></a>
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
                                            <th class="reload"><a>投稿はまだありません。</a></th>
                                        </tr>
                                    </tbody>
                                </table>
                            </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
            </c:when>
        </c:choose>
    </c:param>
</c:import>