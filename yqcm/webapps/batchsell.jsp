<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ page import="com.jrj.yqcm.utils.OperateUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>批量处理</title>
</head>
<body>
<%
String user = request.getParameter("user");
String[] ids = request.getParameterValues("ids");
OperateUtil.batchSellPalyers(ids, user);
response.sendRedirect("/playerlist.jsp?user=" + user);
%>
</body>
</html>
    