package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelUtility {

	@DataProvider
	public Object[][] providerMethod1() throws IOException {

		//	String fileName = "C:/Users/NITHA/Desktop/Task.xlsx";
		File fileName=new File("C:\\Users\\User\\Desktop\\rest\\restfw\\src\\test\\resources\\testdata\\userRestapi.xlsx");
		// Create the input stream from the xlsx/xls file
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);

		int rows=sheet.getPhysicalNumberOfRows();
		int cols=sheet.getRow(0).getLastCellNum();

		Object data[][]=new Object[rows-1][cols];

		for(int i=1;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				try {
					DataFormatter df=new DataFormatter();
					data[i-1][j]=df.formatCellValue(sheet.getRow(i).getCell(j));
				}catch(Exception e) {
					data[i-1][j]="";
				}
			}
		}return data;		


	}

	//@Test(dataProvider="providerMethod1")
	public void test1(String id,String username,String firstname) {

		System.out.println("id "+Integer.parseInt(id));
		System.out.println("username "+username);
		System.out.println("firstname "+firstname);


		System.out.println("========done=====");

	}

	@DataProvider
	public Object[] providerMethod2() throws IOException {

		//	String fileName = "C:/Users/NITHA/Desktop/Task.xlsx";
		File fileName=new File("C:\\Users\\User\\Desktop\\rest\\restfw\\src\\test\\resources\\testdata\\userRestapi.xlsx");
		// Create the input stream from the xlsx/xls file
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);

		int rows=sheet.getPhysicalNumberOfRows();
		String data[]=new String[rows-1];
		for(int i=1;i<rows;i++) {
			DataFormatter df=new DataFormatter();
			data[i-1]=df.formatCellValue(sheet.getRow(i).getCell(1));
		}
		workbook.close();
		fis.close();
		return data;		


	}


}
