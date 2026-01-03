package utilities;
import org.testng.annotations.DataProvider;
import java.io.IOException;

public class DataProviders {

	// ================= DataProvider 1 =================
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        // Excel file path
    	String path = System.getProperty("user.dir")
    	        + "/testData/OpenCartLoginTestData.xlsx";

        // Create ExcelUtility object
        ExcelUtils xlutil = new ExcelUtils(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        // Create 2D array
        String logindata[][] = new String[totalrows][totalcols];

        // Read data from Excel
        for (int i = 1; i <= totalrows; i++) {   // rows
            for (int j = 0; j < totalcols; j++) { // columns
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }

        return logindata; // return 2D array
    }
 // ================= DataProvider 2 =================
 // ================= DataProvider 3 =================
 // ================= DataProvider 4 =================
}


