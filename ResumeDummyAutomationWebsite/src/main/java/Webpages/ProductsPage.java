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
	private By products;
	private By shopping_cart_link;
	public ProductsPage(UIAction action) {
		this.action = action;
		this.select = this.action.findElement(By.xpath("//div//select")).select();
		this.products = new By.ByXPath("//div[@class='inventory_item']");
		this.shopping_cart_link = new By.ByXPath("//a[@class='shopping_cart_link']");
	}

	public void add_product_to_cart() {
		List<WebElement> inventory_items = this.action.findElements(this.products);
		inventory_items.get(0).findElement(By.xpath("//div[@class='pricebar']//button")).click();
	}

	public int get_shopping_cart_badge_quantity() {
		return Integer.parseInt(this.action.findElement(By.xpath("//div//a//span")).getText());
	}

	public void click_shopping_cart() {
		this.action.wait_for_presence_of_element_and_click(this.shopping_cart_link, 500);
	}
	
	public boolean is_sorted_a_to_z_after_sorting() {
		this.select.selectByValue("az");
		List<WebElement> inventory_items = this.action.findElements(this.products);
		boolean is_sorted = true;
		String previous_name = "";
		String current_name = "";
		for (WebElement inventory_item : inventory_items) {
			current_name = inventory_item.findElement(By.xpath("//div[@class='inventory_item_label']//a//div")).getText();
			is_sorted = current_name.compareTo(previous_name) >= 0;
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
				WebElement element = driver.findElement(By.xpath("//div//select"));
				Select select = new Select(element);
				select.selectByValue("za");
				return element;
			}
		});
		List<WebElement> inventory_items = this.action.findElements(this.products);
		boolean is_sorted = true;
		String previous_name = "";
		String current_name = "";
		for (WebElement inventory_item : inventory_items) {
			current_name = inventory_item.findElement(By.xpath("//div[@class='inventory_item_label']//a//div")).getText();
			if (previous_name != "") {
				is_sorted = current_name.compareTo(previous_name) <= 0;
			}
			if (!is_sorted) {
				break;
			}
			previous_name = current_name;
		}
		return is_sorted;
	}
	
	public boolean is_sorted_lo_to_hi_after_sorting() {
		Wait<WebDriver> wait = this.action.fluent_wait(2, 500);
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath("//div//select"));
				Select select = new Select(element);
				select.selectByValue("lohi");
				return element;
			}
		});
		List<WebElement> inventory_items = this.action.findElements(this.products);
		boolean is_sorted = true;
		double previous_price = 0;
		double current_price = 0;
		for (WebElement inventory_item : inventory_items) {
			current_price = Double.parseDouble(inventory_item.findElement(By.xpath("//div[@class='inventory_item_price']")).getText().replace("$", ""));
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
				WebElement element = driver.findElement(By.xpath("//div//select"));
				Select select = new Select(element);
				select.selectByValue("hilo");
				return element;
			}
		});		List<WebElement> inventory_items = this.action.findElements(this.products);
		boolean is_sorted = true;
		double previous_price = 0;
		double current_price = 0;
		for (WebElement inventory_item : inventory_items) {
			current_price = Double.parseDouble(inventory_item.findElement(By.xpath("//div[@class='inventory_item_price']")).getText().replace("$", ""));
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
