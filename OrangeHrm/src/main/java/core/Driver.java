package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;

public class Driver {

	public enum DriverType {
		CHROME, FIREFOX, EDGE, IE
	}

	protected WebDriver driver;
	protected DriverType driverType;

	private OrangeHrmProperties props;

	private String baseUrl;
	private String driverTypeName;
	private String defaultUser;
	private String defaultPassword;
	private long pageLoadTimeout;

	public Driver(OrangeHrmProperties props) {
		this.props = props;
		baseUrl = props.getApplicationUrl();
		defaultUser = props.getDefaultUser();
		defaultPassword = props.getDefaultPassword();
		driverTypeName = props.getDriverType();
		pageLoadTimeout = props.getPageLoadTimeOut();
	}

	public void startDriver() {
		driver = newWebDriver();
	}

	public boolean isRunning() {
		return driver != null;
	}

	public void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	private WebDriver newWebDriver() {

		String webDriverType = driverTypeName.toLowerCase();

		if (driver == null) {

			if (webDriverType.equals("firefox")) {
				System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, props.getDriverPath());
				FirefoxOptions options = new FirefoxOptions();
				options.setAcceptInsecureCerts(true);
				driver = new FirefoxDriver(options);
				driverType = DriverType.FIREFOX;

			} else if (webDriverType.equals("chrome")) {
				System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, props.getDriverPath());
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				driver = new ChromeDriver(options);
				driverType = DriverType.CHROME;

			}
		}

		return driver;
	}

	/**
	 * Returns the default login user as specified in the configuration file.
	 */
	public String getDefaultUser() {
		return defaultUser;
	}

	/**
	 * Returns the password for the default login user as specified in the
	 * configuration file.
	 */
	public String getDefaultPassword() {
		return defaultPassword;
	}

	/**
	 * Returns the base URL for the AUT as specified in the configuration file.
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public DriverType getDriverType() {
		return driverType;
	}

	public long getPageLoadTimeout() {
		return pageLoadTimeout;
	}

}
