package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class OrangeHrmProperties {

	public static final String BASE_URL_KEY = "baseUrl";
	public static final String DEFAULT_USER_KEY = "defaultUser";
	public static final String DEFAULT_PASSWORD_KEY = "defaultPassword";
	public static final String DRIVER_PATH = "driverPath";
	public static final String DRIVER_TYPE_KEY = "driverType";
	public static final String PAGE_LOAD_TIMEOUT_KEY = "pageLoadTimeout";
	public static final String HEADLESS_KEY = "headlessMode";

	private Properties properties;
	private String path = System.getProperty("user.dir");
	private final String propertyFilePath = path + "//uitest.properties";

	public void UitestFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("uitest.properties not found at " + propertyFilePath);
		}
	}

	public String getDriverPath() {
		String driverPath = properties.getProperty(DRIVER_PATH);
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the uitest.properties file.");
	}

	public long getPageLoadTimeOut() {
		String pageLoadTimeOut = properties.getProperty(PAGE_LOAD_TIMEOUT_KEY);
		if (pageLoadTimeOut != null)
			return Long.parseLong(pageLoadTimeOut);
		else
			throw new RuntimeException("page load timeOut not specified in the uitest.properties file.");
	}

	public String getApplicationUrl() {
		String url = properties.getProperty(BASE_URL_KEY);
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the uitest.properties file.");
	}

	public String getDriverType() {
		String driverType = properties.getProperty(DRIVER_TYPE_KEY);
		if (driverType != null)
			return driverType;
		else
			throw new RuntimeException("driver type not specified in the uitest.properties file.");
	}
	
	public String getDefaultUser() {
		String defaultUser = properties.getProperty(DEFAULT_USER_KEY);
		if (defaultUser != null)
			return defaultUser;
		else
			throw new RuntimeException("default user not specified in the uitest.properties file.");
	}
	
	public String getDefaultPassword() {
		String defaultPassword = properties.getProperty(DEFAULT_PASSWORD_KEY);
		if (defaultPassword != null)
			return defaultPassword;
		else
			throw new RuntimeException("default password not specified in the uitest.properties file.");
	}
}
