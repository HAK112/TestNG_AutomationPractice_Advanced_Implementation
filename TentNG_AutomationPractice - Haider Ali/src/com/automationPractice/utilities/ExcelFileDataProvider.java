package com.automationPractice.utilities;

import org.testng.annotations.DataProvider;

public class ExcelFileDataProvider {
		
	@DataProvider(name="TestData")
	public static Object[][] getData() {
		String excelPath = "resource//testData//testData.xlsx";
		Object[][] data= testData(excelPath,"Login");
		return data;
	}
	
	
	public static Object[][] testData(String excelPath, String sheetName) {
		
		@SuppressWarnings("unused")
		ExcelFileUtils excel = new ExcelFileUtils(excelPath, sheetName);
		
		int rowCount = ExcelFileUtils.getRowCount();
		int colCount = ExcelFileUtils.getColCount();
		
		Object data[][] = new Object[rowCount-1][colCount];
		
		for(int row = 1; row<rowCount;row++) {
			for(int col = 0; col<colCount;col++) {
				String cellData = ExcelFileUtils.getCellDataString(row, col);
				data[row-1][col] = cellData;
			}
		}
		return data;
	}
}
