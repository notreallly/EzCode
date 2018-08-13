<%@page contentType="text/html" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragrma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
    <head>
        <title>Board</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery-3.3.1.min.js"></script>
        <link href="css/board.css" rel="stylesheet" type="text/css">
    </head>
    <script>
        $(document).ready(function () {
            $('.modify_publish').on('click', function () {
                var td = $(this).parent().siblings().eq(2);
                var a = td.find("a");
                var title = td.find("input");
                $(a).hide();
                $(title).show();
                $(this).attr("type", "hidden");
                $(this).siblings().eq(1).attr("type", "submit");
            });
        });
    </script>
    <body>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th width="5%">ID</th>
                        <th width="50%">標題</th>
                        <th width="13%">發佈者</th>
                        <th width="14%">更新時間</th>
                        <th width="9%"></th>
                        <th width="9%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pub" items="${publisher}">
                        <tr>
                    <form action="ModifyPublisherServlet" method="POST">
                        <td>${pub.id}</td>
                        <td>
                            <input class="title" type="text" name="title" value="${pub.title}"/>
                            <a class="publisherHref" href="DisplayCommentServlet?P_id=${pub.id}">
                                ${pub.title}
                            </a>
                        </td>
                        <td>${pub.nickname}</td>
                        <td>
                            <fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${pub.updatetime}"/>
                        </td>
                        <c:if test="${M_id != pub.m_id}">
                            <td></td>  
                            <td></td> 
                        </form>
                    </c:if>
                    <c:if test="${M_id == pub.m_id}">
                        <td>
                            <input type="hidden" name="P_id" value="${pub.id}" />
                            <input class="modify_publish" type="button" value="編輯"
                                   style="padding:3px 8px;
                                   background:rgba(0, 95, 191, 0.70);
                                   font-weight:600;
                                   font-size:14px; 
                                   color:#FFFFFF; 
                                   border: 0 none; 
                                   cursor: pointer; 
                                   border-radius: 4px;
                                   margin:0 0px 0 5px;" />
                            <input class="submit_publish" type="hidden" value="送出"  
                                   style="padding:3px 8px;
                                   background:rgba(0, 95, 191, 0.70);
                                   font-weight:600;
                                   font-size:14px; 
                                   color:#FFFFFF; 
                                   border: 0 none; 
                                   cursor: pointer; 
                                   border-radius: 4px;
                                   margin:0 5px 0 5px;"/>
                        </td>
                        </form>
                        <td>
                            <form action="DeletePublisherServlet" method="POST">
                                <input type="hidden" name="P_id" value="${pub.id}" />
                                <input class="delete_publish" type="submit" value="刪除" 
                                       style="padding:3px 8px;
                                       background:#e00842; 
                                       font-weight:600;
                                       font-size:14px; 
                                       color:#FFFFFF; 
                                       border: 0 none;
                                       cursor: pointer; 
                                       border-radius: 4px;"/>
                            </form>
                        </td>
                    </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="page" >Page:
            <c:forEach var="i" begin="1" end="${total}" step="1">
                <a href="DisplayPublisherServlet?index=${i-1}"
                   style="text-decoration:none;
                   font-size:18px;
                   font-weight:600;
                   color:#000000;
                   ">
                    ${i}</a>&nbsp;
                </c:forEach>
        </div>
    </body>
</html>
