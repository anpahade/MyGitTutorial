package pages;

import org.openqa.selenium.By;

import util.BaseObject;
import util.Element;

public class LoginPageObject extends BaseObject {
	
	public final Element usernameTextbox=new Element(By.id("txtUsername"));
	public final Element passwordTextbox=new Element(By.id("txtPassword"));
	public final Element loginButton=new Element(By.id("btnLogin"));
	
	public void login(String userName, String password){
		usernameTextbox.clear();
		usernameTextbox.sendKeys(userName);
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
		loginButton.click();
		waitForContent();
	}

}
