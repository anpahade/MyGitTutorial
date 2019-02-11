package util;

import java.util.List;
import javax.annotation.Nonnull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseObject {

	protected static final long PAGE_LOAD_TIMEOUT = BaseTestCase.getPageLoadTimeout();

	private final WebDriverWait wait = new WebDriverWait(getWebDriver(), PAGE_LOAD_TIMEOUT);

	public static WebDriver getWebDriver() {
		return BaseTestCase.getWebDriver();
	}

	public static void waitForContent() {
		ExpectedCondition<Boolean> loaded = new ExpectedCondition<Boolean>() {
			public Boolean apply(@Nonnull WebDriver driver) {
				String result = "return ((document.readyState === 'complete') && ($.active == 0))";
				return (Boolean) ((JavascriptExecutor) driver).executeScript(result);
			}
		};
		try {
			new WebDriverWait(getWebDriver(), PAGE_LOAD_TIMEOUT).until(loaded);
		} catch (WebDriverException e) {
			// if $.active == 0 not work(for example CFS)
			loaded = new ExpectedCondition<Boolean>() {
				public Boolean apply(@Nonnull WebDriver driver) {
					String result = "return ((document.readyState === 'complete'))";
					return (Boolean) ((JavascriptExecutor) driver).executeScript(result);
				}
			};
			new WebDriverWait(getWebDriver(), PAGE_LOAD_TIMEOUT).until(loaded);
		}
	}

	public void waitForContent(By by) {
		waitForContent();
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public WebElement findElement(By by) {
		waitForContent(by);
		return getWebDriver().findElement(by);
	}

	public List<WebElement> findElements(By by) {
		waitForContent();
		return getWebDriver().findElements(by);
	}

	/**
	 * Returns true if element is displayed on the page
	 *
	 * @param by
	 * @return
	 */
	public boolean isDisplayed(By by) {
		try {
			return findElement(by).isDisplayed();
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Returns true if element is Enabled on the page
	 *
	 * @param by
	 * @return
	 */
	public boolean isEnabled(By by) {
		if (isPresent(by)) {
			return findElement(by).isEnabled();
		} else {
			return false;
		}
	}

	/**
	 * Returns true if element is present on the page
	 *
	 * @param by
	 * @return
	 */
	public boolean isPresent(By by) {
		return findElements(by).size() > 0;
	}

	/**
	 * Returns true if element is present on the page
	 *
	 * @param by
	 * @return
	 */
	public boolean isSelected(By by) {
		return findElement(by).isSelected();
	}

	/**
	 * Returns text contained in the element
	 *
	 * @param by
	 * @return
	 */
	public String getText(By by) {
		try {
			return findElement(by).getText();
		} catch (TimeoutException e) {
			return "";
		}
	}

	public void click(By by) {
		findElement(by).click();
		waitForContent();
	}

	public void doubleClick(By by) {
		Actions act = new Actions(getWebDriver());
		act.doubleClick(findElement(by)).build().perform();
	}

	/**
	 * Pause for specified number of milliseconds
	 *
	 */
	public static void pauseFor(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException iex) {
			// Ignore since not a problem
		}
	}

}
