package cropincodingassignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

	public static void waitUntilelementIsVisible(String locator, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} catch (Exception e) {
			 e.printStackTrace();
		}

	}
}
