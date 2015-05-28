<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ page import="com.jrj.yqcm.utils.OperateUtil"%>
<%@ page import="com.jrj.yqcm.utils.SomeLock"%>
<%@ page import="com.jrj.yqcm.utils.HttpComponentUtilsWeb"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>搜索</title>
<script type="text/javascript" src="jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function startSearch(key) {
		$("#button").disabled = true;
		$("#button").val("搜索中...");
		window.location.href("search.jsp?key=" + key + "&a=s");
	}
</script>
</head>
<body>
<%
String cookie = "";
String loginUrl = "";
String football = "";
String host = "http://f9.xba.com.cn";
String key = request.getParameter("key");
String a = request.getParameter("a");
if ("thzsbg3".equals(key)) {
	cookie = "DomainFirstCost=0183F352C2C3F2DB; DomainIsChild=7F3AD7A19FB3DF266CFD75ADAA292E691AB10FE6FB86AC00; DomainUserID=0183F352C2C3F2DB; Hm_lvt_820e1d92884263155b0c90f4f33b7ae2=1331703986328; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1331703981&U=1602619&S=YTcyMjg3Yjk1ZDA28619148FNWMyNjBhMThiZmI0&ChangeUser=2052158";
	football = "UserID=B2736B70A9E65841&UnionID=064E8521A67CE8C8&Gender=False&NickName=%e5%86%8d%e6%88%98%e5%b7%b4%e8%90%a8&SID=A381B4488C7F686A92DED2C4A01CC0E2";
} else if ("jyy745512".equals(key)) {
	cookie = "DomainUserID=BC70570F82AFBDCE; DomainFirstCost=BC70570F82AFBDCE; DomainIsChild=A7BD2DC7C1C528DFBB5B7C49EA23C56C95652A7AC48883B0; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1348645319&U=1602819&S=YTcyMjg3Yjk1ZDA2F1A4FD76NWMyNjBhMThiZmI0&ChangeUser=2052146";
	football = "UserID=FF5D721C8D68003F&UnionID=064E8521A67CE8C8&Gender=False&NickName=FIFA+Sophia&SID=2CF9D7124C30934DD1EA2661186495B5";
} else if ("184554543".equals(key)) {
	cookie = "DomainFirstCost=F4B53D041E348461; DomainIsChild=5C17C24478B84313CACC2B681D6B38E35C60E8AFCF460408; DomainUserID=F4B53D041E348461; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1348645714&U=1631258&S=YTcyMjg3Yjk1ZDA25F53647BNWMyNjBhMThiZmI0&ChangeUser=1631258";
	football = "UserID=F4B53D041E348461&UnionID=85846B2DDE3ED645&Gender=False&NickName=%e5%b0%8f%e5%bc%ba&SID=433179B81FFEA17DD784A337DC819448";
} else if ("chen130128".equals(key)) {
	cookie = "DomainUserID=F9403B76CF06375D; DomainFirstCost=F9403B76CF06375D; DomainIsChild=42C1C9BAC93919EDD42591FD3F6A25A18BB669D0F3C00555; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1348890702&U=1244861&S=YTcyMjg3Yjk1ZDA2FEB0396ANWMyNjBhMThiZmI0&ChangeUser=1244861";
	football = "UserID=F9403B76CF06375D&UnionID=02721F6C6DE04BA4&Gender=False&NickName=%e9%82%a3%e7%84%b6%e5%b0%8f%e4%bf%8a&SID=4B4ACAC54A7739C4023837ED2CC55E2B";
} else if ("zzq".equals(key)) {
	cookie = "DomainFirstCost=62C763E9AC34CAFE; DomainIsChild=260ACDF92F591E2233994DD1733650C06ECED8BFA394A122; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1348911961&U=1566580&S=YTcyMjg3Yjk1ZDA2E75F0EA2NWMyNjBhMThiZmI0&ChangeUser=1566580";
	football = "UserID=62C763E9AC34CAFE&UnionID=064E8521A67CE8C8&Gender=False&NickName=%e9%92%9f%e5%ad%90%e6%9c%9f&SID=7F8E40403F9F21397CAEAEFBCAD2B6FD";
} else if ("yby".equals(key)) {
	cookie = "DomainFirstCost=62C763E9AC34CAFE; DomainIsChild=260ACDF92F591E2233994DD1733650C06ECED8BFA394A122; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1348912241&U=1566580&S=YTcyMjg3Yjk1ZDA2E75F0EA2NWMyNjBhMThiZmI0&ChangeUser=2052450";
	football = "UserID=741C2E893291B791&UnionID=064E8521A67CE8C8&Gender=False&NickName=%e4%bf%9e%e4%bc%af%e7%89%99&SID=60937EF230E6D804F92565DDBE989B1A";
} else if ("shaoye".equals(key)) {
	cookie = "DomainUserID=54EE30F39F010AEC; DomainFirstCost=54EE30F39F010AEC; DomainIsChild=FB1EE5152D2A6250351C9A1195D78EFADAA759EF42025D12; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1350437716&U=1602792&S=YTcyMjg3Yjk1ZDA24BBF76E3NWMyNjBhMThiZmI0&ChangeUser=1602792";
	football = "UserID=54EE30F39F010AEC&UnionID=064E8521A67CE8C8&Gender=False&NickName=FIFA%e5%b0%91%e7%88%b7&SID=A7772698EF8810144CD52444CCE336BE";
} else if ("cc5".equals(key)) {
	cookie = "DomainFirstCost=611611237D7A160D; DomainIsChild=1585AFF41C460B7B51880318091A45603A90EE21872E571B; DomainUserID=611611237D7A160D; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1350971493&U=2030635&S=YTcyMjg3Yjk1ZDA24447F449NWMyNjBhMThiZmI0&ChangeUser=2030635";
	football = "UserID=611611237D7A160D&UnionID=85846B2DDE3ED645&Gender=False&NickName=2468&SID=7D713E0EA491EE47432B81A0D1ABEFE4";
} else if ("hop".equals(key)) {
	cookie = "DomainFirstCost=7C2F5FA5E79E5DF6; DomainIsChild=BFE23ED69946E17D746F7AD5FE4F5BE495F953DAE195A87F; DomainUserID=7C2F5FA5E79E5DF6; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1351479025&U=1664256&S=YTcyMjg3Yjk1ZDA2EB1E9624NWMyNjBhMThiZmI0&ChangeUser=1664256";
	football = "UserID=7C2F5FA5E79E5DF6&UnionID=064E8521A67CE8C8&Gender=False&NickName=Hope1126&SID=346D112F38E3291026D7757CA183AD0A";
} else if ("xhd".equals(key)) {
	cookie = "DomainUserID=4444F0593718CAF5; DomainFirstCost=4444F0593718CAF5; DomainIsChild=0EF019645739D8818CCA8415D0F98AF3B59743049E31CCCB; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1351502109&U=1602745&S=YTcyMjg3Yjk1ZDA24BBF76E3NWMyNjBhMThiZmI0&ChangeUser=1602745";
	football = "UserID=4444F0593718CAF5&UnionID=064E8521A67CE8C8&Gender=False&NickName=FIFA%e5%b0%8f%e7%9a%87%e5%b8%9d&SID=91D9B0A7A1B6D5449234373F1D59744E";
} else if ("cc3".equals(key)) {
	cookie = "DomainFirstCost=AE6FA3C7A0F0BC9B; DomainIsChild=1585AFF41C460B7B51880318091A45603A90EE21872E571B; DomainUserID=AE6FA3C7A0F0BC9B; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1353037429&U=2013823&S=YTcyMjg3Yjk1ZDA21AAFCAD0NWMyNjBhMThiZmI0&ChangeUser=2013823";
	football = "UserID=AE6FA3C7A0F0BC9B&UnionID=85846B2DDE3ED645&Gender=False&NickName=%e7%88%b7&SID=A4745207DB865A0DBF4F2AFEA7727FC4";
} else if ("jb0512".equals(key)) {
	cookie = "DomainFirstCost=6E1C8DE9491B633F; DomainIsChild=8513B35703BC2C44A97C7887CDA3A24BBFE5D669833311F7; DomainUserID=6E1C8DE9491B633F; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1353043563&U=1664058&S=YTcyMjg3Yjk1ZDA2EF37C1F2NWMyNjBhMThiZmI0&ChangeUser=1664058";
	football = "UserID=6E1C8DE9491B633F&UnionID=02721F6C6DE04BA4&Gender=False&NickName=%ef%bc%81%e9%98%bf%e5%8f%a3%e6%9c%a8%ef%bc%81&SID=F1D4E59182126D82E39CC37FEB6CAE5A";
} else if ("cc6".equals(key)) {
	cookie = "DomainFirstCost=1604C6719689FCEA; DomainIsChild=1585AFF41C460B7B51880318091A45603A90EE21872E571B; DomainUserID=1604C6719689FCEA; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1361340437&U=2046269&S=YTcyMjg3Yjk1ZDA21673C0C5NWMyNjBhMThiZmI0&ChangeUser=2046269";
	football = "UserID=1604C6719689FCEA&UnionID=85846B2DDE3ED645&Gender=False&NickName=24680&SID=D6218C31E409AF9F5E08AB19552711E1";
} else if ("szg1222".equals(key)) {
	cookie = "DomainFirstCost=275611CEFCEE9B97; DomainIsChild=33AED9B72CDC2851977F7D80BB0D94F722CA4CDE49B0A1E0; DomainUserID=275611CEFCEE9B97; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1362382604&U=1640135&S=YTcyMjg3Yjk1ZDA222056F31NWMyNjBhMThiZmI0&ChangeUser=1640135";
	football = "UserID=275611CEFCEE9B97&UnionID=064E8521A67CE8C8&Gender=False&NickName=%e5%86%ac%e9%9d%92%e7%93%9c&SID=4B07FEABACB831F7701E34AD89AC2A36";
} else if ("qwww541".equals(key)) {
	cookie = "DomainFirstCost=A3D4BFF6447DF2CE; DomainIsChild=05DC2D3E7F87A786DABC59A35817AEFB3A0978287458616A; DomainUserID=A3D4BFF6447DF2CE; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1362382798&U=1599400&S=YTcyMjg3Yjk1ZDA2F85272BCNWMyNjBhMThiZmI0&ChangeUser=1599400";
	football = "UserID=A3D4BFF6447DF2CE&UnionID=064E8521A67CE8C8&Gender=False&NickName=XiaoQiang&SID=54E7CA79243596BA8D39BEA22597219A";
} else if ("xiaococo1".equals(key)) {
	cookie = "DomainFirstCost=A2380F777170835D; DomainIsChild=1585AFF41C460B7B51880318091A45603A90EE21872E571B; DomainUserID=A2380F777170835D; ";
	loginUrl = "http://f9.xba.com.cn/AutoLogin.aspx?F=1&T=1362382915&U=1631510&S=YTcyMjg3Yjk1ZDA2EB0044C2NWMyNjBhMThiZmI0&ChangeUser=1631510";
	football = "UserID=A2380F777170835D&UnionID=064E8521A67CE8C8&Gender=False&NickName=MMMMMMMM&SID=DC540F9C247C4C652DF4996258C77282";
}
if ("s".equals(a)) {
	if (!"".equals(cookie) && SomeLock.getLock(key)) {
		//out.println("搜索中...<br/>");
		for (int j = 0; j < 30; j++) {
			HttpComponentUtilsWeb.login(loginUrl);
			String run = OperateUtil.scoutRunWeb(cookie, host, football);
			if (run.indexOf("搜索成功") != -1) {
				String result = OperateUtil.scoutResuoltWeb(cookie, host, football);
				//System.out.println("sres:" +result);
				if (result.indexOf("color:#642100") != -1) {
					//System.out.println("aabb: hei");
					SomeLock.releaseLock(key);
					response.sendRedirect("search.jsp?key=" + key + "&a=r");
					return;
				} else if (result.indexOf("color:#00db00") != -1) {
					//System.out.println("aabb: buy");
					OperateUtil.buyAndSellWeb(cookie, host, football, result);
				}
			} else {
				SomeLock.releaseLock(key);
				out.println("资金用完，或搜索出错<br/>");
				out.println("<a href='search.jsp?key=" + key + "'>重新搜索</a><br/>");
				return;
			}
		}
		SomeLock.releaseLock(key);
		out.println("搜索完成，没搜到黑人<br/>");
		out.println("<a href='search.jsp?key=" + key + "'>重新搜索</a><br/>");
	}
} else if ("r".equals(a)) {
	HttpComponentUtilsWeb.login(loginUrl);
	String result = OperateUtil.scoutResuoltWeb(cookie, host, football);
	out.println(result);
%>
<br/>
<a href='search.jsp?key=<%=key%>'>重新搜索</a><br/>
<%
} else {
%>
<input type="button" id="button" onclick="javascript:startSearch('<%=key %>')" value="开始搜索" />
<%
}
%>
</body>
</html>
    