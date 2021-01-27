<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="cmlist">
            <c:choose>
                <c:when test="${searches.size() != 0}">
                    <table>
                        <tbody>
                            <tr>
                                <th><a id=""><i class="fas fa-search"></i>&nbsp;検索結果</a></th>
                            </tr>
                            <c:forEach var="account" items="${searches}" varStatus="status">
                                <tr>
                                <tr>
                                    <td><a
                                        href="<c:url value='/account/show?id=${account.id}' />"><c:out
                                                value="${account.name}" />@<c:out value="${account.code}" /></a>
                                        <pre id="procontent">
                            <c:out value="${account.profile}" />
                        </pre></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <table>
                        <tbody>
                            <c:forEach var="accountcontribution" items="${acsearches}"
                                varStatus="status">
                                <tr>
                                    <td><a class="conname"
                                        href="<c:url value='/account/show?id=${accountcontribution.account.id}' />"><c:out
                                                value="${accountcontribution.account.name}" />@<c:out
                                                value="${accountcontribution.account.code}"></c:out></a> <a
                                        class="con"
                                        href="<c:url value='/acreply/new?id=${accountcontribution.id}' />"><c:out
                                                value="${accountcontribution.content}" /><br /></a> <c:if
                                            test="${accountcontribution.image != null}">
                                            <div class="trim">
                                                <a
                                                    href="<c:url value='/getImage?id=${accountcontribution.id}' />"
                                                    data-lightbox="group"><img
                                                    src="<c:url value='/getImage?aid=${accountcontribution.id}' />" /></a>
                                            </div>
                                        </c:if> <a class="reply"
                                        href="<c:url value='/acreply/new?id=${accountcontribution.id}' />"><i
                                            class="fas fa-reply"></i></a> <%--<c:forEach var="favorite" items="${fav}" varStatus="status">
                                                <c:choose>
                                                    <c:when test="${accountcontribution.id != favorite.accountcontribution.id}">
                                                       <a class="fav" href="<c:url value='/favorite/add?id=${accountcontribution.id}' />"><i class="far fa-star"></i></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                       <a class="fav" href="<c:url value='/favorite/take?id=${accountcontribution.id}' />"><i class="fas fa-star"></i></a>
                                                    </c:otherwise>
                                                </c:choose>
                                                </c:forEach>--%> <a
                                        class="time"><c:out
                                                value="${accountcontribution.created_at}"></c:out></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </c:param>
</c:import>