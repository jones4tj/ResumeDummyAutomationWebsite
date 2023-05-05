package Webpages;


import org.openqa.selenium.By;

import framework.UIAction;

public class LoginPage {
	public static final String url = "https://www.saucedemo.com/";
	
	private UIAction action;

	private By username_locator;
	private By password_locator;
	private By locked_out_locator;
	private By login_button;
	public LoginPage(UIAction action) {
		this.action = action;
		this.username_locator = new By.ById("user-name");
		this.password_locator = new By.ById("password");
		this.locked_out_locator = new By.ByXPath("//div//h3");
		this.login_button = new By.ById("login-button");
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
		this.action.wait_for_presence_of_element_and_click(this.login_button, 500);
	}
	
	public boolean is_locked_out_error_displayed() {
		return this.action.findElement(this.locked_out_locator).isDisplayed();
	}
}
