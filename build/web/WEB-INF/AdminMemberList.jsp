<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/list.css" rel="stylesheet" type="text/css">
        <title>AdminMemberList</title>
    </head>
    <body>
        <div>
            <table>
                <thead>
                    <tr>
                        <th id="th1">ID</th>
                        <th>Mail</th>
                        <th>Nickname</th>
                        <th>Password</th>
                        <th>Actived</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ml" items="${memberlist}">
                    <form action="AdminModifyMemberServlet">
                        <tr>
                            <td>${ml.id}</td>
                            <td>${ml.mail}</td>
                            <td><input type="text" name="nickname"
                                       value="${ml.nickname}" /></td>
                            <td><input type="text" name="password"
                                       value="${ml.password}" /></td>
                            <td><input type="number" name="actived" value="${ml.actived}" min="0" max="1" /></td>
                            <td><input class="btn btn-edit" type="submit" style="padding:5px 15px; background:#4fb2ee; border:0 none; cursor:pointer; border-radius:5px; font:微軟正黑體;" value="修改"
                                       name="action" /></td>
                            <td><input class="btn btn-del" type="submit" value="刪除"
                                       name="action"
                                       style="padding: 5px 15px; background:#d42b56; border: 0 none; cursor: pointer; border-radius: 5px;" /></td>
                        <input type="hidden" name="M_id" value="${ml.id}" />
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="backAdmin">
            <c:forEach var="i" begin="1" end="${totalpage}" step="1">
                <a
                    href="AdminMemberListServlet?index=${i-1}"
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
