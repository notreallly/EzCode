<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma","no-cache");
	response.setDateHeader("Expires",0);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/list.css" rel="stylesheet" type="text/css">
        <title>AdminCommetList</title>
    </head>
    <body>
        <div>
            <table>
                <thead>
                    <tr>
                        <th id="th1">ID</th>
                        <th>留言內容</th>
                        <th>暱稱</th>
                        <th>發佈時間</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="com" items="${comments}">
                        <tr>
                            <td>${com.id}</td>
                            <td>${com.content}</td>
                            <td>${com.nickname}</td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${com.commenttime}"/></td>
                    <form action="AdminModifyCommentServlet" method="POST">
                        <td><input type="hidden" name="COM_id" value="${com.id}" />
                            <input type="hidden" name="P_id" value="${publisher.id}" />
                            <input class="btn btn-del" type="submit" value="刪除" name="action" style="padding: 5px 15px; background: #d42b56; border: 0 none; cursor: pointer; border-radius: 5px;" /></td>
                    </form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="backAdmin">
            <input type="button" value="回前頁"
                   onclick="location.href = 'AdminPublisherListServlet?index=0'" />
        </div>
    </body>
</html>
