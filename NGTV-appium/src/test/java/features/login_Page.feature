Feature: NgTv login page Automation

  Scenario Outline: Validation of Login using <type>
    Given As a user I want to open the NGTV
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User gets a toast message in "login page"

    Examples:
      | username       | password    | type  |
      | Android        | Android@123 | email |
      | androidtesting | Gaian@123   | email |

  Scenario Outline: Verify the User Interface in login page
    Given As a user I want to open the NGTV
    When User wants to identify the "WKARng" image
    And User wants to identify the "MobiusTv" image
    Then User enters "<username>" in Username field
    And User enters "<password>" in Password field
    Then Clicks on login button
    Then User is navigated to Home Page
    When User clicks on profile menu
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on Account
    Then User is navigated to profile screen
    When User clicks on Logout Button
    And User clicks on Yes on confirmation popup
    Then Verify user is logged out
    Then User clicks on Sign up Button
    Then User is navigated to Email Page
    And User clicks on Back button in the Email Page
    And User clicks on Forgot Password Button
    And Clicks on login button
    And User wants to identify the "Google+" image
    And User wants to identify the "QrCode" image

    Examples:
      | username | password    |
      | coe.gaian| Gaian@123   |
