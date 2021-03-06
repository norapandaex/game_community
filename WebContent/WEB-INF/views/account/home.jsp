<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
                <div class="hometitle">
                    <i class="fas fa-home"></i><a>ホーム</a>
                </div>
                <div class="homesearchform">
                    <form id="search" action="<c:url value='/account/search' />" method="POST">
                <input id="sbox" name="search" type="text" placeholder="キーワードを入力"/>
                <button type="submit" id="sbtn"><i class="fas fa-search"></i></button>
            </form>
                </div>
                <div class="homeccontribution" >
                                    <form method="POST" enctype="multipart/form-data" action="<c:url value='/accountcontribution/create' />">
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
                    </div>
                <div class="mycommunity">
                    <table>
                            <tbody>
                                <tr>
                                    <th>参加しているコミュニティ</th>
                                </tr>
                                <c:if test="${sessionScope.login_account == null}">
                                <tr>
                                    <td>コミュニティの管理をするには<a class="here" href="<c:url value='/login' />">こちら</a>からログインしてください。</td>
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
                                <c:forEach var="communitymember" items="${mycommu}" varStatus="status">
                                    <tr>
                                        <td><a href="<c:url value='/community/show?id=${communitymember.community.id}' />"><c:out value="${communitymember.community.name}" /></a></td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                                </c:if>
                            </tbody>
                    </table>
                </div>
                <div class="timeline">
                    <hr class="hr1" />
                    <div id="hometimeline">
                            <c:choose>
                            <c:when test="${acontributions.size() != 0}">
                            <table>
                                    <tbody>
                                        <tr>
                                            <th class="reload"><a class="reload" href="<c:url value='/home' />"><i class="fas fa-redo-alt"></i>更新</a></th>
                                        </tr>
                                        <c:forEach var="accountcontribution" items="${acontributions}" varStatus="status">
                                        <c:forEach var="follow" items="${myfollows}" varStatus="status">
                                        <c:if test="${accountcontribution.account == follow.follower}">
                                        <c:if test="${accountcontribution.delete_flag == 0}">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${accountcontribution.account.id}' />"><c:out value="${accountcontribution.account.name}" />@<c:out value="${accountcontribution.account.code}"></c:out></a>
                                                <pre><a class="con" href="<c:url value='/acreply/new?id=${accountcontribution.id}' />"><c:out value="${accountcontribution.content}" /><br /></a></pre>
                                                <c:if test="${accountcontribution.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?aid=${accountcontribution.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?aid=${accountcontribution.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/acreply/new?id=${accountcontribution.id}' />"><i class="fas fa-reply"></i></a>
                                                <c:set var="f" value="${0}"/>
                                                <c:forEach var="favorite" items="${fav}" varStatus="status">
                                                    <c:if test="${accountcontribution.id == favorite.accountcontribution.id}">
                                                        <c:set var="f" value="${1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${f == 1}">
                                                    <a class="fav" href="<c:url value='/favorite/take?aid=${accountcontribution.id}' />"><i class="fas fa-star"></i></a>
                                                </c:if>
                                                <c:if test="${f == 0}">
                                                    <a class="fav" href="<c:url value='/favorite/add?aid=${accountcontribution.id}' />"><i class="far fa-star"></i></a>
                                                </c:if>
                                                <c:if test="${login_account.id == accountcontribution.account.id}">
                                                <input type="checkbox" id="popup01" class="popupCheck">
                                            <div class="wrapper" align="center">
                                                            <label  class="fav" for="popup01"><i class="far fa-trash-alt"></i></label>
                                                        <div id="popup01Con" class="popupWrap">
                                                            <div class="popupBg">
                                                                <label for="popup01" class="popup_Close"></label>
                                                            </div>
                                                            <div class="popupCon">
                                                                <div class="popupInner">
                                                                        <div class="popupText">
                                                                                <p>投稿を削除しますか？</p>
                                                                                <a href="<c:url value='/accountcontribution/destroy?aid=${accountcontribution.id}' />" id="yes">はい</a>
                                                                                <label for="popup01" class="popup_Close"><a id="no">いいえ</a></label>
                                                                        </div>
                                                                </div>
                                                            </div>
                                                        </div>


                                             </div>
                                                </c:if>
                                                <a class="time"><c:out value="${accountcontribution.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                            </c:if>
                                        </c:if>
                                        </c:forEach>
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
    </c:param>
</c:import>