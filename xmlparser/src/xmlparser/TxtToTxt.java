package xmlparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtToTxt {
	public static void main(String[] args) {
		translate();
	}

	public static void translate() {
		List<String> list = readFile("E:\\xml3\\1.txt");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			if (s != null && s.length() > 0) {
				String[] line = s.split("  ");
				if (line.length == 1) {
					sb.append(line[0]);
					sb.append("\r\n");
				} else if (line.length == 2) {
					String nextTime = "0600";
					for (int j = 1; j < 5; j++) {
						if (i < list.size() - j) {
							String nextS = list.get(i + j);
							String[] nextLine = nextS.split("  ");
							if (nextLine != null && nextLine.length == 2) {
								nextTime = nextLine[0].replace(":", "");
								break;
							}
						}
					}
					sb.append(line[0].replace(":", ""));
					sb.append("-");
					sb.append(nextTime);
					sb.append("| ");
					sb.append(line[1]);
					sb.append("\r\n");
				}
			}
		}
		writeFile(sb.toString(), "E:\\xml3\\2.txt");
	}

	public static void translate1() {
		List<String> list = readFile("E:\\xml3\\1.txt");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			if (s != null && s.length() > 0) {
				String[] line = s.split(" ");
				if (line.length == 1) {
					// char c = line[0].charAt(0);
					// if (c >= '0' && c <= '9') {
					// sb.append("Date:");
					// sb.append(line[0]);
					// sb.append("\r\n");
					// } else {
					// sb.append("Channel:");
					// sb.append(line[0]);
					// sb.append("\r\n");
					// }
					if (isDate(s)) {
						sb.append("Date:");
						sb.append(s);
						sb.append("\r\n");
					} else {
						sb.append("Channel:");
						sb.append(line[0]);
						sb.append("\r\n");
					}
				} else if (line.length == 2) {
					String nextTime = "0600";
					for (int j = 1; j < 5; j++) {
						if (i < list.size() - j) {
							String nextS = list.get(i + j);
							String[] nextLine = nextS.split(" ");
							if (nextLine != null && nextLine.length == 2) {
								nextTime = nextLine[0].replace(":", "");
								break;
							}
						}
					}
					sb.append(line[0].replace(":", ""));
					sb.append("-");
					sb.append(nextTime);
					sb.append("| ");
					sb.append(line[1]);
					sb.append("\r\n");
				}
			}
		}
		writeFile(sb.toString(), "E:\\xml3\\2.txt");
	}

	public static boolean isDate(String s) {
		return (s.indexOf("ÔÂ") != -1 || s.indexOf("/") != -1 || s.indexOf("-") != -1);
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
