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
