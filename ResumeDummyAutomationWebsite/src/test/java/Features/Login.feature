Feature: Login

  Background: Navigate to login webpage 
    When I navigate to login webpage

  Scenario: Attempt to login with locked out user
    When I enter valid credentials for locked out user
    Then I should be notified that I am locked out as a locked out user

  Scenario: Login to products webpage
    When I enter valid credentials
    Then I should be navigated to the products webpage
    And I logout

  Scenario: Sort products
    When I enter valid credentials
    Then the products should be sorted appropriately by the dropdown
    And I logout

  Scenario: Shopping Cart Badge value should indicate number of products in cart
    When I enter valid credentials
    And I add a product to the cart
    Then the shopping cart badge should indicate the number of products added
    And I logout