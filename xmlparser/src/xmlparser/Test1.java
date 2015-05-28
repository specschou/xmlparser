package xmlparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Test1 {
	public static void main(String[] args) {
		// replace();
		testMonth();
	}

	public static void testMonth() {
		List<String[]> monthList = new ArrayList<String[]>();
		String beginMonth = "201312";

		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, 1);
		DateFormat df = new SimpleDateFormat("yyyyMM");
		String month = df.format(now.getTime());

		String tmpMonth = month;
		while (tmpMonth.compareTo(beginMonth) > 0) {
			try {
				Date date = df.parse(tmpMonth);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.MONTH, -1);
				monthList.add(new String[] {
						tmpMonth,
						c.get(Calendar.YEAR) + "年"
								+ (c.get(Calendar.MONTH) + 1) + "月" });
				tmpMonth = df.format(c.getTime());
			} catch (ParseException e) {
				break;
			}
		}

		for (String[] a : monthList) {
			System.out.println(a[0] + "," + a[1]);
		}
	}

	public static void replace() {
		parseAll();
	}

	// 将xml中Movie的文件名替换
	public static void parseAll() {
		XMLWriter writer = null;
		// 目标文件夹
		String destPath = "E:\\xmltest\\dest\\";
		// 这里目的主要是设置字符集为GBK
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");

		SAXReader reader = new SAXReader();
		Document doc = null;
		// 遍历文件夹
		File dir = new File("E:\\xmltest\\src");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		for (File file : files) {
			i++;
			// 读取xml文件
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				// 选取Object节点
				List<Node> list = root.selectNodes("//Object");
				String fileName = "";
				for (Node node : list) {
					// 只对ElementType为Movie的节点进行操作
					if ("Movie".equals(node.valueOf("@ElementType"))) {
						@SuppressWarnings("unchecked")
						List<Node> props = node.selectNodes("Property");
						// 遍历Property
						for (Node prop : props) {
							String name = prop.valueOf("@Name");
							// 只对Name为FileURL的Property进行操作
							if ("FileURL".equals(name)) {
								// 获取具体内容
								String text = prop.getText();
								if (text != null) {
									// 取得具体的文件名
									int index = text.lastIndexOf("/");
									if (index != -1) {
										fileName = text.substring(index + 1);
										// 替换ftp路径
										String newName = "ftp://wacos:wacos@172.25.41.68//opt/wacos/iptv1/2012/11/01/content/"
												+ fileName;
										prop.setText(newName);
										// 将替换后的内容写入新的xml文件中
										try {
											writer = new XMLWriter(
													new FileWriter(destPath
															+ file.getName()),
													format);
											writer.write(doc);
											writer.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										System.out.println(i);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 读取文件到List中
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static List<String> readFile(String fileName) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String s = null;
			while ((s = reader.readLine()) != null) {
				list.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				reader = null;
			}
		}
		return list;
	}
}
