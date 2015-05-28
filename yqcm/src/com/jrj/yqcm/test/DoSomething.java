package com.jrj.yqcm.test;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.UrlConstants;

public class DoSomething {
	public static final String DIR = "D:/workspace/yqcm/webapps/WEB-INF/conf/";

	public static void main(String[] args) throws Exception {
		String file = DIR + "list.property";
		Properties listProps = getProperties(file);
		System.out.println(listProps);
		int i = 0;
		for (Object o : listProps.keySet()) {
			try {
				String key = o.toString();
				String itemFile = key + ".property";
				createFile(itemFile);
				Properties itemProps = getProperties(DIR + itemFile);
				// System.out.println(itemProps);
				String name = itemProps.getProperty("name");
				String cookie = itemProps.getProperty("cookie");
				String loginUrl = itemProps.getProperty("loginUrl");
				String host = itemProps.getProperty("host");
				String trainIds = itemProps.getProperty("trainIds");

				HttpComponentUtils.login(loginUrl, cookie);
				if (trainIds != null) {
					String[] a = trainIds.split(",");
					for (String s : a) {
						String url = (host + UrlConstants.MARKET_TRANINER)
								.replace("$1", "7").replace("$2", s);
						String content = HttpComponentUtils.get(url, cookie);
						System.out.println(name + ":" + formatContent(content));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	public static Properties getProperties(String file) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(file));
		return props;
	}

	public static void createFile(String file) throws Exception {
		File f = new File(DIR + file);
		if (!f.exists()) {
			f.createNewFile();
		}
	}

	public static String formatContent(String content) {
		int index = -1;
		int begin = -1;
		int end = -1;
		if ((index = content.indexOf("public_d_process")) != -1) {
			begin = index + 18;
		} else if ((index = content.indexOf("public_d_content")) != -1) {
			begin = index + 18;
		}
		end = content.indexOf("</div>", index);
		if (begin != -1 && end != -1) {
			content = content.substring(begin, end);
		}
		return content;
	}
}
