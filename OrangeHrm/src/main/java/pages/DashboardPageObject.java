package pages;

import org.openqa.selenium.By;

import util.BaseObject;
import util.Element;

public class DashboardPageObject extends BaseObject {

	private final Element headLabel = new Element(By.xpath("//div[@class='head']//h1"));

	public String getHeadLabelText() {
		return headLabel.getText();
	}
}
