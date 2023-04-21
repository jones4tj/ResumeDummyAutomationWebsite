package Webpages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import framework.UIAction;

public class LoginPage {
	public static final String url = "https://www.saucedemo.com/";
	
	private UIAction action;

	private By username_locator;
	private By password_locator;
	private By locked_out_locator;
	
	public LoginPage(UIAction action) {
		this.action = action;
		this.username_locator = new By.ById("user-name");
		this.password_locator = new By.ById("password");
		this.locked_out_locator = new By.ByClassName("error-message-container");
	}
	
	public void navigate_to_login_page() {
		this.action.get(url);
	}
	
	public void login(String username, String password) {
		this.enterUsername(username);
		this.enterPassword(password);
		this.clickLoginButton();
	}
	
	public void enterUsername(String username) {
		this.action.findElement(this.username_locator)
				.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		this.action.findElement(this.password_locator)
				.sendKeys(password);
	}
	
	public void clickLoginButton() {
		Wait<WebDriver> wait = this.action.fluent_wait(5, 500);
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(new By.ById("login-button"));
				element.click();
				return element;
			}
		});
	}
	
	public boolean is_locked_out_error_displayed() {
		this.action.wait_for(this.locked_out_locator, 1000);
		return this.action.findElement(this.locked_out_locator).isDisplayed();
	}
}
