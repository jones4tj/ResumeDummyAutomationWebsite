package StepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import WebpageComponents.MenuOptions;
import Webpages.LoginPage;
import Webpages.ProductsPage;
import framework.UIAction;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
