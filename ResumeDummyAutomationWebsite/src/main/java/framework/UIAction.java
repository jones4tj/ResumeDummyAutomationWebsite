package framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Loggers.Log4jLogger;
import Loggers.Logger;

public class UIAction {
	
	private WebDriver driver;
	private Logger logger;
	private WebElement element;

	public UIAction() {
		this.driver = new ChromeDriver();
		this.logger = new Log4jLogger(this.getClass().getName());
		this.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
	}
	
	public UIAction get(String url) {
		this.driver.get(url);
		this.logger.info("Navigating to url: " + url);
		return this;
	}
	
	public UIAction wait_for_presence_of_element_and_click(By locator, long milliseconds) {
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofMillis(milliseconds));
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.presenceOfElementLocated(locator)).click();
		return this;
	}
	
	public Wait<WebDriver> fluent_wait(int timeoutInSeconds, int pollingInMilliseconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(this.driver)
				.withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingInMilliseconds))
				.ignoring(StaleElementReferenceException.class);
		return wait;
	}
	
	public UIAction findElement(By locator) {
		this.element = this.driver.findElement(locator);
		this.logger.info("Finding element: " + this.element.getTagName() + " using locator: " + locator);
		return this;
	}
	
	public List<WebElement> findElements(By locator) {
		this.logger.info("Finding elements using locator: " + locator);
		return this.driver.findElements(locator);
	}
	
	public UIAction click() {
		this.element.click();
		this.logger.info("Clicking element: " + this.element.getTagName());
		return this;
	}
	
	public UIAction sendKeys(CharSequence keys) {
		this.element.sendKeys(keys);
		this.logger.info("Sending keys: " + keys + " to element: " + this.element.getTagName());
		return this;
	}
	
	public Select select() {
		return new Select(this.element);
	}

	public String getText() {
		this.logger.info("Getting text: " + this.element.getText() + " from element: " + this.element.getTagName());
		return element.getText();
	}
	
	public boolean isDisplayed() {
		this.logger.info("Checking is displayed: " + this.element.isDisplayed() + " from element: " + this.element.getTagName());
		return this.element.isDisplayed();
	}
	
	public boolean isEnabled() {
		this.logger.info("Checking is enabled: " + this.element.isEnabled() + " from element: " + this.element.getTagName());
		return this.element.isEnabled();
	}
	
	public boolean isSelected() {
		this.logger.info("Checking is selected: " + this.element.isSelected() + " from element: " + this.element.getTagName());
		return this.element.isSelected();
	}
	
	public String getCurrentUrl() {
		this.logger.info("Retrieving current url: " + this.driver.getCurrentUrl());
		return this.driver.getCurrentUrl();
	}
	
	public void quit() {
		this.driver.quit();
	}
}
