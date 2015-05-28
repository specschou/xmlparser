package com.jrj.yqcm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;

import com.jrj.yqcm.task.CommonTask;
import com.jrj.yqcm.task.PlayerTrainTask;
import com.jrj.yqcm.utils.Config;
import com.jrj.yqcm.utils.HttpComponentUtils;

public class PresidentMain {
	public static void start() throws Exception {
		List<Properties> list = Config.list;
		int i = 0;
		for (Properties itemProps : list) {
			try {
				String name = itemProps.getProperty("name");
				// String cookie = itemProps.getProperty("cookie");
				String cookie = String.valueOf(i);
				String loginUrl = itemProps.getProperty("loginUrl");
				String host = itemProps.getProperty("host");
				HttpComponentUtils.login(loginUrl, cookie, true);
				PresidentThread thread = new PresidentThread(name, cookie,
						loginUrl, host);
				thread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	public static void main(String[] args) throws Exception {
		start();
	}
}
