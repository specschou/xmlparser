package com.jrj.yqcm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigNew {
	public static String task = "[{\"taskId\":\"75\",\"sceneId\":\"23\",\"type\":\"2\",\"leftMatchId\":[\"-364\", \"-365\", \"-366\", \"-367\", \"-368\"]},{\"taskId\":\"84\",\"sceneId\":\"23\",\"type\":\"1\",\"leftMatchId\":[\"-364\", \"-364\", \"-364\", \"-364\", \"-364\", \"-364\", \"-364\", \"-364\", \"-364\", \"-364\"]},{\"taskId\":\"77\",\"sceneId\":\"25\",\"type\":\"2\",\"leftMatchId\":[\"-374\", \"-375\", \"-376\", \"-377\", \"-378\"]},{\"taskId\":\"86\",\"sceneId\":\"25\",\"type\":\"1\",\"leftMatchId\":[\"-374\", \"-374\", \"-374\", \"-374\", \"-374\", \"-374\", \"-374\", \"-374\", \"-374\", \"-374\"]},{\"taskId\":\"79\",\"sceneId\":\"27\",\"type\":\"2\",\"leftMatchId\":[\"-384\", \"-385\", \"-386\", \"-387\", \"-388\"]},{\"taskId\":\"88\",\"sceneId\":\"27\",\"type\":\"1\",\"leftMatchId\":[\"-384\", \"-384\", \"-384\", \"-384\", \"-384\", \"-384\", \"-384\", \"-384\", \"-384\", \"-384\"]},{\"taskId\":\"80\",\"sceneId\":\"28\",\"type\":\"2\",\"leftMatchId\":[\"-389\", \"-390\", \"-391\", \"-392\", \"-393\"]},{\"taskId\":\"89\",\"sceneId\":\"28\",\"type\":\"1\",\"leftMatchId\":[\"-389\", \"-389\", \"-389\", \"-389\", \"-389\", \"-389\", \"-389\", \"-389\", \"-389\", \"-389\"]},{\"taskId\":\"76\",\"sceneId\":\"24\",\"type\":\"2\",\"leftMatchId\":[\"-369\", \"-370\", \"-371\", \"-372\", \"-373\"]},{\"taskId\":\"85\",\"sceneId\":\"24\",\"type\":\"1\",\"leftMatchId\":[\"-369\", \"-369\", \"-369\", \"-369\", \"-369\", \"-369\", \"-369\", \"-369\", \"-369\", \"-369\"]},{\"taskId\":\"94\",\"sceneId\":\"24\",\"type\":\"3\",\"leftMatchId\":[\"-373\", \"-373\", \"-373\", \"-373\", \"-373\"]},{\"taskId\":\"78\",\"sceneId\":\"26\",\"type\":\"2\",\"leftMatchId\":[\"-379\", \"-380\", \"-381\", \"-382\", \"-383\"]},{\"taskId\":\"87\",\"sceneId\":\"26\",\"type\":\"1\",\"leftMatchId\":[\"-379\", \"-379\", \"-379\", \"-379\", \"-379\", \"-379\", \"-379\", \"-379\", \"-379\", \"-379\"]},{\"taskId\":\"96\",\"sceneId\":\"26\",\"type\":\"3\",\"leftMatchId\":[\"-383\", \"-383\", \"-383\", \"-383\", \"-383\"]},{\"taskId\":\"51\",\"sceneId\":\"7\",\"type\":\"2\",\"leftMatchId\":[\"-124\", \"-125\", \"-126\", \"-127\", \"-128\"]},{\"taskId\":\"56\",\"sceneId\":\"7\",\"type\":\"1\",\"leftMatchId\":[\"-124\", \"-124\", \"-124\", \"-124\", \"-124\", \"-124\", \"-124\", \"-124\", \"-124\", \"-124\"]},{\"taskId\":\"61\",\"sceneId\":\"7\",\"type\":\"3\",\"leftMatchId\":[\"-128\", \"-128\", \"-128\", \"-128\", \"-128\"]},{\"taskId\":\"52\",\"sceneId\":\"8\",\"type\":\"2\",\"leftMatchId\":[\"-129\", \"-130\", \"-131\", \"-132\", \"-133\"]},{\"taskId\":\"57\",\"sceneId\":\"8\",\"type\":\"1\",\"leftMatchId\":[\"-129\", \"-129\", \"-129\", \"-129\", \"-129\", \"-129\", \"-129\", \"-129\", \"-129\", \"-129\"]},{\"taskId\":\"62\",\"sceneId\":\"8\",\"type\":\"3\",\"leftMatchId\":[\"-133\", \"-133\", \"-133\", \"-133\", \"-133\"]}]";
	public static List<Properties> list = new ArrayList<Properties>();
	static {
		Properties p1 = new Properties();
		Properties p2 = new Properties();
		Properties p3 = new Properties();

		setP1(p1);
		setP2(p2);
		setP3(p3);

		list.add(p1);
		list.add(p2);
		list.add(p3);
	}

	public static void setP1(Properties p1) {
		p1.setProperty("name", "specschou2- 34");
		p1.setProperty("playerId", "13380036");
		p1.setProperty(
				"cookie",
				"DomainFirstCost=5012F380FB878E9D; DomainIsChild=DB98EB1E077CD956DA836F8ABE566533736841B841192F56; DomainUserID=5012F380FB878E9D; ");
		p1.setProperty(
				"loginUrl",
				"http://f37.xba.com.cn/AutoLogin.aspx?F=1&T=1353473202&U=1285614&S=YTcyMjg3Yjk1ZDA25C0E4754NWMyNjBhMThiZmI0&ChangeUser=1285614");
		p1.setProperty("host", "http://f37.xba.com.cn");
		p1.setProperty("trainIds",
				"13380036,13380040,13379979,13379992,13380038");
		p1.setProperty("taskType", "1,1,1");
		p1.setProperty("ticket", "1");
		p1.setProperty("activeBag", "1");
		p1.setProperty("stadium", "1");
		p1.setProperty("boss", "1");
		p1.setProperty("trainCount", "25");
		// p1.setProperty("wcType", "2");
		// p1.setProperty("wcrc", "6");
		// p1.setProperty("wctid", "585");
		// p1.setProperty("wcStartTime", "9");
		// p1.setProperty("wcEndTime", "12");
		// p1.setProperty("wcCaptain", "1≈÷");
		// p1.setProperty("wcPw", "134");
		// p1.setProperty("wcMatch", "2");
		// p1.setProperty("plotOrder", "4");
		// p1.setProperty("plotCategory", "1191");
		p1.setProperty("trainTime", "7-23");
		// p1.setProperty("unionSL", "150");
		// p1.setProperty("unionWarTask", "1");
	}

	public static void setP2(Properties p2) {
		p2.setProperty("name", "specschou-34");
		p2.setProperty("playerId", "13380090");
		p2.setProperty(
				"cookie",
				"DomainFirstCost=85EB5C0A1ADAD203; DomainIsChild=DB98EB1E077CD956DA836F8ABE566533736841B841192F56; DomainUserID=85EB5C0A1ADAD203; ");
		p2.setProperty(
				"loginUrl",
				"http://f37.xba.com.cn/AutoLogin.aspx?F=1&T=1353473380&U=1291562&S=YTcyMjg3Yjk1ZDA25E119EE2NWMyNjBhMThiZmI0&ChangeUser=1291562");
		p2.setProperty("host", "http://f37.xba.com.cn");
		p2.setProperty("trainIds",
				"13380090,13380092,13379995,13380027,13380089");
		p2.setProperty("taskType", "1,1,1");
		p2.setProperty("ticket", "1");
		p2.setProperty("activeBag", "1");
		p2.setProperty("stadium", "1");
		p2.setProperty("boss", "1");
		p2.setProperty("trainCount", "25");
		// p2.setProperty("wcType", "2");
		// p2.setProperty("wcrc", "6");
		// p2.setProperty("wctid", "585");
		// p2.setProperty("wcStartTime", "14");
		// p2.setProperty("wcEndTime", "17");
		// p2.setProperty("wcCaptain", "∏•π«…≠");
		// p2.setProperty("wcPw", "134");
		// p2.setProperty("wcMatch", "1");
		// p2.setProperty("plotOrder", "2");
		// p2.setProperty("plotCategory", "1173");
		p2.setProperty("trainTime", "7-23");
	}

	public static void setP3(Properties p3) {
		p3.setProperty("name", "specschou4-34");
		p3.setProperty("playerId", "13380094");
		p3.setProperty(
				"cookie",
				"DomainFirstCost=F789864987AD3452; DomainIsChild=DB98EB1E077CD956DA836F8ABE566533736841B841192F56; DomainUserID=F789864987AD3452; ");
		p3.setProperty(
				"loginUrl",
				"http://f37.xba.com.cn/AutoLogin.aspx?F=1&T=1353473497&U=1291656&S=YTcyMjg3Yjk1ZDA24F2AD92ANWMyNjBhMThiZmI0&ChangeUser=1291656");
		p3.setProperty("host", "http://f37.xba.com.cn");
		p3.setProperty("trainIds",
				"13380094,13380095,13380010,13380029,13380029");
		p3.setProperty("taskType", "1,1,1");
		p3.setProperty("ticket", "1");
		p3.setProperty("activeBag", "1");
		p3.setProperty("stadium", "1");
		p3.setProperty("trainCount", "100");
		p3.setProperty("boss", "1");
		// p3.setProperty("wcType", "1");
		// p3.setProperty("wcrc", "6");
		// p3.setProperty("wctid", "585");
		// p3.setProperty("wcStartTime", "9");
		// p3.setProperty("wcEndTime", "12");
		// p3.setProperty("wcPw", "134");
		// p3.setProperty("wcMatch", "0");
		// p3.setProperty("plotOrder", "8");
		// p3.setProperty("plotCategory", "1123");
		p3.setProperty("trainTime", "7-23");
	}
}
