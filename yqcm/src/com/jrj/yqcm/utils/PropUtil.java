package com.jrj.yqcm.utils;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class PropUtil {
	public static Properties getProperties(String file) throws Exception {
		System.out.println("file:" + file);
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
