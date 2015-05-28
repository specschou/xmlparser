package com.jrj.yqcm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			System.out.println("MyListener.");
//			SingleMain.start();
			Main.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
