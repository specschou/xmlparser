package xmlparser;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

public class XMLReplace {
	public static void main(String[] args) {
		replace();
	}

	public static void replace() {
		parseAll();
	}

	public static void parseAll() {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> fileList = readFile("E:\\xml2\\han.sh");
		System.out
				.println(fileList
						.contains("0573d4254b3a4c86972fd2c2eb4f86db.H2641300000mp296.ts"));
		System.out.println(fileList.get(72));
		XMLWriter writer = null;
		String destPath = "E:\\xml2\\dest\\";
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");

		SAXReader reader = new SAXReader();
		Document doc = null;
		File dir = new File("E:\\xml2\\src");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		for (File file : files) {
			i++;
			// if (i > 2)
			// break;
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> list = root.selectNodes("//Object");
				String fileName = "";
				for (Node node : list) {
					if ("Movie".equals(node.valueOf("@ElementType"))
							|| "Picture".equals(node.valueOf("@ElementType"))) {
						@SuppressWarnings("unchecked")
						List<Node> props = node.selectNodes("Property");
						for (Node prop : props) {
							String name = prop.valueOf("@Name");
							if ("FileURL".equals(name)) {
								String text = prop.getText();
								if (text != null) {
									int index = text.lastIndexOf("/");
									if (index != -1) {
										if ("Movie".equals(node
												.valueOf("@ElementType"))) {
											fileName = text
													.substring(index + 1);
											if (fileList.contains(fileName)) {
												List<String> itemList = map
														.get(fileName);
												if (itemList == null) {
													itemList = new ArrayList<String>();
													map.put(fileName, itemList);
												}
												itemList.add(file.getName());
												String newName = "ftp://wacos:wacos@172.25.41.68//opt/wacos/iptv1/2012/11/01/content/"
														+ fileName;
												prop.setText(newName);
											}
										} else if ("Picture".equals(node
												.valueOf("@ElementType"))) {
											String newName = "ftp://wacos:wacos@172.25.41.68//opt/wacos/iptv1/2012/11/01/picture/"
													+ fileName;
											prop.setText(newName);
										}
										System.out.println(i);
									}
								}
							}
						}
					}
				}
				if (fileList.contains(fileName)) {
					try {
						writer = new XMLWriter(new FileWriter(destPath
								+ file.getName()), format);
						writer.write(doc);
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		writeFile(map, "E:\\xml2\\result.txt");
	}

	public static void writeFile(Map<String, List<String>> map, String fileName) {
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(fileName));
			int count = 0;
			for (String key : map.keySet()) {
				StringBuilder sb = new StringBuilder();
				sb.append(key);
				sb.append(":");
				List<String> list = map.get(key);
				if (list.size() > 1) {
					for (String s : list) {
						sb.append(s);
						sb.append(",");
						count++;
					}
					sb.append("\r\n");
					out.write(sb.toString().getBytes());
				}
			}
			System.out.println(map.size() + "," + count);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out = null;
		}
	}

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
