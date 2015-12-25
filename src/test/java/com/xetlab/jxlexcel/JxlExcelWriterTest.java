package com.xetlab.jxlexcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.xetlab.jxlexcel.conf.DataRow;
import com.xetlab.jxlexcel.conf.ExcelTemplate;
import com.xetlab.jxlexcel.conf.TitleRow;

public class JxlExcelWriterTest extends Assert {

	@Test
	public void testSimpleExportData() throws Exception {
		String[] titles = createTestTitles("标题", 10);
		int colSize = titles.length;
		List<Object[]> datas = createTestDatas("数据", 20, colSize);
		File tmp = createTmpFile("testSimpleExportData.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		ExcelTemplate excelTemplate = new ExcelTemplate();
		excelTemplate.addTitleRow(new TitleRow(titles));
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		jxlExcelWriter.writeArrays(datas);
	}

	@Test
	public void testSimpleExportDataOut() throws Exception {
		String[] titles = createTestTitles("标题", 20);
		List<Object[]> datas = createTestDatas("数据", 20, titles.length);
		File tmp = createTmpFile("testSimpleExportDataOut.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		ExcelTemplate excelTemplate = new ExcelTemplate();
		excelTemplate.addTitleRow(new TitleRow(titles));
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		jxlExcelWriter.writeArrays(datas);
	}

	@Test
	public void testMassiveExportData() throws Exception {
		String[] titles = createTestTitles("标题", 10);
		int colSize = titles.length;
		List<Object[]> datas = createTestDatas("数据", 70000, colSize);
		File tmp = createTmpFile("testMassiveExportData.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		ExcelTemplate excelTemplate = new ExcelTemplate();
		excelTemplate.addTitleRow(new TitleRow(titles));
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		jxlExcelWriter.writeArrays(datas);
	}

	@Test
	public void testAutoResize() throws Exception {
		String[] titles = createTestTitles("标题", 10);
		int colSize = titles.length;
		List<Object[]> datas = createTestDatas("数据数据12431432", 20, colSize);
		File tmp = createTmpFile("testAutoAdaptCol.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		ExcelTemplate excelTemplate = new ExcelTemplate();
		excelTemplate.addTitleRow(new TitleRow(titles));
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		jxlExcelWriter.writeArrays(datas);
	}

	@Test
	public void testExportPojoBeans() throws Exception {
		List<Pojo> pojos = new ArrayList<Pojo>();
		for (int i = 0; i < 20; i++) {
			Pojo pojo = new Pojo();
			pojo.setProperty1("property1.value" + i);
			pojo.setProperty2(i);
			pojo.setProperty3(new Date());
			pojo.setProperty4(new Long(i));
			pojos.add(pojo);
		}
		ExcelTemplate excelTemplate = new ExcelTemplate();
		TitleRow excelTitleRow = new TitleRow();
		excelTitleRow.addCol("test1", 2);
		excelTitleRow.addCol("test2", 2);
		excelTemplate.addTitleRow(excelTitleRow);

		excelTitleRow = new TitleRow();
		excelTitleRow.addCol("我是属性1");
		excelTitleRow.addCol("我是属性2");
		excelTitleRow.addCol("我是属性3");
		excelTitleRow.addCol("我是属性4");

		excelTemplate.addTitleRow(excelTitleRow);
		DataRow dataConfig = new DataRow();
		dataConfig.addProperty(new String[] { "property1", "property2",
				"property3", "property4" });
		excelTemplate.setDataRow(dataConfig);

		File tmp = createTmpFile("testExportPojoBeans.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		jxlExcelWriter.writeBeans(pojos);
	}

	@Test
	public void testExportMaps() throws Exception {
		List<Map<String, Object>> pojos = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 20; i++) {
			Map<String, Object> pojo = new HashMap<String, Object>();
			pojo.put("property1", "property1.value" + i);
			pojo.put("property2", i);
			pojo.put("property3", new Date());
			pojo.put("property4", new Long(i));
			pojos.add(pojo);
		}
		ExcelTemplate excelTemplate = new ExcelTemplate();
		TitleRow excelTitleRow = new TitleRow();
		excelTitleRow.addCol("test1", 2);
		excelTitleRow.addCol("test2", 2);
		excelTemplate.addTitleRow(excelTitleRow);

		excelTitleRow = new TitleRow();
		excelTitleRow.addCol("我是属性1");
		excelTitleRow.addCol("我是属性2");
		excelTitleRow.addCol("我是属性3");
		excelTitleRow.addCol("我是属性4");

		excelTemplate.addTitleRow(excelTitleRow);
		DataRow dataConfig = new DataRow();
		dataConfig.addProperty(new String[] { "property1", "property2",
				"property3", "property4" });
		excelTemplate.setDataRow(dataConfig);
		File tmp = createTmpFile("testExportMaps.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		jxlExcelWriter.writeMaps(pojos);
	}

	@Test
	public void testTitleLenNotEqualDataLen() throws Exception {
		ExcelTemplate excelTemplate = new ExcelTemplate();
		TitleRow excelTitleRow = new TitleRow();
		excelTitleRow.addCol("test1", 3);
		excelTitleRow.addCol("test2", 2);
		excelTemplate.addTitleRow(excelTitleRow);

		excelTitleRow = new TitleRow();
		excelTitleRow.addCol("我是属性1");
		excelTitleRow.addCol("我是属性2");
		excelTitleRow.addCol("我是属性3");
		excelTitleRow.addCol("我是属性4");
		excelTitleRow.addCol("我是属性5");
		excelTitleRow.addCol("我是属性6");

		excelTemplate.addTitleRow(excelTitleRow);
		
		DataRow dataConfig = new DataRow();
		dataConfig.addProperty(new String[] { "property1", "property2",
				"property3", "property4" });
		excelTemplate.setDataRow(dataConfig);

		File tmp = createTmpFile("testTitleLenNotEqualDataLenTemplate.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		try {
			jxlExcelWriter.writeTemplate();
			fail("标题与数据列不一致，应该有异常");
		} catch (Exception e) {
			assertTrue(e instanceof JxlExcelException);
		}
	}
	
	@Test
	public void testTitleLenNotEqual() throws Exception {
		ExcelTemplate excelTemplate = new ExcelTemplate();
		TitleRow excelTitleRow = new TitleRow();
		excelTitleRow.addCol("test1", 3);
		excelTitleRow.addCol("test2", 2);
		excelTemplate.addTitleRow(excelTitleRow);

		excelTitleRow = new TitleRow();
		excelTitleRow.addCol("我是属性1");
		excelTitleRow.addCol("我是属性2");
		excelTitleRow.addCol("我是属性3");
		excelTitleRow.addCol("我是属性4");
		excelTitleRow.addCol("我是属性5");
		excelTitleRow.addCol("我是属性5");

		excelTemplate.addTitleRow(excelTitleRow);

		File tmp = createTmpFile("testTitleLenNotEqual.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		try {
			jxlExcelWriter.writeTemplate();
			fail("标题不一致，应该有异常");
		} catch (Exception e) {
			assertTrue(e instanceof JxlExcelException);
		}
	}

	@Test
	public void testWriteTemplate() throws Exception {
		ExcelTemplate excelTemplate = new ExcelTemplate();
		TitleRow excelTitleRow = new TitleRow();
		excelTitleRow.addCol("test1", 2);
		excelTitleRow.addCol("test2", 2);
		excelTemplate.addTitleRow(excelTitleRow);

		excelTitleRow = new TitleRow();
		excelTitleRow.addCol("我是属性1");
		excelTitleRow.addCol("我是属性2");
		excelTitleRow.addCol("我是属性3");
		excelTitleRow.addCol("我是属性4");

		excelTemplate.addTitleRow(excelTitleRow);
		File tmp = createTmpFile("testWriteTemplate.xls");
		JxlExcelWriter jxlExcelWriter = new JxlExcelWriter(tmp);
		jxlExcelWriter.setExcelTemplate(excelTemplate);
		jxlExcelWriter.writeTemplate();
	}

	private File createTmpFile(String fileName) throws IOException {
		File tmp = new File(fileName);
		if (tmp.exists()) {
			tmp.delete();
		}
		tmp.createNewFile();
		return tmp;
	}

	private List<Object[]> createTestDatas(String dataPrefix, int dataSize,
			int colSize) {
		List<Object[]> datas = new ArrayList<Object[]>();
		for (int i = 0; i < dataSize; i++) {
			Object[] rowData = new Object[colSize];
			datas.add(rowData);
			for (int j = 0; j < colSize; j++) {
				rowData[j] = dataPrefix + (i + 1) + (j + 1);
			}
		}
		return datas;
	}

	private String[] createTestTitles(String titlePrefix, int colSize) {
		String[] titles = new String[colSize];
		for (int i = 0; i < titles.length; i++) {
			titles[i] = titlePrefix + (i + 1);
		}
		return titles;
	}
}
