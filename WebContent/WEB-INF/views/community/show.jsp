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
                        <textarea name="content" rows="2" cols="50">${report.content}</textarea>

                        <button type="submit" class="image"><i class="far fa-image"></i></button>
                        <input type="hidden" name="_token" value="${_token}" />
                        <button class="con_button">投稿</button>
                    </div>
                    <div class="ctimeline">
                        <hr class="hr1" />
                        <a>タイムライン</a>
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