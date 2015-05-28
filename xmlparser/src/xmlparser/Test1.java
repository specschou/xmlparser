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
						c.get(Calendar.YEAR) + "��"
								+ (c.get(Calendar.MONTH) + 1) + "��" });
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

	// ��xml��Movie���ļ����滻
	public static void parseAll() {
		XMLWriter writer = null;
		// Ŀ���ļ���
		String destPath = "E:\\xmltest\\dest\\";
		// ����Ŀ����Ҫ�������ַ���ΪGBK
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");

		SAXReader reader = new SAXReader();
		Document doc = null;
		// �����ļ���
		File dir = new File("E:\\xmltest\\src");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		for (File file : files) {
			i++;
			// ��ȡxml�ļ�
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				// ѡȡObject�ڵ�
				List<Node> list = root.selectNodes("//Object");
				String fileName = "";
				for (Node node : list) {
					// ֻ��ElementTypeΪMovie�Ľڵ���в���
					if ("Movie".equals(node.valueOf("@ElementType"))) {
						@SuppressWarnings("unchecked")
						List<Node> props = node.selectNodes("Property");
						// ����Property
						for (Node prop : props) {
							String name = prop.valueOf("@Name");
							// ֻ��NameΪFileURL��Property���в���
							if ("FileURL".equals(name)) {
								// ��ȡ��������
								String text = prop.getText();
								if (text != null) {
									// ȡ�þ�����ļ���
									int index = text.lastIndexOf("/");
									if (index != -1) {
										fileName = text.substring(index + 1);
										// �滻ftp·��
										String newName = "ftp://wacos:wacos@172.25.41.68//opt/wacos/iptv1/2012/11/01/content/"
												+ fileName;
										prop.setText(newName);
										// ���滻�������д���µ�xml�ļ���
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
	 * ��ȡ�ļ���List��
	 * 
	 * @param fileName
	 *            �ļ���
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
