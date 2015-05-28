package com.jrj.yqcm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;

import com.jrj.yqcm.task.CommonTask;
import com.jrj.yqcm.task.PlayerTrainTask;
import com.jrj.yqcm.utils.ConfigNew;
import com.jrj.yqcm.utils.HttpComponentUtils;

public class MainNew {
	public static void start() throws Exception {
		List<Properties> list = ConfigNew.list;
		int i = 0;
		for (Properties itemProps : list) {
			try {
				Map<String, String> params = new HashMap<String, String>();
				Map<String, String> timeParams = new HashMap<String, String>();
				Map<String, String> wcPrams = new HashMap<String, String>();
				String name = itemProps.getProperty("name");
//				String cookie = itemProps.getProperty("cookie");
				String cookie = String.valueOf(i);
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
				String unionSL = itemProps.getProperty("unionSL");
				String unionWarTask = itemProps.getProperty("unionWarTask");
				HttpComponentUtils.login(loginUrl, cookie, true);
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
				if (unionSL != null) {
					timeParams.put("unionSL", unionSL);
				}
				if (unionWarTask != null) {
					timeParams.put("unionWarTask", unionWarTask);
				}
				Timer timer = new Timer();
				if (playerId != null) {
					timer.schedule(new PlayerTrainTask(name, playerId, cookie,
							loginUrl, host, trainTime), 0, 1 * 60 * 1000 + 1
							* 1000);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (params.size() > 0) {
					timer.schedule(new CommonTask(name, playerId, cookie,
							loginUrl, host, params), 0, 5 * 60 * 1000);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (timeParams.size() > 0) {
					TimeThread timeThread = new TimeThread(name, cookie,
							loginUrl, host, timeParams);
					timeThread.start();
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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (wcType != null) {
					WorldCupThread wcThread = new WorldCupThread(name, cookie,
							loginUrl, host, wcrc, wctid, wcPrams);
					wcThread.start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(2 * 1000);
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
