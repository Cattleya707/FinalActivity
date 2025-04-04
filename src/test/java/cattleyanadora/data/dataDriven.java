package cattleyanadora.data;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
		
		public ArrayList<ArrayList<String>> getData() throws IOException {
	        ArrayList<ArrayList<String>> data = new ArrayList<>();
	        String filePath = System.getProperty("user.dir") + "/SaucedemoTestData.xlsx";
	        File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);

	        XSSFWorkbook workbook = new XSSFWorkbook(fis);

	        XSSFSheet sheet = workbook.getSheetAt(0); // Get first sheet
	        Iterator<Row> rowIterator = sheet.iterator();

	        // Skip header row
	        if (rowIterator.hasNext()) rowIterator.next();

	        while (rowIterator.hasNext()) {
	            Row row = rowIterator.next();
	            ArrayList<String> rowData = new ArrayList<>();

	            for (Cell cell : row) {
	                switch (cell.getCellType()) {
	                    case STRING:
	                        rowData.add(cell.getStringCellValue());
	                        break;
	                    case NUMERIC:
	                        rowData.add(String.valueOf(cell.getNumericCellValue()));
	                        break;
	                    case BOOLEAN:
	                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
	                        break;
	                    default:
	                        rowData.add("");
	                }
	            }
	            data.add(rowData);
	        }

	        workbook.close();
	        fis.close();
	        return data;
	    }
    public ArrayList<ArrayList<String>> getUserData() throws IOException {
    	ArrayList<ArrayList<String>> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream("C:\\Users\\Nadora Family\\OneDrive\\Documents\\SaucedemoTestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0); // Get first sheet
        Iterator<Row> rowIterator = sheet.iterator();

        // Skip header row
        if (rowIterator.hasNext()) rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            ArrayList<String> rowData = new ArrayList<>();
            
            // Assuming columns: First Name (0), Last Name (1), Zip Code (2)
            for (int i = 0; i < 3; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.add(String.valueOf((int) cell.getNumericCellValue())); // Convert to int to avoid decimal
                        break;
                    case BOOLEAN:
                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    default:
                        rowData.add("");
                }
            }
            data.add(rowData);
        }
        
        workbook.close();
        fis.close();
        return data;
    }
		
	public List<String> getAscendingProductsFromExcel() throws IOException {
		    List<String> productNames = new ArrayList<>();
		    FileInputStream fis = new FileInputStream("C:\\Users\\Nadora Family\\OneDrive\\Documents\\SaucedemoTestData.xlsx");
		    XSSFWorkbook workbook = new XSSFWorkbook(fis);
		    XSSFSheet sheet = workbook.getSheetAt(1); 

		    int firstRowIndex = 1; 
		    int lastRowIndex = sheet.getLastRowNum();

		    for (int i = firstRowIndex; i <= lastRowIndex; i++) {
		        Row row = sheet.getRow(i);
		        if (row != null) {
		            Cell cell = row.getCell(0); 
		            if (cell != null && cell.getCellType() == CellType.STRING) {
		                productNames.add(cell.getStringCellValue());
		            }
		        }
		    }

		    workbook.close();
		    fis.close();
		    return productNames;
	}

	
	public List<String> getDescendingProductsFromExcel() throws IOException {
	    List<String> productNames = new ArrayList<>();
	    FileInputStream fis = new FileInputStream("C:\\Users\\Nadora Family\\OneDrive\\Documents\\SaucedemoTestData.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    XSSFSheet sheet = workbook.getSheetAt(1); 

	    int firstRowIndex = 1; 
	    int lastRowIndex = sheet.getLastRowNum();

	    for (int i = firstRowIndex; i <= lastRowIndex; i++) {
	        Row row = sheet.getRow(i);
	        if (row != null) {
	            Cell cell = row.getCell(1); 
	            if (cell != null && cell.getCellType() == CellType.STRING) {
	                productNames.add(cell.getStringCellValue());
	            }
	        }
	    }

	    workbook.close();
	    fis.close();
	    return productNames;
}
	
	public List<String> getLowtoHighPriceFromExcel() throws IOException {
	    List<String> prices = new ArrayList<>();
	    FileInputStream fis = new FileInputStream("C:\\Users\\Nadora Family\\OneDrive\\Documents\\SaucedemoTestData.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);

	    XSSFSheet sheet = workbook.getSheetAt(1); // Get second sheet (Product List)

	    for (Row row : sheet) {
	        Cell cell = row.getCell(2); 
	        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
	            prices.add(String.format("%.2f", cell.getNumericCellValue())); // Convert to String with 2 decimal places
	        }
	    }

	    workbook.close();
	    return prices;
	}
	
	public List<String> getHightoLowPriceFromExcel() throws IOException {
	    List<String> prices = new ArrayList<>();
	    FileInputStream fis = new FileInputStream("C:\\Users\\Nadora Family\\OneDrive\\Documents\\SaucedemoTestData.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);

	    XSSFSheet sheet = workbook.getSheetAt(1); // Get second sheet (Product List)

	    for (Row row : sheet) {
	        Cell cell = row.getCell(3); // Assuming prices are in column index 2
	        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
	            prices.add(String.format("%.2f", cell.getNumericCellValue())); // Convert to String with 2 decimal places
	        }
	    }

	    workbook.close();
	    return prices;
	}

	public ArrayList<ArrayList<String>> getFilteredData(String firstName, String lastName, String zipCode) throws IOException {
	    ArrayList<ArrayList<String>> filteredData = new ArrayList<>();
	    FileInputStream fis = new FileInputStream("C:\\Users\\Nadora Family\\OneDrive\\Documents\\SaucedemoTestData.xlsx");
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    XSSFSheet sheet = workbook.getSheetAt(0); // Get first sheet
	    Iterator<Row> rowIterator = sheet.iterator();

	    // Skip header row
	    if (rowIterator.hasNext()) rowIterator.next();

	    while (rowIterator.hasNext()) {
	        Row row = rowIterator.next();
	        ArrayList<String> rowData = new ArrayList<>();

	        // Read specific columns (First Name = 2, Last Name = 3, Zip Code = 4)
	        String fName = getCellValue(row, 2);
	        String lName = getCellValue(row, 3);
	        String zip = getCellValue(row, 4);

	        // Check if row matches the search criteria
	        if (fName.equals(firstName) && lName.equals(lastName) && zip.equals(zipCode)) {
	            rowData.add(fName);
	            rowData.add(lName);
	            rowData.add(zip);
	            filteredData.add(rowData);
	        }
	    }

	    workbook.close();
	    fis.close();
	    return filteredData;
	}

	// Helper method to get cell value as a String
	private String getCellValue(Row row, int columnIndex) {
	    Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	    if (cell == null) return ""; // Ignore missing cells
	    switch (cell.getCellType()) {
	        case STRING:
	            return cell.getStringCellValue().trim();
	        case NUMERIC:
	            return String.valueOf((int) cell.getNumericCellValue()); // Convert to int if numeric (e.g., Zip Code)
	        default:
	            return "";
	    }
	}

		
		

	public static void main(String[] args) throws IOException {

	}
}

