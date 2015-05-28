package com.jrj.yqcm.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jrj.yqcm.utils.OperateUtil;

public class TestNew {
	public static void main(String[] args) {
		// testGetPlayerList("2.property");
		// testRegex();
		testWhile();
	}

	public static void testWhile() {
		int i = 0;
		do {
			i++;
			System.out.println(i);
		} while (i < 10);
	}

	public static void testGetPlayerList(String fileName) {
		OperateUtil.getPlayerList(fileName);
	}

	public static void testRegex() {
		String content = "aa1321bbaafkdjfbblsfjsdklfjaaiiisbb";
		Pattern p = Pattern.compile("(aa)(.*?)(bb)");
		Matcher m = p.matcher(content);
		while (m.find()) {
			System.out.println(m.group());
		}
	}
}
