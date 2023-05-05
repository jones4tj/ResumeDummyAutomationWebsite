package Webpages;

import framework.UIAction;
import org.openqa.selenium.By;

public class CheckoutFormPage {
    private UIAction action;
    private By first_name;
    private By last_name;
    private By postal_code;
    private By error;
    private By continue_button;
    public CheckoutFormPage(UIAction action) {
        this.action = action;
        this.first_name = new By.ById("first-name");
        this.last_name = new By.ById("last-name");
        this.postal_code = new By.ById("postal-code");
        this.error = new By.ByXPath("//div//h3");
        this.continue_button = new By.ById("continue");
    }

    public void check_required_fields() {
        this.check_first_name_field();
        this.check_last_name_field();
        this.check_postal_code_field();
        this.click_continue();
    }

    private void check_first_name_field() {
        this.click_continue();
        this.action.findElement(this.error).isDisplayed();
        this.action.findElement(this.first_name).sendKeys("Paul");
    }

    private void check_last_name_field() {
        this.click_continue();
        this.action.findElement(this.error).isDisplayed();
        this.action.findElement(this.last_name).sendKeys("Hayes");
    }

    private void check_postal_code_field() {
        this.click_continue();
        this.action.findElement(this.error).isDisplayed();
        this.action.findElement(this.postal_code).sendKeys("12345");
    }

    private void click_continue() {
        this.action.wait_for_presence_of_element_and_click(this.continue_button, 500);
    }
}
