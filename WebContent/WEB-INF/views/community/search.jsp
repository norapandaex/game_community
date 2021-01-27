<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class="commutitle">
            <i class="fas fa-comments"></i><a>コミュニティー</a><br />
        </div>
        <div class="searchform">
            <form id="search" action="<c:url value='/community/search' />" method="POST">
                <input id="sbox" name="search" type="text" placeholder="キーワードを入力"/>
                <button type="submit" id="sbtn"><i class="fas fa-search"></i></button>
            </form>
            <input type="hidden" name="_token" value="${_token}" />
            <a href="<c:url value='/community/new' />" class="btn">作成</a>
        </div>
        <div class="mycommunity">
            <table>
                    <tbody>
                        <tr>
                            <th>参加しているコミュニティ</th>
                        </tr>
                        <c:if test="${sessionScope.login_account == null}">
                        <tr>
                            <td>コミュニティの管理をするには<a href="<c:url value='/login' />">こちら</a>からログインしてください。</td>
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
        <div class="searchcommuinty">
            <table>
                    <tbody>
                        <tr>
                            <th>コミュニティ一覧</th>
                        </tr>
                        <c:choose>
                            <c:when test="${searches.size() == 0}">
                            <tr>
                                    <td><a>お探しのコミュニティは見つかりませんでした。</a></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                            <c:forEach var="community" items="${searches}" varStatus="status">
                                <tr>
                                    <td><a href="<c:url value='/community/show?id=${community.id}' />"><c:out value="${community.name}" /></a></td>
                                </tr>
                            </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
            </table>

        </div>
    </c:param>
</c:import>