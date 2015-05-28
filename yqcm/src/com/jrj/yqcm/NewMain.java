package com.jrj.yqcm;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;

import com.jrj.yqcm.task.CommonTask;
import com.jrj.yqcm.task.PlayerTrainTask;
import com.jrj.yqcm.utils.Constants;
import com.jrj.yqcm.utils.PropUtil;

public class NewMain {
	public static final String DIR = "/home/chuanyu.zhou/qc/webapps/WEB-INF/conf/";

	public static void main(String[] args) throws Exception {
		String file = DIR + "list.property";
		Properties listProps = PropUtil.getProperties(file);
		System.out.println(listProps);
		int i = 0;
		for (Object o : listProps.keySet()) {
			try {
				Map<String, String> params = new HashMap<String, String>();
				Map<String, String> timeParams = new HashMap<String, String>();
				Map<String, String> wcPrams = new HashMap<String, String>();
				String key = o.toString();
				String itemFile = key + ".property";
				PropUtil.createFile(itemFile);
				Properties itemProps = PropUtil.getProperties(DIR + itemFile);
				// System.out.println(itemProps);
				String name = itemProps.getProperty("name");
				String cookie = itemProps.getProperty("cookie");
				String loginUrl = itemProps.getProperty("loginUrl");
				String host = itemProps.getProperty("host");
				String playerId = itemProps.getProperty("playerId");
				String taskType = itemProps.getProperty("taskType");
				String ticket = itemProps.getProperty("ticket");
				String activeBag = itemProps.getProperty("activeBag");
				String stadium = itemProps.getProperty("stadium");
				String trainIds = itemProps.getProperty("trainIds");
				String wcType = itemProps.getProperty("wcType");
				String wcCaptain = itemProps.getProperty("wcCaptain");
				String wcMatch = itemProps.getProperty("wcMatch");
				String wcrc = itemProps.getProperty("wcrc");
				String wctid = itemProps.getProperty("wctid");
				String wcStartTime = itemProps.getProperty("wcStartTime");
				String wcEndTime = itemProps.getProperty("wcEndTime");
				String boss = itemProps.getProperty("boss");
				String plotOrder = itemProps.getProperty("plotOrder");
				String plotCategory = itemProps.getProperty("plotCategory");
				String trainTime = itemProps.getProperty("trainTime");
				if (ticket != null) {
					params.put("ticket", ticket);
				}
				if (activeBag != null) {
					params.put("activeBag", activeBag);
				}
				if (stadium != null) {
					timeParams.put("stadium", stadium);
				}
				if (trainIds != null) {
					timeParams.put("trainIds", trainIds);
				}
				if (trainIds != null) {
					timeParams.put("boss", boss);
				}
				if (plotOrder != null) {
					timeParams.put("plotOrder", plotOrder);
				}
				if (plotCategory != null) {
					timeParams.put("plotCategory", plotCategory);
				}
				Timer timer = new Timer();
				if (playerId != null) {
					timer.schedule(new PlayerTrainTask(name, playerId, cookie,
							loginUrl, host, trainTime), 0, 1 * 60 * 1000 + 1
							* 1000);
				}
				TaskOpThread toThread = null;
				if (taskType != null) {
					TaskQueue tq = new TaskQueue(host, cookie, name);
					int lrt = 90 * 60;
					try {
						// lrt = Integer.parseInt(leftRefreshTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
					toThread = new TaskOpThread(name, tq, cookie, loginUrl,
							host, taskType, lrt, trainTime);
					toThread.start();
					System.out.println("toThread state.");
				}
				if (params.size() > 0) {
					timer.schedule(new CommonTask(name, playerId, cookie,
							loginUrl, host, params), 0, 5 * 60 * 1000);
					System.out.println("CommonTask state.");
				}
				if (timeParams.size() > 0) {
					TimeThread timeThread = new TimeThread(name, cookie,
							loginUrl, host, timeParams);
					timeThread.start();
					System.out.println("timeThread state.");
				}
				if (wcType != null) {
					wcPrams.put("wcType", wcType);
				}
				if (wcCaptain != null) {
					wcPrams.put("wcCaptain", wcCaptain);
				}
				if (wcrc != null) {
					wcPrams.put("wcrc", wcrc);
				}
				if (wctid != null) {
					wcPrams.put("wctid", wctid);
				}
				if (wcStartTime != null) {
					wcPrams.put("wcStartTime", wcStartTime);
				}
				if (wcEndTime != null) {
					wcPrams.put("wcEndTime", wcEndTime);
				}
				if (wcMatch != null) {
					wcPrams.put("wcMatch", wcMatch);
				}
				if (wcType != null) {
					WorldCupThread wcThread = new WorldCupThread(name, cookie,
							loginUrl, host, wcrc, wctid, wcPrams);
					wcThread.start();
					System.out.println("wcThread state.");
				} else {
					if (toThread != null) {
						toThread.setStart(true);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}
}
