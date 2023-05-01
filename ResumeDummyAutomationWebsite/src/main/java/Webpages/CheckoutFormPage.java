package Webpages;

import com.google.common.base.Function;
import framework.UIAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

public class CheckoutFormPage {
    private UIAction action;

    public CheckoutFormPage(UIAction action) {
        this.action = action;
    }

    public void check_required_fields() {
        this.check_first_name_field();
        this.check_last_name_field();
        this.check_postal_code_field();
        this.click_continue();
    }

    private void check_first_name_field() {
        this.action.findElement(By.id("continue")).click();
        this.action.findElement(By.className("error-message-container")).isDisplayed();
        this.action.findElement(By.id("first-name")).sendKeys("Paul");
    }

    private void check_last_name_field() {
        this.action.findElement(By.id("continue")).click();
        this.action.findElement(By.className("error-message-container")).isDisplayed();
        this.action.findElement(By.id("last-name")).sendKeys("Hayes");
    }

    private void check_postal_code_field() {
        this.action.findElement(By.id("continue")).click();
        this.action.findElement(By.className("error-message-container")).isDisplayed();
        this.action.findElement(By.id("postal-code")).sendKeys("12345");
    }

    private void click_continue() {
        Wait<WebDriver> wait = this.action.fluent_wait(2, 500);
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.id("continue"));
                element.click();
                return element;
            }
        });
    }
}
