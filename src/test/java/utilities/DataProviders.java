package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//Dataprovider 1
	
	@DataProvider(name = "LoginData")
	public String[][] getLoginData() throws Exception{
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		ExcelUtils util = new ExcelUtils(path);
		
		int totalRows = util.getRowCount("Sheet1");
		int totalCols = util.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols];
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalCols;j++) {
				loginData[i-1][j] = util.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
		
	}
	
}
