<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="cmlist">
                            <c:choose>
                            <c:when test="${members.size() != 0}">
                            <table>
                                    <tbody>
                                        <tr>
                                            <th><a id=""><i class="fas fa-male"></i>&nbsp;メンバー一覧&nbsp;(${members_count}人が参加しています)</a></th>
                                        </tr>
                                        <c:forEach var="communitymember" items="${members}" varStatus="status">
                                            <tr>
                                                <td>
                                                <a class="conname" href="<c:url value='/account/show?id=${communitymember.account.id}' />"><c:out value="${communitymember.account.name}" />@<c:out value="${communitymember.account.code}" /></a>
                                                <pre id="procontent">
                            <a><c:out value="${communitymember.account.profile}" /></a>
                        </pre>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                            </table>
                            </c:when>
                            <c:otherwise>
                                <table>
                                    <tbody>
                                        <tr>
                                            <th class="reload"><a>このコミュニティに参加している人はいません。</a></th>
                                        </tr>
                                    </tbody>
                                </table>
                            </c:otherwise>
                            </c:choose>
                        </div>
    </c:param>
</c:import>