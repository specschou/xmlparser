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
			// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
			WritableSheet sheet = book.createSheet("��һҳ", 0);
			sheet.setColumnView(0, 50);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 20);
			sheet.setColumnView(4, 20);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			sheet.addCell(new Label(0, 0, "��Ŀ����",wcf));
			sheet.addCell(new Label(1, 0, "�ۿ�����"));
			sheet.addCell(new Label(2, 0, "�ۿ��û���"));
			sheet.addCell(new Label(3, 0, "��ʱ��(��λ:Сʱ)"));
			sheet.addCell(new Label(4, 0, "ƽʱʱ��(��λ:����)"));
//			// ��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
//			// �Լ���Ԫ������Ϊtest
//			Label label = new Label(0, 0, "���");
//
//			// ������õĵ�Ԫ����ӵ���������
//			sheet.addCell(label);
//
//			// ����һ���������ֵĵ�Ԫ�� ����ʹ��Number��������·�����������﷨���� ��Ԫ��λ���ǵڶ��У���һ�У�ֵΪ789.123
//			WritableCellFormat format = new WritableCellFormat();
//			format.setVerticalAlignment(VerticalAlignment.CENTRE);
//			jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
//			sheet.addCell(number);
//			sheet.mergeCells(0, 1, 0, 10);
//			sheet.addCell(new Label(0, 1, "�ϲ���Ԫ��", format));
//			sheet.mergeCells(0, 12, 0, 14);
//			sheet.addCell(new Label(0, 12, "sss"));
			// д�����ݲ��ر��ļ�
			book.write();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
