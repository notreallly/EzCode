<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma","no-cache");
	response.setDateHeader("Expires",0);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
<link href="css/administrator.css" rel="stylesheet" type="text/css">
<script src="js/administrator.js" type="text/javascript"></script>
<title>Administrator</title>

<c:set var="i" value="1"/>
<div class="container">
    <div class="box member">
        <a href="AdminMemberListServlet?index=${i-1}">會員列表</a>
    </div>                
    <div class="box publisher">
        <a href="AdminPublisherListServlet?index=${i-1}">留言板管理</a>
    </div>
    <input id="button" type="button" value="回首頁" onclick="location.href = 'index.html'"/>
 </div>
<div id="ALittleSVGTest"><embed src="images/svgTest.html" width="180" height="180" type="image/svg+xml" /></div>