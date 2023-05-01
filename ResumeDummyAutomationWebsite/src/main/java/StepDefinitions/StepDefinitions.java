package StepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import WebpageComponents.MenuOptions;
import Webpages.CheckoutFormPage;
import Webpages.CheckoutPage;
import Webpages.LoginPage;
import Webpages.ProductsPage;
import com.google.common.base.Function;
import framework.UIAction;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class StepDefinitions {
	private UIAction action;
	private LoginPage login_page;

	@Before
	public void setup() {
		this.action = new UIAction();
		this.login_page = new LoginPage(this.action);
	}

	@When("I navigate to login webpage")
	public void i_navigate_to_login_webpage() {
		this.login_page.navigate_to_login_page();
	}

	@When("I enter valid credentials for locked out user")
	public void i_enter_valid_credentials_for_locked_out_user() {
		this.login_page.login("locked_out_user", "secret_sauce");
	}

	@Then("I should be notified that I am locked out as a locked out user")
	public void i_should_be_notified_that_i_am_locked_out() {
		boolean is_displayed = this.login_page.is_locked_out_error_displayed();
		assertTrue(is_displayed);
	}

	@When("I enter valid credentials")
	public void i_enter_valid_credentials() {
		this.login_page.login("standard_user", "secret_sauce");
	}

	@Then("I should be navigated to the products webpage")
	public void i_should_be_navigated_to_the_products_webpage() {
		assertEquals(ProductsPage.url, this.action.getCurrentUrl());
	}

	@Then("the products should be sorted appropriately by the dropdown")
	public void the_products_should_be_sorted_appropriately_by_the_dropdown( ) {
		ProductsPage products_page = new ProductsPage(this.action);
		assertTrue(products_page.is_sorted_a_to_z_after_sorting());
		assertTrue(products_page.is_sorted_z_to_a_after_sorting());
		assertTrue(products_page.is_sorted_lo_to_hi_after_sorting());
		assertTrue(products_page.is_sorted_hi_to_lo_after_sorting());
	}

	@And("I add a product to the cart")
	public void i_add_a_product_to_the_cart() {
		ProductsPage products_page = new ProductsPage(this.action);
		products_page.add_product_to_cart();
	}

	@Then("the shopping cart badge should indicate the number of products added")
	public void the_shopping_cart_badge_should_indicate_the_number_of_products_added() {
		ProductsPage products_page = new ProductsPage(this.action);
		assertEquals(1, products_page.get_shopping_cart_badge_quantity());
	}

	@When("I go to checkout")
	public void i_go_to_checkout() {
		ProductsPage products_page = new ProductsPage(this.action);
		products_page.click_shopping_cart();
		CheckoutPage checkout_page = new CheckoutPage(this.action);
		checkout_page.click_checkout();
	}

	@Then("the required fields should act as such")
	public void the_required_fields_should_act_as_such() {
		CheckoutFormPage checkout_form_page = new CheckoutFormPage(this.action);
		checkout_form_page.check_required_fields();
		assertEquals("https://www.saucedemo.com/checkout-step-two.html", this.action.getCurrentUrl());
	}

	@Then("I logout")
	public void i_logout() {
		MenuOptions.open_side_menu(this.action);
		MenuOptions.reset_app_state(this.action);
		MenuOptions.logout(this.action);
	}

	@After
	public void teardown() throws InterruptedException {
		this.action.quit();
	}
}
