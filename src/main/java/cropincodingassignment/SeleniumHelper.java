package cropincodingassignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumHelper {
	
	public WebDriver initializeDriver() {
		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		return driver;
	}
	
	public void setImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	public HubSpotPage launchApplication(WebDriver driver) {
		driver.get("https://www.hubspot.com/");
		driver.manage().window().maximize();
		return new HubSpotPage(driver);
	}
}
