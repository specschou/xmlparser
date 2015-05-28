package com.jrj.yqcm.test;

import com.jrj.yqcm.utils.OperateUtil;

public class TestThread implements Runnable {
	private String mainCookie;
	private String host;
	private String targetId;

	public TestThread(String mainCookie, String host, String targetId) {
		this.mainCookie = mainCookie;
		this.host = host;
		this.targetId = targetId;
	}

	public void run() {
		OperateUtil.openGiftBag(targetId, mainCookie, host);
	}

}
