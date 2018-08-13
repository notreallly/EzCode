<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/list.css" rel="stylesheet" type="text/css">
        <title>AdminPublisherList</title>
    </head>
    <body>
        <div>
            <table>
                <thead>
                    <tr>
                        <th id="th1">ID</th>
                        <th>標題</th>
                        <th>暱稱</th>
                        <th>發佈時間</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pub" items="${Publisherlist}">
                    <form action="AdminModifyPublisherServlet">
                        <tr>
                            <td>${pub.id}</td>
                            <td><a href="AdminCommentListServlet?P_id=${pub.id}">${pub.title}</a></td>
                            <td>${pub.nickname}</td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
                                            value="${pub.updatetime}" /></td>
                            <td><input class="btn btn-del" type="submit" value="刪除" name="action" 
                                       style="padding: 5px 15px; background: #d42b56; border: 0 none; cursor: pointer; border-radius: 5px;" />
                            </td>
                        <input type="hidden" name="P_id" value="${pub.id}"/>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="backAdmin">
            <c:forEach var="i" begin="1" end="${totalpage}" step="1">
                <a href="AdminPublisherListServlet?index=${i-1}"
                   style="text-decoration:none;
                   font-size:18px;
                   font-weight:600;
                   color:#000000;
                   ">${i}</a>&nbsp;
            </c:forEach>
            <input type="button" value="回後台"
                   onclick="location.href = 'administrator.jsp'" />
        </div>
    </body>
</html>
