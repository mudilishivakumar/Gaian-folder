Feature: NgTv Signup page Automation

  Scenario Outline: Verify Sign up process
    Given As a user I want to open the NGTV
    When User clicks on Sign up Button
    Then User is navigated to Email Page
    Then User enters "<email>" as Sign up email
    And User clicks on Next button in the Email Page
    Then User gets a toast message in "signup page"

    Examples:
      | email                    |
      | ramyamahali888@gmail.com |

  Scenario:Verify the User Interface of signup page
    Given As a user I want to open the NGTV
    When User clicks on Sign up Button
    Then User wants to identify the "WKARng" image
    And User wants to identify the "MobiusTv" image
    Then User checks the UI of "signup page"

