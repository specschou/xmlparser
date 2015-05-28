package com.jrj.yqcm.utils;

import java.util.HashMap;
import java.util.Map;

public class SomeLock {
	private static Map<String, Boolean> map = new HashMap<String, Boolean>();

	public static synchronized boolean getLock(String key) {
		if (map.get(key) == null || !map.get(key)) {
			map.put(key, true);
			return true;
		}
		return false;
	}

	public static void releaseLock(String key) {
		map.put(key, false);
	}
}
