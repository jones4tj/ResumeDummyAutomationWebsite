package Webpages;

import com.google.common.base.Function;
import framework.UIAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

public class CheckoutPage {

    private UIAction action;

    public CheckoutPage(UIAction action) {
        this.action = action;
    }

    public void click_checkout() {
        Wait<WebDriver> wait = this.action.fluent_wait(2, 500);
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.id("checkout"));
                element.click();
                return element;
            }
        });
    }
}
