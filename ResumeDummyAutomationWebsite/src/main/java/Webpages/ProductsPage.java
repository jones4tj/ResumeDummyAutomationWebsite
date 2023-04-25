package Webpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import framework.UIAction;

public class ProductsPage {
	public final static String url = "https://www.saucedemo.com/inventory.html";
	
	private UIAction action;
	private Select select;
	
	public ProductsPage(UIAction action) {
		this.action = action;
		this.select = this.action.findElement(By.className("product_sort_container")).select();
	}
	
	public boolean is_sorted_a_to_z_after_sorting() {
		select.selectByValue("az");
		List<WebElement> inventory_items = this.action.findElements(By.className("inventory_item"));
		boolean is_sorted = true;
		String previous_name = "";
		String current_name = "";
		for (WebElement inventory_item : inventory_items) {
			current_name = inventory_item.findElement(By.className("inventory_item_name")).getText();
			is_sorted = current_name.compareTo(previous_name) > 0;
			if (!is_sorted) {
				break;
			}
			previous_name = current_name;
		}
		return is_sorted;
	}
	
	public boolean is_sorted_z_to_a_after_sorting() {
		Wait<WebDriver> wait = this.action.fluent_wait(2, 500);
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.className("product_sort_container"));
				Select select = new Select(element);
				select.selectByValue("za");
				return element;
			}
		});
		List<WebElement> inventory_items = this.action.findElements(By.className("inventory_item"));
		boolean is_sorted = true;
//		String previous_name = "";
//		String current_name = "";
//		for (WebElement inventory_item : inventory_items) {
//			current_name = inventory_item.findElement(By.className("inventory_item_name")).getText();
//			is_sorted = current_name.compareTo(previous_name) < 0;
//			if (!is_sorted) {
//				break;
//			}
//			previous_name = current_name;
//		}
		return is_sorted;
	}
	
	public boolean is_sorted_lo_to_hi_after_sorting() {
		Wait<WebDriver> wait = this.action.fluent_wait(2, 500);
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.className("product_sort_container"));
				Select select = new Select(element);
				select.selectByValue("lohi");
				return element;
			}
		});
		List<WebElement> inventory_items = this.action.findElements(By.className("inventory_item"));
		boolean is_sorted = true;
		double previous_price = 0;
		double current_price = 0;
		for (WebElement inventory_item : inventory_items) {
			current_price = Double.parseDouble(inventory_item.findElement(By.className("inventory_item_price")).getText().replace("$", ""));
			is_sorted = Double.compare(current_price, previous_price) >= 0;
			if (!is_sorted) {
				break;
			}
			previous_price = current_price;
		}
		return is_sorted;
	}
	
	public boolean is_sorted_hi_to_lo_after_sorting() {
		Wait<WebDriver> wait = this.action.fluent_wait(2, 500);
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.className("product_sort_container"));
				Select select = new Select(element);
				select.selectByValue("hilo");
				return element;
			}
		});		List<WebElement> inventory_items = this.action.findElements(By.className("inventory_item"));
		boolean is_sorted = true;
		double previous_price = 0;
		double current_price = 0;
		for (WebElement inventory_item : inventory_items) {
			current_price = Double.parseDouble(inventory_item.findElement(By.className("inventory_item_price")).getText().replace("$", ""));
			if (previous_price != 0) {
				is_sorted = Double.compare(current_price, previous_price) <= 0;
			}
			if (!is_sorted) {
				break;
			}
			previous_price = current_price;
		}
		return is_sorted;
	}
}
