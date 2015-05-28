package xmlparser;

import java.io.File;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TestExcel {
	public static void main(String[] args) {
		String fileName = null;
		fileName = "E:\\xmltest\\test.xls";
		createExcel(fileName);
	}

	public static void createExcel(String fileName) {
		try {
			WritableWorkbook book = Workbook.createWorkbook(new File(fileName));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			sheet.setColumnView(0, 50);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			sheet.addCell(new Label(0, 0, "节目名称",wcf));
			sheet.addCell(new Label(1, 0, "观看次数"));
			sheet.addCell(new Label(2, 0, "观看用户数"));
			sheet.addCell(new Label(3, 0, "总时长(单位:小时)"));
			sheet.addCell(new Label(4, 0, "平时时长(单位:分钟)"));
//			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
//			// 以及单元格内容为test
//			Label label = new Label(0, 0, "你好");
//
//			// 将定义好的单元格添加到工作表中
//			sheet.addCell(label);
//
//			// 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
//			WritableCellFormat format = new WritableCellFormat();
//			format.setVerticalAlignment(VerticalAlignment.CENTRE);
//			jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
//			sheet.addCell(number);
//			sheet.mergeCells(0, 1, 0, 10);
//			sheet.addCell(new Label(0, 1, "合并单元格", format));
//			sheet.mergeCells(0, 12, 0, 14);
//			sheet.addCell(new Label(0, 12, "sss"));
			// 写入数据并关闭文件
			book.write();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
