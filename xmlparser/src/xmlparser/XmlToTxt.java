package xmlparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XmlToTxt {
	public static void main(String[] args) {
		translate("E:\\xml3\\txt\\");
	}

	public static void translate(String resultDir) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		File dir = new File("E:\\xml3\\xml");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		for (File file : files) {
			String channelName = file.getName().substring(0,
					file.getName().indexOf("."));
			i++;
			// if (i > 100)
			// break;
			if (i % 1000 == 0) {
				System.out.println(i);
			}
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
				continue;
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> nodeList = root
						.selectNodes("//PROGRAM//PGM//ITEM//MAIN");
				String lastDate = null;
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < nodeList.size(); j++) {
					Node node = nodeList.get(j);
					String date = node.valueOf("@date");
					date = date.replace("-", "");
					if (lastDate == null || !lastDate.equals(date)) {
						sb.append("Channel:");
						sb.append(channelName);
						sb.append("\r\n");
						sb.append("Date:");
						sb.append(date);
						sb.append("\r\n");
					}
					String name = node.valueOf("@name");
					String time = node.valueOf("@time");
					time = time.replace(":", "").substring(0, 4);
					String nextDate = null;
					String nextTime = null;
					if (j < nodeList.size() - 1) {
						Node nextNode = nodeList.get(j + 1);
						nextDate = nextNode.valueOf("@date");
						nextDate = nextDate.replace("-", "");
						nextTime = null;
						if (!date.equals(nextDate)) {
							nextTime = "0000";
						} else {
							nextTime = nextNode.valueOf("@time");
							nextTime = nextTime.replace(":", "")
									.substring(0, 4);
						}

					} else {
						nextTime = "0000";
					}
					sb.append(time);
					sb.append("-");
					sb.append(nextTime);
					sb.append("| ");
					sb.append(name);
					sb.append("\r\n");
					if (!date.equals(nextDate)) {
						sb.append("\r\n");
					}
					lastDate = date;
				}
				writeFile(sb.toString(), resultDir + channelName + ".txt");
				// System.out.println(channelName + "," + sb.toString());
			}
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

	public static void writeFile(String s, String fileName) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(s);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
