package xmlparser;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Workbook;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Parser {
	public static void main(String[] args) throws IOException {
		// parseAll();
		List<Map<String, String>> list = null;
		String fileName = null;
		// list = parseVOD();
		// fileName = "E:\\workspace\\xml\\vod.txt";
		// writeFile(list, fileName);
		// fileName = "E:\\workspace\\xml\\category.txt";
		// list = parseCategory();
		// writeFile(list, fileName);
		// fileName = "E:\\workspace\\xml\\series.txt";
		// list = parseSeries();
		// writeFile(list, fileName);
		// fileName = "E:\\workspace\\xml\\vodcategory.txt";
		// list = parseVODCategory();
		// writeFile(list, fileName);
		// fileName = "E:\\workspace\\xml\\seriescategory.txt";
		// list = parseSeriesCategory();
		// writeFile(list, fileName);
		// sepVod();
		// fileName = "E:\\workspace\\xml\\onlyvodcategory.txt";
		// List<String> onlyVodCategoryList = getOnlyVodCategory();
		// writeFileLine(onlyVodCategoryList, fileName);
		// fileName = "E:\\workspace\\xml\\seriesvodcategory.txt";
		// List<String> seriesVodCategoryList = getSeriesVodCategory();
		// writeFileLine(seriesVodCategoryList, fileName);
		// String s = getResult();
		// fileName = "E:\\workspace\\xml\\testvod.txt";
		// writeFile(s, fileName);
		// Map<String, Category> map = getResult();
		// String dir = "E:\\workspace\\xml\\excel\\";
		// writeExcel(map, dir);
		// fileName = "E:\\xml\\xml\\result.txt";
		// writeOtherCode(fileName);
		// fileName = "E:\\xml\\xml\\result1.txt";
		// writeOtherCode1(fileName);
		// fileName = "E:\\xml\\xml\\result2.txt";
		// writeOtherCode2(fileName);
		// fileName = "E:\\xml\\xml\\result3.txt";
		// writeOtherCode2(fileName);
		// fileName = "E:\\xml\\xml\\result4.txt";
		// writeOtherCode1(fileName);
		// fileName = "E:\\xml\\xml\\result5.txt";
		// writeOtherCode1(fileName);
//		fileName = "E:\\xml\\xml\\result7.txt";
//		getVODCategory(fileName);
//		 fileName = "E:\\workspace\\xml\\vodpackage.txt";
//		 list = parseVODPackage();
//		 writeFile(list, fileName);
//		 fileName = "E:\\xml\\xml\\packageresult1.txt";
//		 writePackageCode1(fileName);
	}

	public static void writePackageCode1(String fileName) {
		List<String> a1 = readFile("E:\\xml\\xml\\a1.txt");
		List<String> vodcategory = readFile("E:\\workspace\\xml\\vodpackage.txt");
		Set<String> a2 = new HashSet<String>();
		List<String> result = new ArrayList<String>();
		for (String s : vodcategory) {
			String[] a = s.split("\\|");
			a2.add(a[1]);
		}
		System.out.println(a2.size());
		for (String s : a1) {
			if (a2.contains(s)) {
				result.add(s);
			}
		}
		writeFileLine(result, fileName);
	}

	public static void getVODCategory(String fileName) {
		List<String> a1 = readFile("E:\\xml\\xml\\result.txt");
		List<String> result = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		File dir = new File("E:\\workspace\\xml\\VODCategory");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		int c = 0;
		for (File file : files) {
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
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> nodeList = root.selectNodes("//Mappings//Mapping");
				if (nodeList != null && nodeList.size() > 0) {
					c++;
				}
				for (Node node : nodeList) {
					Map<String, String> map = new HashMap<String, String>();
					String elementID = node.valueOf("@ElementID");
					if (elementID != null) {
						if (a1.contains(elementID)&&!set.contains(elementID)) {
							set.add(elementID);
							result.add(file.getName());
							break;
						}
					}
				}
			}
		}
		System.out.println(c);
		writeFileLine(result, fileName);
	}

	public static void writeOtherCode2(String fileName) {
		List<String> a1 = readFile("E:\\xml\\xml\\result.txt");
		List<String> vodcategory = readFile("E:\\workspace\\xml\\vodcategory.txt");
		List<String> a2 = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		for (String s : vodcategory) {
			String[] a = s.split("\\|");
			a2.add(a[1]);
		}
		for (String s : a1) {
			if (a2.contains(s)) {
				result.add(s);
			}
		}
		writeFileLine(result, fileName);
	}

	public static void writeOtherCode1(String fileName) {
		List<String> a1 = readFile("E:\\xml\\xml\\a1.txt");
		List<String> vodcategory = readFile("E:\\workspace\\xml\\vodcategory.txt");
		Set<String> a2 = new HashSet<String>();
		List<String> result = new ArrayList<String>();
		for (String s : vodcategory) {
			String[] a = s.split("\\|");
			a2.add(a[1]);
		}
		System.out.println(a2.size());
		for (String s : a1) {
			if (!a2.contains(s)) {
				result.add(s);
			}
		}
		writeFileLine(result, fileName);
	}

	public static void writeOtherCode(String fileName) {
		List<String> a1 = readFile("E:\\xml\\xml\\a1.txt");
		List<String> a2 = readFile("E:\\xml\\xml\\a2.txt");
		List<String> result = new ArrayList<String>();
		for (String s : a1) {
			if (!a2.contains(s)) {
				result.add(s);
			}
		}
		writeFileLine(result, fileName);
	}

	public static void writeExcel(Map<String, Category> map, String dir) {
		try {
			int i = 0;
			for (String key : map.keySet()) {
				Category category = map.get(key);
				if (category != null && category.getParentID().equals("0")
						&& category.getTotalSize() > 0) {
					WritableWorkbook book = Workbook.createWorkbook(new File(
							dir + category.getName() + ".xls"));
					WritableSheet sheet = book.createSheet(category.getName(),
							0);
					sheet.setColumnView(3, 50);
					WritableCellFormat format = new WritableCellFormat();
					format.setVerticalAlignment(VerticalAlignment.CENTRE);
					sheet.addCell(new Label(0, 0, "二级"));
					sheet.addCell(new Label(1, 0, "三级"));
					sheet.addCell(new Label(2, 0, "四级"));
					sheet.addCell(new Label(3, 0, "名称"));
					sheet.addCell(new Label(4, 0, "序号"));
					System.out.println("1---------" + category.getName() + ","
							+ category.getId() + "," + category.getTotalSize()
							+ "," + category.getSubCategory().size() + ","
							+ category.getVods().size());
					int start1 = 1;
					for (String s1 : category.getSubCategory()) {
						Category category1 = map.get(s1);
						int size1 = category1.getTotalSize();
						if (size1 > 0) {
							sheet.mergeCells(0, start1, 0, start1 + size1 - 1);
							sheet.addCell(new Label(0, start1, category1
									.getName(), format));
							System.out.println("2---------"
									+ category1.getName() + ","
									+ category1.getId() + ","
									+ category1.getTotalSize() + ","
									+ category1.getSubCategory().size() + ","
									+ category1.getVods().size());
							int start2 = start1;
							if (category1.getVods().size() > 0) {
								Collections.sort(category1.getVods());
								for (Vod vod1 : category1.getVods()) {
									sheet.addCell(new Label(3, start2, vod1
											.getName()));
									sheet.addCell(new Label(4, start2, vod1
											.getSequence()));
									start2++;
								}
							}
							for (String s2 : category1.getSubCategory()) {
								Category category2 = map.get(s2);
								int size2 = category2.getTotalSize();
								if (size2 > 0) {
									sheet.mergeCells(1, start2, 1, start2
											+ size2 - 1);
									sheet.addCell(new Label(1, start2,
											category2.getName(), format));
									System.out.println("3---------"
											+ category2.getName() + ","
											+ category2.getId() + ","
											+ category2.getTotalSize() + ","
											+ category2.getSubCategory().size()
											+ "," + category2.getVods().size());
									int start3 = start2;
									if (category2.getVods().size() > 0) {
										Collections.sort(category2.getVods());
										for (Vod vod2 : category2.getVods()) {
											sheet.addCell(new Label(3, start3,
													vod2.getName()));
											sheet.addCell(new Label(4, start3,
													vod2.getSequence()));
											start3++;
										}
									}
									for (String s3 : category2.getSubCategory()) {
										Category category3 = map.get(s3);
										int size3 = category3.getTotalSize();
										if (size3 > 0) {
											sheet.mergeCells(2, start3, 2,
													start3 + size3 - 1);
											sheet.addCell(new Label(2, start3,
													category3.getName(), format));
											System.out.println("4---------"
													+ category3.getName()
													+ ","
													+ category3.getId()
													+ ","
													+ category3.getTotalSize()
													+ ","
													+ category3
															.getSubCategory()
															.size()
													+ ","
													+ category3.getVods()
															.size());
											int start4 = start3;
											if (category3.getVods().size() > 0) {
												Collections.sort(category3
														.getVods());
												for (Vod vod3 : category3
														.getVods()) {
													sheet.addCell(new Label(3,
															start4, vod3
																	.getName()));
													sheet.addCell(new Label(4,
															start4,
															vod3.getSequence()));
													start4++;
												}
											}
											start3 += size3;
										}
									}
									start2 += size2;
								}
							}
							start1 += size1;
						}
					}
					book.write();
					book.close();
					i++;
					// if (i > 1) {
					// break;
					// }
				}
			}

			// Label label = new Label(0, 0, "你好");
			//
			// // 将定义好的单元格添加到工作表中
			// sheet.addCell(label);
			//
			// // 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			// jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
			// sheet.addCell(number);
			// sheet.mergeCells(0, 1, 0, 10);
			// sheet.addCell(new Label(0, 1, "合并单元格", format));
			// sheet.mergeCells(0, 12, 0, 14);
			// sheet.addCell(new Label(0, 12, "sss"));
			// 写入数据并关闭文件

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, Category> getResult() {
		String fileName = null;
		fileName = "E:\\workspace\\xml\\category.txt";
		Map<String, Category> categoryMap = getCategory(fileName);
		// for (Map.Entry<String, Category> entry : categoryMap.entrySet()) {
		// System.out.println(entry);
		// }
		fileName = "E:\\workspace\\xml\\vod.txt";
		Map<String, Vod> vodMap = getVod(fileName);
		// for (String key : categoryMap.keySet()) {
		// Category category = categoryMap.get(key);
		// // if (category.getParentID().equals("0")) {
		// // System.out.println(category.getName() + "," + category.getId()
		// // + "," + category.getSubCategory().size());
		// // }
		// if (category.getSubCategory().size() == 0) {
		// if (category.getParentIDs().size() > 2) {
		// System.out.println(category);
		// }
		// }
		// }
		fileName = "E:\\workspace\\xml\\vodcategory.txt";
		List<String> vodCategoryList = readFile(fileName);
		StringBuilder sb = new StringBuilder();
		for (String s : vodCategoryList) {
			String[] line = s.split("\\|");
			Vod vod = vodMap.get(line[1]);
			Category category = categoryMap.get(line[0]);
			if (vod != null && category != null) {
				if (!category.getVods().contains(vod)) {
					addTotalSize(categoryMap, category);
					category.getVods().add(vod);
					vod.setSequence(line[2]);
				}
				// sb.append(getAllCategoryName(categoryMap, category));
				// sb.append("##");
				// sb.append(vod.getName());
				// sb.append("|");
				// sb.append(line[2]);
				// sb.append("\r\n");
				// sb.append("|");
				// sb.append(line[1]);
				// System.out.println(sb.toString());
			}
		}

		Map<String, Set<String>> seriesVodMap = new HashMap<String, Set<String>>();
		fileName = "E:\\workspace\\xml\\seriesvod.txt";
		List<String> seriesVodList = readFile(fileName);
		for (String s : seriesVodList) {
			String[] a = s.split("\\|");
			if (seriesVodMap.get(a[2]) == null) {
				Set<String> set = new HashSet<String>();
				seriesVodMap.put(a[2], set);
			}
			seriesVodMap.get(a[2]).add(a[1]);
		}

		fileName = "E:\\workspace\\xml\\seriescategory.txt";
		List<String> seriesCategoryList = readFile(fileName);
		for (String s : seriesCategoryList) {
			String[] line = s.split("\\|");
			Category category = categoryMap.get(line[0]);
			Set<String> vodSet = seriesVodMap.get(line[1]);
			if (category != null && vodSet != null && vodSet.size() > 0) {
				for (String s1 : vodSet) {
					Vod vod = vodMap.get(s1);
					if (vod != null) {
						if (!category.getVods().contains(vod)) {
							addTotalSize(categoryMap, category);
							category.getVods().add(vod);
							vod.setSequence(line[2]);
						}
					}
				}
			}
		}
		return categoryMap;
	}

	public static void addTotalSize(Map<String, Category> categoryMap,
			Category category) {
		category.addTotalSize();
		List<String> list = category.getParentIDs();
		if (list != null && list.size() > 0) {
			for (String id : list) {
				Category parentCategory = categoryMap.get(id);
				if (parentCategory != null) {
					parentCategory.addTotalSize();
				}
			}
		}
	}

	public static String getAllCategoryName(Map<String, Category> categoryMap,
			Category category) {
		StringBuilder sb = new StringBuilder();
		List<String> list = category.getParentIDs();
		if (list != null && list.size() > 0) {
			for (int i = list.size() - 1; i >= 0; i--) {
				Category parentCategory = categoryMap.get(list.get(i));
				if (parentCategory != null) {
					sb.append(parentCategory.getName());
					sb.append("|");
				}
			}
		}
		sb.append(category.getName());
		sb.append("|");
		return sb.toString();
	}

	public static Map<String, Vod> getVod(String fileName) {
		Map<String, Vod> map = new HashMap<String, Vod>();
		List<String> list = readFile(fileName);
		for (String s : list) {
			String[] line = s.split("\\|");
			Vod vod = new Vod();
			vod.setId(line[1]);
			vod.setName(line[0]);
			map.put(vod.getId(), vod);
		}
		return map;
	}

	public static Map<String, Category> getCategory(String fileName) {
		Map<String, Category> map = new HashMap<String, Category>();
		List<String> list = readFile(fileName);
		for (String s : list) {
			String[] line = s.split("\\|");
			Category category = new Category();
			category.setId(line[1]);
			category.setName(line[0]);
			category.setParentID(line[2]);
			map.put(category.getId(), category);
			for (String item : list) {
				String[] itemLine = item.split("\\|");
				if (category.getId().equals(itemLine[2])) {
					category.getSubCategory().add(itemLine[1]);
				}
			}
		}
		for (String key : map.keySet()) {
			Category category = map.get(key);
			Category parentCategory = category;
			while (parentCategory != null
					&& !parentCategory.getParentID().equals("0")) {
				category.getParentIDs().add(parentCategory.getParentID());
				parentCategory = map.get(parentCategory.getParentID());
			}
		}
		return map;
	}

	public static List<String> getSeriesVodCategory() {
		List<String> onlyVodCategoryList = new ArrayList<String>();
		Map<String, String[]> categoryMap = new HashMap<String, String[]>();
		Map<String, Set<String>> vodCategoryMap = new HashMap<String, Set<String>>();
		Map<String, String> seriesMap = new HashMap<String, String>();

		String fileName = "E:\\workspace\\xml\\category.txt";
		List<String> categoryList = readFile(fileName);
		for (String s : categoryList) {
			String[] a = s.split("\\|");
			categoryMap.put(a[1], a);
		}

		fileName = "E:\\workspace\\xml\\seriescategory.txt";
		List<String> vodCategoryList = readFile(fileName);
		for (String s : vodCategoryList) {
			String[] a = s.split("\\|");
			if (vodCategoryMap.get(a[1]) == null) {
				Set<String> set = new HashSet<String>();
				vodCategoryMap.put(a[1], set);
			}
			vodCategoryMap.get(a[1]).add(a[0]);
		}
		// for(Map.Entry<String, Set<String>> entry:vodCategoryMap.entrySet()){
		// System.out.println(entry);
		// }

		fileName = "E:\\workspace\\xml\\series.txt";
		List<String> seriesList = readFile(fileName);
		for (String s : seriesList) {
			String[] a = s.split("\\|");
			seriesMap.put(a[1], a[0]);
		}

		fileName = "E:\\workspace\\xml\\seriesvod.txt";
		List<String> vodList = readFile(fileName);
		for (String s : vodList) {
			String[] a = s.split("\\|");
			String vodID = a[1];
			String vodName = a[0];
			String seriesID = a[2];
			String seriesName = seriesMap.get(seriesID);
			StringBuilder sb = new StringBuilder();
			sb.append(vodName);
			sb.append("|");
			sb.append(vodID);
			sb.append("|");
			sb.append(seriesName);
			sb.append("|");
			sb.append(seriesID);
			sb.append("|");
			Set<String> set = vodCategoryMap.get(seriesID);
			// System.out.println(set);
			if (set != null) {
				for (String categoryID : set) {
					String itemID = categoryID;
					sb.append(itemID);
					int i = 0;
					do {
						if (categoryMap.get(itemID) != null) {
							String[] categorys = categoryMap.get(itemID);
							itemID = categorys[2];
							sb.append(":");
							sb.append(categorys[0]);
							sb.append("**");
							sb.append(itemID);
							i++;
						} else {
							break;
						}
					} while (!"0".equals(itemID));
					// if (i ==1) {
					// System.out.println(i + ":" + categoryID);
					// }
					sb.append("$$");
				}
			}
			// System.out.println(sb.toString());
			onlyVodCategoryList.add(sb.toString());
		}
		System.out.println(onlyVodCategoryList.size());
		return onlyVodCategoryList;
	}

	public static List<String> getOnlyVodCategory() {
		List<String> onlyVodCategoryList = new ArrayList<String>();
		Map<String, String[]> categoryMap = new HashMap<String, String[]>();
		Map<String, Set<String>> vodCategoryMap = new HashMap<String, Set<String>>();

		String fileName = "E:\\workspace\\xml\\category.txt";
		List<String> categoryList = readFile(fileName);
		for (String s : categoryList) {
			String[] a = s.split("\\|");
			categoryMap.put(a[1], a);
		}

		fileName = "E:\\workspace\\xml\\vodcategory.txt";
		List<String> vodCategoryList = readFile(fileName);
		for (String s : vodCategoryList) {
			String[] a = s.split("\\|");
			if (vodCategoryMap.get(a[1]) == null) {
				Set<String> set = new HashSet<String>();
				vodCategoryMap.put(a[1], set);
			}
			vodCategoryMap.get(a[1]).add(a[0]);
		}
		// for(Map.Entry<String, Set<String>> entry:vodCategoryMap.entrySet()){
		// System.out.println(entry);
		// }

		fileName = "E:\\workspace\\xml\\onlyvod.txt";
		List<String> vodList = readFile(fileName);
		for (String s : vodList) {
			String[] a = s.split("\\|");
			String vodID = a[1];
			String vodName = a[0];
			StringBuilder sb = new StringBuilder();
			sb.append(vodName);
			sb.append("|");
			sb.append(vodID);
			sb.append("|");
			Set<String> set = vodCategoryMap.get(vodID);
			// System.out.println(set);
			if (set != null) {
				for (String categoryID : set) {
					String itemID = categoryID;
					sb.append(itemID);
					int i = 0;
					do {
						if (categoryMap.get(itemID) != null) {
							String[] categorys = categoryMap.get(itemID);
							itemID = categorys[2];
							sb.append(":");
							sb.append(categorys[0]);
							sb.append("**");
							sb.append(itemID);
							i++;
						} else {
							break;
						}
					} while (!"0".equals(itemID));
					// if (i ==4) {
					// System.out.println(i + ":" + categoryID);
					// }
					sb.append("$$");
				}
			}
			// System.out.println(sb.toString());
			onlyVodCategoryList.add(sb.toString());
		}
		return onlyVodCategoryList;
	}

	public static void sepVod() throws IOException {
		List<Map<String, String>> vodList = parseVOD();
		List<Map<String, String>> seriesList = parseSeries();
		List<Map<String, String>> onlyVodList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> seriesVodList = new ArrayList<Map<String, String>>();
		Map<String, String> seriesMap = new HashMap<String, String>();
		// Set<String> set = new HashSet<String>();
		String seriesID = "";
		for (Map<String, String> itemMap : seriesList) {
			String vod = itemMap.get("VODID");
			if (itemMap.get("SeriesID") != null
					&& !"null".equals(itemMap.get("SeriesID"))) {
				seriesID = itemMap.get("SeriesID");
			}
			if (vod != null && vod.length() > 10) {
				// set.add(vod);
				seriesMap.put(vod, seriesID);
				System.out.println("seriesID:" + seriesID);
			}
		}
		// System.out.println("set size:" + set.size());
		System.out.println("seriesMap size:" + seriesMap.size());
		for (Map<String, String> map : vodList) {
			Map<String, String> vodMap = new HashMap<String, String>();
			String id = map.get("ID");
			vodMap.put("ID", id);
			vodMap.put("Name", map.get("Name"));
			seriesID = seriesMap.get(id);
			if (seriesID != null) {
				vodMap.put("ParentCode", seriesID);
				seriesVodList.add(vodMap);
			} else {
				onlyVodList.add(vodMap);
			}
		}
		String fileName = "E:\\workspace\\xml\\onlyvod.txt";
		writeFile(onlyVodList, fileName);
		fileName = "E:\\workspace\\xml\\seriesvod.txt";
		writeFile(seriesVodList, fileName);
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

	public static void writeFile(String s, String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		fw.write(s);
		fw.close();
	}

	public static void writeFileLine(List<String> list, String fileName) {
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(fileName));
			for (String s : list) {
				out.write((s + "\r\n").getBytes());
			}
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

	public static void writeFile(List<Map<String, String>> list, String fileName)
			throws IOException {
		FileWriter fw = new FileWriter(fileName);
		for (Map<String, String> map : list) {
			StringBuilder sb = new StringBuilder();
			if (map.get("Name") != null) {
				sb.append(map.get("Name"));
			} else if (map.get("ParentID") != null) {
				sb.append(map.get("ParentID"));
			}
			if (map.get("ID") != null) {
				sb.append("|");
				sb.append(map.get("ID"));
			}
			if (map.get("Duration") != null) {
				sb.append("|");
				sb.append(map.get("Duration"));
			}
			if (map.get("ParentCode") != null) {
				sb.append("|");
				sb.append(map.get("ParentCode"));
			}
			if (map.get("ElementID") != null) {
				sb.append("|");
				sb.append(map.get("ElementID"));
			}
			if (map.get("Sequence") != null) {
				sb.append("|");
				sb.append(map.get("Sequence"));
			}
			if (map.get("SeriesID") != null) {
				sb.append("|");
				sb.append(map.get("SeriesID"));
				sb.append("|");
			}
			if (map.get("VODID") != null) {
				sb.append("~");
				sb.append(map.get("VODID"));
			}
			if (map.get("VODID") == null && map.get("SeriesID") == null) {
				sb.append("\r\n");
			}
			fw.write(sb.toString());
		}
		fw.close();
	}

	public static List<Map<String, String>> parseCategory() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		File dir = new File("E:\\workspace\\xml\\Category");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		for (File file : files) {
			i++;
			// if (i > 10)
			// break;
			if (i % 1000 == 0) {
				System.out.println(i);
			}
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> nodeList = root.selectNodes("//Objects//Object");
				for (Node node : nodeList) {
					String elementType = node.valueOf("@ElementType");
					if ("Category".equals(elementType)) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("ID", node.valueOf("@ID"));
						map.put("ParentCode", node.valueOf("@ParentCode"));
						// System.out.println(elementType + ","
						// + node.valueOf("@ID"));
						@SuppressWarnings("unchecked")
						List<Node> props = node.selectNodes("Property");
						for (Node prop : props) {
							String name = prop.valueOf("@Name");
							if ("Name".equals(name)) {
								// System.out.println(prop.getText());
								map.put("Name", prop.getText());
							}
						}
						list.add(map);
					}
				}
			}
		}
		return list;
	}

	public static List<Map<String, String>> parseVOD() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		File dir = new File("E:\\workspace\\xml\\VOD");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		for (File file : files) {
			i++;
			// if (i > 10)
			// break;
			if (i % 1000 == 0) {
				System.out.println(i);
			}
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> nodeList = root.selectNodes("//Objects//Object");
				for (Node node : nodeList) {
					String elementType = node.valueOf("@ElementType");
					if ("Program".equals(elementType)) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("ID", node.valueOf("@ID"));
						// System.out.println(elementType + ","
						// + node.valueOf("@ID"));
						@SuppressWarnings("unchecked")
						List<Node> props = node.selectNodes("Property");
						for (Node prop : props) {
							String name = prop.valueOf("@Name");
							if ("Name".equals(name)) {
								// System.out.println(prop.getText());
								map.put("Name", prop.getText());
							} else if ("Duration".equals(name)) {
								// System.out.println(prop.getText());
								map.put("Duration", prop.getText());
							}
						}
						list.add(map);
					}
				}
			}
		}
		return list;
	}

	public static List<Map<String, String>> parseSeries() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		File dir = new File("E:\\workspace\\xml\\Series");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		for (File file : files) {
			i++;
			// if (i > 10)
			// break;
			if (i % 1000 == 0) {
				System.out.println(i);
			}
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> nodeList = root.selectNodes("//Objects//Object");
				for (Node node : nodeList) {
					String elementType = node.valueOf("@ElementType");
					if ("Series".equals(elementType)) {
						Map<String, String> map = new HashMap<String, String>();
						list.add(map);
						map.put("SeriesID", node.valueOf("@ID"));
						// System.out.println(elementType + ","
						// + node.valueOf("@ID"));
						@SuppressWarnings("unchecked")
						List<Node> props = node.selectNodes("Property");
						for (Node prop : props) {
							String name = prop.valueOf("@Name");
							if ("Name".equals(name)) {
								// System.out.println(prop.getText());
								map.put("Name", prop.getText());
							}
						}
					}
				}

				@SuppressWarnings("unchecked")
				List<Node> mapList = root.selectNodes("//Mappings//Mapping");
				for (Node node : mapList) {
					String elementType = node.valueOf("@ElementType");
					if ("Program".equals(elementType)) {
						if (node.valueOf("@ElementID") != null) {
							Map<String, String> map = new HashMap<String, String>();
							list.add(map);
							map.put("VODID", node.valueOf("@ElementID"));
						}
					}
				}
				Map<String, String> map = new HashMap<String, String>();
				list.add(map);
				map.put("VODID", "\r\n");
			}
		}
		return list;
	}

	public static List<Map<String, String>> parseVODPackage() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		 File dir = new File("E:\\workspace\\xml\\VODPackage");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		int c = 0;
		for (File file : files) {
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
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> nodeList = root.selectNodes("//Mappings//Mapping");
				if (nodeList != null && nodeList.size() > 0) {
					c++;
				}
				for (Node node : nodeList) {
					Map<String, String> map = new HashMap<String, String>();
					if (node.valueOf("@ParentID") != null) {
						map.put("ParentID", node.valueOf("@ParentID"));
					}
					if (node.valueOf("@ElementID") != null) {
						map.put("ElementID", node.valueOf("@ElementID"));
					}
					@SuppressWarnings("unchecked")
					List<Node> props = node.selectNodes("Property");
					for (Node prop : props) {
						String name = prop.valueOf("@Name");
						if ("Sequence".equals(name)) {
							// System.out.println(prop.getText());
							map.put("Sequence", prop.getText());
						}
					}
					if (map.size() > 0) {
						list.add(map);
					}
				}
			}
		}
		System.out.println(c);
		return list;
	}

	public static List<Map<String, String>> parseVODCategory() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		// File dir = new File("E:\\workspace\\xml\\VODCategory");
		File dir = new File("E:\\xml\\xml\\xml3");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		int c = 0;
		for (File file : files) {
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
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> nodeList = root.selectNodes("//Mappings//Mapping");
				if (nodeList != null && nodeList.size() > 0) {
					c++;
				}
				for (Node node : nodeList) {
					Map<String, String> map = new HashMap<String, String>();
					if (node.valueOf("@ParentID") != null) {
						map.put("ParentID", node.valueOf("@ParentID"));
					}
					if (node.valueOf("@ElementID") != null) {
						map.put("ElementID", node.valueOf("@ElementID"));
					}
					@SuppressWarnings("unchecked")
					List<Node> props = node.selectNodes("Property");
					for (Node prop : props) {
						String name = prop.valueOf("@Name");
						if ("Sequence".equals(name)) {
							// System.out.println(prop.getText());
							map.put("Sequence", prop.getText());
						}
					}
					if (map.size() > 0) {
						list.add(map);
					}
				}
			}
		}
		System.out.println(c);
		return list;
	}

	public static List<Map<String, String>> parseSeriesCategory() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SAXReader reader = new SAXReader();
		Document doc = null;
		File dir = new File("E:\\workspace\\xml\\SeriesCategory");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		int i = 0;
		for (File file : files) {
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
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> nodeList = root.selectNodes("//Mappings//Mapping");
				for (Node node : nodeList) {
					Map<String, String> map = new HashMap<String, String>();
					if (node.valueOf("@ParentID") != null) {
						map.put("ParentID", node.valueOf("@ParentID"));
					}
					if (node.valueOf("@ElementID") != null) {
						map.put("ElementID", node.valueOf("@ElementID"));
					}
					@SuppressWarnings("unchecked")
					List<Node> props = node.selectNodes("Property");
					for (Node prop : props) {
						String name = prop.valueOf("@Name");
						if ("Sequence".equals(name)) {
							// System.out.println(prop.getText());
							map.put("Sequence", prop.getText());
						}
					}
					if (map.size() > 0) {
						list.add(map);
					}
				}
			}
		}
		return list;
	}

	public static void parseAll() {
		SAXReader reader = new SAXReader();
		Document doc = null;
		File dir = new File("E:\\workspace\\xml");
		File[] files = dir.listFiles();
		System.out.println("file numbers:" + files.length);
		for (File file : files) {
			try {
				doc = reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			if (doc != null) {
				Element root = doc.getRootElement();
				@SuppressWarnings("unchecked")
				List<Node> list = root.selectNodes("//template");
				for (Node node : list) {
					System.out.println(node.getName() + ","
							+ node.valueOf("@desc"));
				}
			}
		}
	}
}
