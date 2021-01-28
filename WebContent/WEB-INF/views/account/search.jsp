<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="asearchlist">
                <c:if test="${asearches.size() != 0}">
                    <table>
                        <tbody>
                            <tr>
                                <th><a id=""><i class="fas fa-search"></i>&nbsp;キーワードを含むアカウント</a></th>
                            </tr>
                            <c:forEach var="account" items="${asearches}" varStatus="status">
                                <tr>
                                <tr>
                                    <td><a
                                        href="<c:url value='/account/show?id=${account.id}' />"><c:out
                                                value="${account.name}" />@<c:out value="${account.code}" /></a>
                                        <pre id="procontent">
                            <a><c:out value="${account.profile}" /></a>
                        </pre></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                </div>
                <div id="acsearchlist">
                <c:if test="${acsearches.size() != 0}">
                    <table>
                                    <tbody>
                                    <tr>
                                <th><a id=""><i class="fas fa-search"></i>&nbsp;キーワードを含む投稿</a></th>
                            </tr>
                                        <c:forEach var="accountcontribution" items="${acsearches}" varStatus="status">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${accountcontribution.account.id}' />"><c:out value="${accountcontribution.account.name}" />@<c:out value="${accountcontribution.account.code}"></c:out></a>
                                                <pre><a class="con" href="<c:url value='/acreply/new?id=${accountcontribution.id}' />"><c:out value="${accountcontribution.content}" /><br /></a></pre>
                                                <c:if test="${accountcontribution.image != null}">
                                                    <div class="trim">
                                                        <a href="<c:url value='/getImage?id=${accountcontribution.id}' />" data-lightbox="group"><img src="<c:url value='/getImage?aid=${accountcontribution.id}' />" /></a>
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
                                                <a class="time"><c:out value="${accountcontribution.created_at}"></c:out></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                            </table>
                </c:if>
                </div>
                <div id="noresult">
                <c:if test="${acsearches.size() == 0 && asearches.size() == 0}">
                    <table>
                    <tbody>
                    <tr>
                                <th><a id="">キーワードが含まれるものはありませんでした。</a></th>
                            </tr>
                    </tbody>
                    </table>
                </c:if>
        </div>
    </c:param>
</c:import>