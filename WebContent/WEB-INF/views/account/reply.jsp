<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
                <div class="commutitle">
                    <i class="fab fa-stack-exchange"></i><a>投稿詳細</a>
                </div>
                <div class="homesearchform">
                    <form id="search" action="" method="">
                        <input id="sbox" name="s" type="text" placeholder="キーワードを入力"/>
                        <button type="submit" id="sbtn"><i class="fas fa-search"></i></button>
                    </form>
                </div>
                <div class="homeccontribution" >
                <div class="searchcommuinty">
                            <table>
                                    <tbody>
                                        <tr>
                                            <th class="reload"><a class="reload" href="<c:url value='/acreply/new?id=${contribution.id}' />"><i class="fas fa-redo-alt"></i>更新</a></th>
                                        </tr>
                                        <tr>
                                            <td class="repcon">
                                                <a class="conname" href="<c:url value='/account/show?id=${contribution.account.id}' />"><c:out value="${contribution.account.name}" />@<c:out value="${contribution.account.code}"></c:out></a>
                                                <pre><a class="con" href="<c:url value='/acreply/new?id=${contribution.id}' />"><c:out value="${contribution.content}" /><br /></a></pre>
                                                <c:if test="${contribution.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?id=${contribution.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?aid=${contribution.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/acreply/new?tid=${contribution.account.id}' />"><i class="fas fa-reply"></i></a>
                                                <c:set var="f" value="${0}"/>
                                                <c:forEach var="favorite" items="${fav}" varStatus="status">
                                                    <c:if test="${contribution.id == favorite.accountcontribution.id}">
                                                        <c:set var="f" value="${1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${f == 1}">
                                                    <a class="fav" href="<c:url value='/favorite/take?rid=${accountcontribution.id}' />"><i class="fas fa-star"></i></a>
                                                </c:if>
                                                <c:if test="${f == 0}">
                                                    <a class="fav" href="<c:url value='/favorite/add?rid=${accountcontribution.id}' />"><i class="far fa-star"></i></a>
                                                </c:if>
                                                <a class="time"><c:out value="${contribution.created_at}"></c:out></a>
                                             </td>
                                        </tr>
                                        <c:forEach var="accountreply" items="${areplies}" varStatus="status">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${accountreply.account.id}' />"><c:out value="${accountreply.account.name}" />@<c:out value="${accountreply.account.code}" />︎</a>
                                                <a class="repname" href="<c:url value='/account/show?id=${accountreply.to_account.id}' />">To&nbsp;<c:out value="${accountreply.to_account.name}" />@<c:out value="${accountreply.to_account.code}"></c:out></a>
                                                <pre class="con"><c:out value="${accountreply.content}" /><br /></pre>
                                                <c:if test="${accountreply.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?id=${accountreply.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?aid=${accountreply.id}' />" /></a>
                                                    </div>
                                                </c:if>
                                                <a class="reply" href="<c:url value='/acreply/new?tid=${accountreply.account.id}' />"><i class="fas fa-reply"></i></a>
                                                <c:set var="f" value="${0}"/>
                                                <c:forEach var="favorite" items="${fav}" varStatus="status">
                                                    <c:if test="${accountreply.id == favorite.accountreply.id}">
                                                        <c:set var="f" value="${1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${f == 1}">
                                                    <a class="fav" href="<c:url value='/favorite/take?rid=${accountreply.id}' />"><i class="fas fa-star"></i></a>
                                                </c:if>
                                                <c:if test="${f == 0}">
                                                    <a class="fav" href="<c:url value='/favorite/add?rid=${accountreply.id}' />"><i class="far fa-star"></i></a>
                                                </c:if>
                                                <a class="time"><c:out value="${accountreply.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                            </table>
                        </div>

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
                    To&nbsp;<c:out value="${sessionScope.to_account.name}" />@<c:out value="${sessionScope.to_account.code}" />
                    <form method="POST" enctype="multipart/form-data" action="<c:url value='/acreply/create' />">
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
    </c:param>
</c:import>