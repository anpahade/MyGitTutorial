import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPageObject;
import util.BaseTestCase;

public class ValidateQuickLaunch extends BaseTestCase {

	@Test
	public void Can_Successful_Login() {
		openOrangeHrmWithDefault();
		DashboardPageObject dashboard=new DashboardPageObject();
		Assert.assertEquals(dashboard.getHeadLabelText(), "Dashboard");
		
		System.out.println("Successfuly verified dashboard page");
		
	}

}
