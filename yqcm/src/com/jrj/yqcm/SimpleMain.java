package com.jrj.yqcm;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.jrj.yqcm.utils.Constants;
import com.jrj.yqcm.utils.PropUtil;

public class SimpleMain {
	public static void main(String[] args) throws Exception {
		String file = Constants.DIR + "list.property";
		Properties listProps = PropUtil.getProperties(file);
		int i = 0;
		for (Object o : listProps.keySet()) {
			try {
				Map<String, String> params = new HashMap<String, String>();
				String key = o.toString();
				String itemFile = key + ".property";
				PropUtil.createFile(itemFile);
				Properties itemProps = PropUtil.getProperties(Constants.DIR
						+ itemFile);
				String name = itemProps.getProperty("name");
				String cookie = itemProps.getProperty("cookie");
				String loginUrl = itemProps.getProperty("loginUrl");
				String host = itemProps.getProperty("host");
				String playerId = itemProps.getProperty("playerId");
				String ticket = itemProps.getProperty("ticket");
				String activeBag = itemProps.getProperty("activeBag");
				String stadium = itemProps.getProperty("stadium");
				String trainIds = itemProps.getProperty("trainIds");
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
				SimpleThread simpleThread = new SimpleThread(name, cookie, loginUrl,
						host, params);
				simpleThread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
	}
}
