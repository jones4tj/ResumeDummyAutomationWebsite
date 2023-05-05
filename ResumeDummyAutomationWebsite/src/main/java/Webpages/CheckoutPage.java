package Webpages;

import framework.UIAction;
import org.openqa.selenium.By;

public class CheckoutPage {

    private UIAction action;

    private By checkout_button;

    public CheckoutPage(UIAction action) {
        this.action = action;
        this.checkout_button = new By.ById("checkout");
    }

    public void click_checkout() {
        this.action.wait_for_presence_of_element_and_click(this.checkout_button, 500);
    }
}
