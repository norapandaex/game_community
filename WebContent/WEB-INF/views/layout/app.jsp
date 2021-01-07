<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>ゲームコミュ</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <a id="title" href="<c:url value='/community' />">ゲームコミュ</a>
            </div>
                <div class="cp_navi">
                    <ul>
                    <li><a href="<c:url value='/home' />">ホーム</a></li>
                    <li><a href="<c:url value='/community' />">コミュニティ</a></li>
                    <c:if test="${sessionScope.login_account != null}">
                    <li class="right"><a href="">アカウント設定<span class="account"></span></a>
                        <div>
                            <ul>
                            <li><a href="/account/show">管理</a></li>
                            <li><a href="<c:url value='/logout' />">ログアウト</a></li>
                            </ul>
                        </div>
                    </li>
                    </c:if>
                    <c:if test="${sessionScope.login_account == null}">
                        <li class="right"><a href="<c:url value='/login' />">ログイン</a></li>
                        <li class="right"><a href="<c:url value='/account/new' />">アカウント作成</a></li>
                    </c:if>
                    </ul>
                </div>
            </div>
            <div id="content">
                ${param.content}
                <div class="clear"></div>
            </div>
            <div id="footer">
            <br />
                by K.H.
            </div>
    </body>
</html>