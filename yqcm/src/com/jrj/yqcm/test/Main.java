package com.jrj.yqcm.test;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;

import com.jrj.yqcm.task.PlayerTrainTask;
import com.jrj.yqcm.task.TaskOpTask;
import com.jrj.yqcm.task.TrainMatchTask;
import com.jrj.yqcm.utils.Constants;

public class Main {
	public static void main(String[] args) throws Exception {
		String file = Constants.DIR + "list.property";
		Properties listProps = getProperties(file);
		System.out.println(listProps);
		int i = 0;
		for (Object o : listProps.keySet()) {
			try {
				String key = o.toString();
				String itemFile = key + ".property";
				createFile(itemFile);
				Properties itemProps = getProperties(Constants.DIR + itemFile);
				// System.out.println(itemProps);
				String name = itemProps.getProperty("name");
				String cookie = itemProps.getProperty("cookie");
				String loginUrl = itemProps.getProperty("loginUrl");
				String host = itemProps.getProperty("host");
				Timer timer = new Timer();
				if (itemProps.getProperty("matchId") != null) {
					timer.schedule(
							new TrainMatchTask(name, itemProps
									.getProperty("matchId"), cookie, loginUrl,
									host), i * 10 * 1000, 3 * 60 * 1000 + 10
									* 1000);
				}
				if (itemProps.getProperty("playerId") != null) {
//					timer.schedule(
//							new PlayerTrainTask(name, itemProps
//									.getProperty("playerId"), cookie, loginUrl,
//									host), i * 1 * 1000 + 5000, 1 * 60 * 1000
//									+ 1 * 1000);
				}
				if (itemProps.getProperty("task") != null) {
					List<String> allowTask = Arrays.asList(itemProps
							.getProperty("task").split(","));
					timer.schedule(new TaskOpTask(name, allowTask, cookie,
							loginUrl, host), i * 10 * 1000 + 5000, 5 * 60
							* 1000 + 10 * 1000);
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
		File f = new File(Constants.DIR + file);
		if (!f.exists()) {
			f.createNewFile();
		}
	}
}
