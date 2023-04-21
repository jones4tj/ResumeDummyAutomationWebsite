package WebpageComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import framework.UIAction;

public class MenuOptions {
	
	public static void open_side_menu(UIAction action) {
		By side_menu_button_locator = new By.ById("react-burger-menu-btn");
		action.findElement(side_menu_button_locator).click();
	}
	
	public static void reset_app_state(UIAction action) {
		By reset_app_state_button_locator = new By.ById("reset_sidebar_link");
		action.findElement(reset_app_state_button_locator).click();
	}
	
	public static void logout(UIAction action) {
		Wait<WebDriver> wait = action.fluent_wait(5, 500);
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				By logout_button_locator = new By.ById("logout_sidebar_link");
				WebElement element = driver.findElement(logout_button_locator);
				element.click();
				return element;
			}
		});
	}
	
	private MenuOptions() {}
}
