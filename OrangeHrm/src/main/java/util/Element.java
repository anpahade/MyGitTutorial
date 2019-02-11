package util;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Element extends BaseSolvent {

  private By locator;

  public Element(String xpath) {
    locator = By.xpath(xpath);
  }

  /**
   * Use this for other types of locators such as cssSelector
   *
   * @param by
   */
  public Element(By by) {
    locator = by;
  }

  public By get() {
    return locator;
  }

  public WebElement getWebElement() {
    return findElement(locator);
  }

  public void waitFor() {
    super.waitForContent(locator);
  }

  public boolean isDisplayed() {
    return isDisplayed(locator);
  }

  public boolean isEnabled() {
    return isEnabled(locator);
  }

  public boolean isPresent() {
    return isPresent(locator);
  }

  public boolean isSelected() {
    return isSelected(locator);
  }

  public void click() {
    click(locator);
  }

  public void doubleClick() {
    doubleClick(locator);
  }

  public String getText() {
    return getText(locator);
  }

  @Override
  public String toString() {
    return getText();
  }

  public Point getLocation() {
    return getWebElement().getLocation();
  }

  public Dimension getSize() {
    return getWebElement().getSize();
  }

  public void clear() {
    getWebElement().clear();
  }

  public void sendKeys(CharSequence keysToSend) {
    getWebElement().sendKeys(keysToSend);
  }

  public String getAttribute(String name) {
    return getWebElement().getAttribute(name);
  }

  public String getCssValue(String value) {
    return getWebElement().getCssValue(value);
  }

  public void hoverOnElement() {
    Actions action = new Actions(getWebDriver());
    action.moveToElement(getWebElement()).build().perform();
  }

  public void hoverAndClickOnElement() {
    Actions action = new Actions(getWebDriver());
    action.moveToElement(getWebElement()).click().build().perform();
  }

}
