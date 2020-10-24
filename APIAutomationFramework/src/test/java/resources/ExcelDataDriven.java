package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {

	public static void main(String args[]) throws IOException
	{
		getTestDataUsingExcel("Sheet1","AddBook");
	}
	
	public static ArrayList<String> getTestDataUsingExcel(String sheetName, String testName) throws IOException {
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\AnilY\\git\\APIFramework\\APIAutomationFramework\\src\\test\\java\\resources\\testDataExcelRestAssured.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		ArrayList<String> list=new ArrayList<String>();
		int totalSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < totalSheets; i++) {
			
				if(workbook.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetName))
				{
					System.out.println("Anil");
					XSSFSheet sheet=workbook.getSheetAt(i);
					Iterator<Row> row=sheet.iterator();
					Row firstRow=row.next();
					Iterator<Cell> cell=firstRow.iterator();
					int k=0,column=0;
					while(cell.hasNext())
					{
						Cell testNameCell=cell.next();
						if(testNameCell.getStringCellValue().equalsIgnoreCase("testcases"))
						{
							column=k;
						}
						k++;
						
					}
					System.out.println(column);
					
					while(row.hasNext())
					{
						Row r=row.next();
						if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testName))
						{
							Iterator<Cell> c=r.iterator();
							while(c.hasNext())
							{
								//System.out.println(c.next().getStringCellValue());

								list.add(c.next().getStringCellValue())	;
							}
						}
						
					}
					
				}
			}
		return list;
			
		}
	}
