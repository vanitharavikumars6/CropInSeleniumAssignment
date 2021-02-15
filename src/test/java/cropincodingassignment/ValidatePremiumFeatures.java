package cropincodingassignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class ValidatePremiumFeatures {
	
	SeleniumHelper helper = new SeleniumHelper();
	WebDriver driver;
	HubSpotPage hspage;
	
	@BeforeMethod
	public void beforeMethod() {
		driver = helper.initializeDriver();
		helper.setImplicitWait(driver);
		hspage = helper.launchApplication(driver);
	}
	
	@Test
	public void validatePremiumFeatures() throws IOException {
		
		hspage.clickOnCMSHub();
		
		//Verify CMS Premium features
		Boolean cmsHubProf = hspage.verifyCmsHubProfessional();
		Assert.assertTrue(cmsHubProf);
		
		Boolean cmsHubEnterPrise = hspage.verifyCmsHubEnterprise();
		Assert.assertTrue(cmsHubEnterPrise);
		
		//Click on get a demo
		hspage.clickOnGetDemo();
	
		//Close hub bot
		hspage.closeHubBot();
		
		//Fill the form with required details
		hspage.fillRequiredDetails();
		
		//Click on submit button
		hspage.clickSubmitButton();
		
		//Verify success message after registration
		Boolean successMessage = hspage.getSuccessMessage();
		Assert.assertTrue(successMessage, "User is not registered successfully");
		
		}
	
	@AfterMethod
	  public void afterMethod() {	 
	    driver.close(); 
	  }

}
