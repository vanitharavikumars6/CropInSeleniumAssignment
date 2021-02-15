package cropincodingassignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HubSpotPage extends SeleniumHelper {
	
	private final WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	public static String filePath = System.getProperty("user.dir");
	public static String fileName = "TestData.xlsx";
	public static String sheetName = "HubSpot";
	
	@FindBy(xpath = "//div[@class='home-products-feature__cta']//a[contains(@href,'cms')]") WebElement cmsHub;
	@FindBy(xpath = "//*[contains(text(),'CMS Hub Professional')]") WebElement cmsHubProfessionalElement;
	@FindBy(xpath = "//*[contains(text(),'CMS Hub Enterprise')]") WebElement cmsHubEnterpriseElement;
	@FindBy(css = ".cta--primary.cta--large.cms-hero-1") WebElement getDemo;
	@FindBy(xpath = "//input[@name='firstname']") WebElement firstNameElement;
	@FindBy(xpath = "//input[@name='lastname']") WebElement lastNameElement;
	@FindBy(xpath = "//input[@name='email']") WebElement emailElement;
	@FindBy(xpath = "//input[@name='phone']") WebElement phoneElement;
	@FindBy(xpath = "//input[@name='company']") WebElement companyElement;
	@FindBy(xpath = "//input[@name='website']") WebElement websiteElement;
	@FindBy(xpath = "//input[@type='submit']") WebElement submitElement;
	@FindBy(xpath = "//select[@name='employees__c']") WebElement employeesElement;
	@FindBy(css = ".hsg-page-header__heading") WebElement successRegistration;
	@FindBy(xpath = "//button[@aria-label='Close live chat']") WebElement closeHubBot;
	
	String employees = "//select[@name='employees__c']";
	
	public HubSpotPage(WebDriver driver) {
		this.driver = driver;
		wait=new WebDriverWait(driver,20);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		js = (JavascriptExecutor) driver;
	}

	public void clickOnCMSHub() {
		wait.until(ExpectedConditions.elementToBeClickable(cmsHub)).click();
	}
	
	public static String readExcel(String filePath, String fileName, String sheetName, String RowField)
			throws IOException {
		// Create a object of File class to open xlsx file
		File file = new File(filePath + "//" + fileName);
		
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		
		Workbook workbook = null;		
		workbook = new XSSFWorkbook(inputStream);
		// Read sheet inside the workbook by its name
		Sheet sheet = workbook.getSheet(sheetName);
		// Find number of rows and columns in excel sheet
		int rowCount = ((sheet.getLastRowNum() - sheet.getFirstRowNum()) + 1);
		int colCount = sheet.getRow(0).getLastCellNum();

		System.out.println("No of rows are : " + rowCount);
		System.out.println("No of columns are : " + colCount);

		for (int i = 0; i < rowCount; i++) {

			Row row = sheet.getRow(i);
			Cell cell = CellUtil.getCell(row, 0);

			if (cell.getStringCellValue().equals(RowField)) {
				System.out.println("Inside if");
				String strCellValue = row.getCell(1).getStringCellValue();
				System.out.println("Cell value is : " + strCellValue);
				return strCellValue;
			}
		}
		return "NoRowMatched";
	}

	public Boolean verifyCmsHubProfessional() throws IOException {
		String expectedCmsHubProfessional = readExcel(filePath, fileName, sheetName, "cmsHubProfessional");
		String actualCmsHubProfessional = wait.until(ExpectedConditions.elementToBeClickable(cmsHubProfessionalElement)).getText();
		if(actualCmsHubProfessional.equals(expectedCmsHubProfessional)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean verifyCmsHubEnterprise() throws IOException {
		String expectedCmsHubEnterprise = readExcel(filePath, fileName, sheetName, "cmsHubEnterprise");
		String actualCmsHubEnterprise = wait.until(ExpectedConditions.elementToBeClickable(cmsHubEnterpriseElement)).getText();
		if(actualCmsHubEnterprise.equals(expectedCmsHubEnterprise)) {
			return true;
		} else {
			return false;
		}
	}

	public void clickOnGetDemo() {
		wait.until(ExpectedConditions.elementToBeClickable(getDemo)).click();
	}

	public void fillRequiredDetails() throws IOException {
		String firstName = readExcel(filePath, fileName, sheetName, "firstName");
		waitForLoad(driver);
		wait.until(ExpectedConditions.elementToBeClickable(firstNameElement)).sendKeys(firstName);
		
		String lastName = readExcel(filePath, fileName, sheetName, "lastName");
		wait.until(ExpectedConditions.elementToBeClickable(lastNameElement)).sendKeys(lastName);
		
		String email = readExcel(filePath, fileName, sheetName, "email");
		wait.until(ExpectedConditions.elementToBeClickable(emailElement)).sendKeys(email);
		
		String phone = readExcel(filePath, fileName, sheetName, "phone");
		wait.until(ExpectedConditions.elementToBeClickable(phoneElement)).sendKeys(phone);
		
		String company = readExcel(filePath, fileName, sheetName, "company");
		wait.until(ExpectedConditions.elementToBeClickable(companyElement)).sendKeys(company);
		
		String website = readExcel(filePath, fileName, sheetName, "website");
		wait.until(ExpectedConditions.elementToBeClickable(websiteElement)).sendKeys(website);
		
		String employeeNumber = readExcel(filePath, fileName, sheetName, "employeeNumber");
		Select select = new Select(driver.findElement(By.xpath(employees)));
		select.selectByValue(employeeNumber);
		
	}

	public void clickSubmitButton() {
		wait.until(ExpectedConditions.elementToBeClickable(submitElement)).click();
	}

	public Boolean getSuccessMessage() throws IOException {
		String actualMessage = wait.until(ExpectedConditions.elementToBeClickable(successRegistration)).getText();
		String expectedMessage = readExcel(filePath, fileName, sheetName, "successMessage");
		if(actualMessage.equals(expectedMessage)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        wait.until(pageLoadCondition);
    }

	public void closeHubBot() {
		try {
			closeHubBot.click();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
