<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ page import="com.jrj.yqcm.utils.OperateUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>列表</title>
<script type="text/javascript" src="jquery-1.7.1.min.js"></script>
</head>
<body>
<%
String user = request.getParameter("user");
String content = OperateUtil.getPlayerList(user);
out.print(content);
%>
</body>
<script type="text/javascript">
$("#chk_all").click(function(){
	var flag = $("#chk_all").attr("checked");
	$("input[name='ids']").each(function () {
		if (flag) {
			$(this).attr("checked", true);
		} else {
			$(this).attr("checked", false);
		}
	}); 
	//$("input[name='ids']").attr("checked",$(this).attr("checked"));
});
</script>
</html>
    