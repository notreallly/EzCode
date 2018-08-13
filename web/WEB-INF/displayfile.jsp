<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${M_id == null}"><p>請先登入</p></c:if><c:if test="${M_id != null && files == []}"><p>沒有存檔</p></c:if><c:forEach var="file" items="${files}"><p><span>é</span> ${file.filename}</p></c:forEach>