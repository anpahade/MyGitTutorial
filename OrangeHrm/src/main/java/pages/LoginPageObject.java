package pages;

import org.openqa.selenium.By;

import util.BaseSolvent;
import util.Element;

public class LoginPageObject extends BaseSolvent {
	
	public final Element usernameTextbox=new Element(By.xpath("//input[@id='txtUsername']"));
	public final Element passwordTextbox=new Element(By.xpath("//input[@id='txtPassword']"));
	public final Element loginButton=new Element(By.xpath("//input[@id='btnLogin']"));
	
	public void login(String userName, String password){
		usernameTextbox.clear();
		usernameTextbox.sendKeys(userName);
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
		loginButton.click();
		waitForContent();
	}

}
