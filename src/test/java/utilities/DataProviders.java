package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		
		System.out.println("Current user directory is :"+System.getProperty("user.dir)".toString()));
		String path = System.getProperty("user.dir")+".\\testData\\Opencart_LoginData.xlsx";
		System.out.println("PATH IS"+path);
		
		ExcelUtility xlutil = new ExcelUtility(path); // CREATING AN OBJECT OF ExcelUtility
		int totalrow=-1;
		int totalcols=-1;
		try {
			totalrow=xlutil.getRowCount("Sheet1");
			totalcols=xlutil.getCellCount("Sheet1",1);
			
		}
		catch(Exception e) {
			System.out.println("Error messagw is: "+e.getLocalizedMessage());
		}
		
		String logindata[][]=new String[totalrow][totalcols]; // CREATING TWO DIMENSION ARRAY WHICH CAN STORE EXCEL DATA
		
		for(int r=1;r<=totalrow;r++)
		{
			for(int c=0;c<totalcols;c++)
			{
				logindata[r-1][c]=xlutil.getCellData("Sheet1", r, c);
			}
		}
		return logindata; // RETURNING TWO DIMENSION ARRAY
				
	}

}
