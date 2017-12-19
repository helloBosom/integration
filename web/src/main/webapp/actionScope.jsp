<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    pageContext.setAttribute("username1", "cissst1");
    request.setAttribute("username", "cissst2");
    session.setAttribute("username3", "cissst3");
    application.setAttribute("username4", "cissst4");
    response.sendRedirect("b.jsp");
%>
<%
    Object object = pageContext.getAttribute("username1");
    if (object != null) {
        out.println(object.toString());
    }

    object = request.getAttribute("username2");
    if (object != null) {
        out.println(object.toString());
    }

    object = session.getAttribute("username3");

    if (object != null) {
        out.println(object.toString());
    }
    object = application.getAttribute("username4");
    if (object != null) {
        out.println(object.toString());
    }
%>
<!-- getMethod()：返回客户端向服务器端传送数据的方法
getParameter(String paramName)：返回客户端向服务器端传送的参数值，该参数由paramName指定
getParameterNames()：获得客户端传送给服务器端的所有参数的名字，结果是一个枚举类型数据（Enumeration）
getParameterValues(String  name)：获得指定参数的所有值，由name指定
getRequestURI（）：获得发出请求字符串的客户端地址
getRemoteAddr()：获取客户端的IP地址
getRemoteHost()：获取客户端机器名称
getServerName()：获取服务器的名字
getServletName()：客户端所请求的脚本文件路径
getServerPort()：获取服务器端的端口
 -->
<%
    String requestURI = request.getRequestURI();
    String requestIp = request.getRemoteAddr();
    String requestHost = request.getRemoteHost();
%>
requestURI:<%=requestURI%><br> requestIp:<%=requestIp%><br>
requestHost:<%=requestHost%>
<!-- ${pageContext.request.queryString} // <%=request.getQueryString()%>
	${pageContext.request.requestURL}
 -->
<h1>获取a.jsp页面传递的参数值</h1>
queryString:<%=request.getQueryString()%>
<hr>
queryString:${pageContext.request.queryString}
url:${pageContext.request.requestURL}
<hr>
nickname:${param.nickname }<br>
email:${param.email}<br>
</body>
</html>