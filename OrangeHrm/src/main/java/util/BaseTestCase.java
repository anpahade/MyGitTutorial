package util;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import core.Driver;
import core.Driver.DriverType;
import core.OrangeHrmProperties;
import pages.LoginPageObject;

public class BaseTestCase {

	private static Driver currentSession;
	protected static volatile OrangeHrmProperties props;

	public static WebDriver getWebDriver() {
		return currentSession.getWebDriver();
	}

	@BeforeClass
	public static void testClassSetup() {
		if (props == null) {
			props = new OrangeHrmProperties();
		}
		// Load the properties
		props.UitestFileReader();
		currentSession = createNewSession(props);
	}

	@AfterClass
	public static void testClassTearDown() {
		currentSession.tearDown();
	}

	public static Driver createNewSession(OrangeHrmProperties props) {
		Driver newSession = new Driver(props);
		newSession.startDriver();
		return newSession;
	}

	public String getBaseUrl() {
		return currentSession.getBaseUrl();
	}

	public static long getPageLoadTimeout() {
		return currentSession.getPageLoadTimeout();
	}

	public void browserRefresh() {
		getWebDriver().navigate().refresh();
		waitForContent();
	}

	public static DriverType getDriverType() {
		return currentSession.getDriverType();
	}

	public static Driver getCurrentSession() {
		return currentSession;
	}

	public void browserBack() {
		getWebDriver().navigate().back();
		waitForContent();
	}

	protected void openOrangeHrmWithDefault() {
		String url = getBaseUrl();
		getWebDriver().manage().window().maximize();
		getWebDriver().get(url);
		waitForContent();
		LoginPageObject formLogin = new LoginPageObject();
		formLogin.login(currentSession.getDefaultUser(), currentSession.getDefaultPassword());

	}

	public void waitForContent() {
		BaseObject.waitForContent();
	}

}
