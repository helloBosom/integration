<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>EL表达式获取用户信息</h1>
userId:${user.userId}
<br> userName:${user["userName"]}
<br>
<hr>
userId:${sessionScope.user.userId}
<br> userName:${sessionScope.user.userName}
<br>
<hr>
userId:${requestScope.user.userId}
<br> userName:${requestScope.user.userName}
<br> String:${empty str}
<br> users:${empty users}
<br> list:${empty list}
<br>
<hr>
${message}
<br>
<c:out value="${message }" default="cissst"></c:out>
<table border="1">
    <tr>
        <td>userId</td>
        <td>userName</td>
        <td>operation</td>
    </tr>
    <c:choose>
        <c:when test="${empty users }">
            <tr>
                <td colspan="3">no data</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="v" items="${users }" varStatus="vs">
                <tr>
                    <td>${v.userId }</td>
                    <td><c:if test="${vs.index %2 ==0}">
                        <font color='red'>${v.userName }</font>
                    </c:if> <c:if test="${vs.index %2 !=0}">
                        <font color='blue'>${v.userName }</font>
                    </c:if></td>
                    <td>update|delete</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</table>
<br>
<c:forEach var="v" items="${maps}">
    key:${v.key }<br>
    value:${v.value.userName }<br>
</c:forEach>
<c:catch var="error"><%=9 / 0%>
</c:catch>
<c:out value="${error }"></c:out>
<br> fmt
<br>
<fmt:formatDate value="${date}" var="v" pattern="yyyy-MM-dd HH:mm:ss"
                scope="page"/>
<c:out value="${v }"></c:out>
</body>
</html>