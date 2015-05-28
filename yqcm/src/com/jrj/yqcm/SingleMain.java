package com.jrj.yqcm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.jrj.yqcm.utils.Config;

public class SingleMain {
	public static void start() throws Exception {
		List<Properties> list = Config.list;
		int i = 0;
		for (Properties itemProps : list) {
			try {
				Map<String, String> params = new HashMap<String, String>();
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
					params.put("stadium", stadium);
				}
				if (trainIds != null) {
					params.put("trainIds", trainIds);
				}
				if (trainIds != null) {
					params.put("boss", boss);
				}
				if (plotOrder != null) {
					params.put("plotOrder", plotOrder);
				}
				if (plotCategory != null) {
					params.put("plotCategory", plotCategory);
				}
				if (playerId != null) {
					params.put("playerId", playerId);
				}
				if (trainTime != null) {
					params.put("trainTime", trainTime);
				}
				if (wcType != null) {
					params.put("wcType", wcType);
				}
				if (wcCaptain != null) {
					params.put("wcCaptain", wcCaptain);
				}
				if (wcrc != null) {
					params.put("wcrc", wcrc);
				}
				if (wctid != null) {
					params.put("wctid", wctid);
				}
				if (wcStartTime != null) {
					params.put("wcStartTime", wcStartTime);
				}
				if (wcEndTime != null) {
					params.put("wcEndTime", wcEndTime);
				}
				if (wcMatch != null) {
					params.put("wcMatch", wcMatch);
				}
				if (taskType != null) {
					params.put("taskType", taskType);
				}
				params.put("lrt", "5400");
				if (params.size() > 0) {
					try {
						Thread.sleep(8 * 1000);
					} catch (Exception e) {
					}
					AllThread allThread = new AllThread(name, cookie, loginUrl,
							host, params);
					allThread.start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}

	public static void main(String[] args) throws Exception {
		start();
	}
}
