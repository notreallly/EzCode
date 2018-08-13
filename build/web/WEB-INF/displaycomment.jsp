<%@page contentType="text/html" pageEncoding="UTF-8"%><%@taglib
    prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@taglib
        prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                <link href="css/comment.css" rel="stylesheet" type="text/css">
                <script>
                    $(document).ready(function () {
                        $('.modify_comment').on('click', function () {
                            var td = $(this).parent().parent().siblings();
                            var span = td.find("span");
                            var content = td.find("input");
                            $(span).hide();
                            $(content).show();
                            $(this).attr("type", "hidden");
                            $(this).siblings().eq(2).attr("type", "submit");
                        });
                    });
                </script>
                <style>
                    .table{
                        margin:auto;
                    }
                    .table table{
                        border-collapse: collapse;
                        border-spacing: 0;
                        width:100%;
                    }
                    .table tr:last-child td:last-child {
                        border-bottom-right-radius:9px;
                    }
                    .table table tr:first-child td:first-child {
                        border-top-left-radius:9px;
                    }
                    .table table tr:first-child td:last-child {
                        border-top-right-radius:9px;
                    }
                    .table tr:last-child td:first-child{
                        border-bottom-left-radius:9px;
                    }
                    .table tr td{
                        vertical-align:middle;
                        background-color:#e5e5e5;
                        border:1px solid #999999;
                        border-width:0px 1px 1px 0px;
                        text-align:left;
                        padding:8px 8px 8px 14px;
                        font-size:16px;
                        font-family:Arial,微軟正黑體;
                        font-weight:500;
                    }
                    .table tr:last-child td{
                        border-width:0px 1px 0px 0px;
                    }
                    .table tr td:last-child{
                        border-width:0px 0px 1px 0px;
                    }
                    .table tr:last-child td:last-child{
                        border-width:0px 0px 0px 0px;
                    }
                    .table tr:first-child td{
                        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#005fbf", endColorstr="#005fbf"); 
                        background-color:#005fbf;
                        text-align:center;
                        font-size:20px;
                        font-family:Arial, 微軟正黑體;
                        font-weight:bold;
                        color:#ffffff;
                    }
                    .container{
                        margin-bottom:10px;
                    }                     
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="table Publisher">
                        <table align="center">
                            <tr>
                                <td width="20%" >標題</td>
                                <td width="80%">${publisher.title}</td>
                            </tr>
                            <tr>
                                <td style="text-align:center; 
                                    font-weight:600;
                                    font-size:16px;">
                                    發佈者
                                </td>
                                <td>${publisher.nickname}</td>
                            </tr>
                            <tr>
                                <td style="text-align:center; 
                                    font-weight:600;
                                    font-size:16px;">
                                    更新時間
                                </td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
                                                value="${publisher.updatetime}" /></td>
                            </tr>
                            <tr>
                                <td style="text-align:center; 
                                    font-weight:600;
                                    font-size:16px;">
                                    描述
                                </td>
                                <td>
                                    ${publisher.description}
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align:center; 
                                    font-weight:600;
                                    font-size:16px;">
                                    程式碼
                                </td>
                                <td>
                                    <textarea cols="70" rows="20" name="content" disabled>${publisher.code}</textarea>
                                </td>
                            </tr>
                            <c:if test="${M_id != null}">
                                <form action="CommentServlet" method="POST">
                                    <tr>
                                        <td>         
                                            <button class="btn btnComment" type="submit">回 覆</button>
                                        </td>
                                        <td>
                                            <textarea cols="60" rows="3" name="content"></textarea>
                                            <input type="hidden" name="P_id" value="${publisher.id}" />
                                        </td>   
                                    </tr>
                                </form>
                            </c:if>
                        </table>
                    </div>
                </div>
                <div><a class="herf" href="DisplayPublisherServlet?index=0">回上一頁</a></div>
                <c:forEach var="com" items="${comments}">
                    <div class="table container">
                        <table align="center">

                            <tr>
                                <td width="20%">${com.id}</td>
                                <td width="80%"></td>
                            </tr>
                            <tr>
                                <td style="text-align:center; 
                                    font-weight:600;
                                    font-size:16px;">
                                    暱 稱
                                </td>
                                <td>
                                    ${com.nickname}
                                </td>
                            </tr>            
                            <tr>
                                <td style="text-align:center; 
                                    font-weight:600;
                                    font-size:16px;">
                                    時 間
                                </td>
                                <td>
                                    <fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${com.commenttime}" />
                                </td>
                            </tr>
                            <form action="ModifyCommentServlet" method="POST">
                                <tr>
                                    <td style="text-align:center; 
                                        font-weight:600;
                                        font-size:16px;">
                                        內 容
                                    </td>
                                    <td><input class="content" type="text" name="content"
                                               value="${com.content}" /><span>${com.content}</span>
                                    </td>
                                </tr>
                            <tr>
                                <td>
                                    <c:if test="${M_id == com.m_id}">
                                        <input type="hidden" name="COM_id" value="${com.id}" /> 
                                        <input type="hidden" name="P_id" value="${publisher.id}" />
                                        <input class="modify_comment" type="button" value="編 輯" 
                                               style="padding:6px 16px;
                                               background:rgba(0, 95, 191, 0.70);
                                               font-weight:600;
                                               font-size:14px; 
                                               color:#FFFFFF; 
                                               border: 0 none; 
                                               cursor: pointer; 
                                               border-radius: 5px;
                                               margin:0 15px 0 15px;"/>
                                        <input class="submit_comment" type="hidden" value="送 出" 
                                               style="padding:6px 16px;
                                               background:rgba(0, 95, 191, 0.70);
                                               font-weight:600;
                                               font-size:14px; 
                                               color:#FFFFFF; 
                                               border: 0 none; 
                                               cursor: pointer; 
                                               border-radius: 5px;
                                               margin:0 15px 0 15px;"/>
                                    </c:if>
                                </td>
                                </form>
                                <td calss="btn" style="text-align:right;">
                                    <c:if test="${M_id == com.m_id}">
                                        <form action="DeleteCommentServlet" method="POST">
                                            <input type="hidden" name="COM_id" value="${com.id}" /> 
                                            <input type="hidden" name="P_id" value="${publisher.id}" /> 
                                            <div style="margin-right:10px;">
                                                <input type="submit" value="刪 除" 
                                                       style="padding:6px 16px;
                                                       background:#e00842; 
                                                       font-weight:600;
                                                       font-size:14px; 
                                                       color:#FFFFFF; 
                                                       border: 0 none;
                                                       cursor: pointer; 
                                                       border-radius: 5px;"/>
                                            </div>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>   
            </body>
        </html>

