@LoginPage @Regression
Feature: NG App Login Page automation
  @UI
  Scenario: Verify the UI in login page
    Given As a user I want to open the NG Application
    Then User is able to see all the Logos, Textboxes,Buttons etc..

  Scenario Outline: Validation of Login using valid username and valid password
    Given As a user I want to open the NG Application
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen

    Examples:
      | username | password    |
      | ksaketh  | Gaian@123#  |

  Scenario Outline: Validation of Login using using valid username and invalid password
  Given As a user I want to open the NG Application
  When User enters "<username>" in Username field
  And User enters "<password>" in Password field
  And Clicks on login button
  Then User is successfully redirected to Login Page

  Examples:
  | username | password    |
  | ksaketh  | Gaian@123   |

  Scenario Outline: Validation of Login using invalid username and valid password
  Given As a user I want to open the NG Application
  When User enters "<username>" in Username field
  And User enters "<password>" in Password field
  And Clicks on login button
  Then User is successfully redirected to Login Page

  Examples:
  | username    | password    |
  | ksaketh901  | Gaian@123#   |

  @Functionality
  Scenario: Verify the functionality of Forgot Password page when the given email is Invalid
    Given As a user I want to open the NG Application
    When User clicks on the Forgot Password Link on the Login Page
    Then User is successfully redirected to "Screen1" of Reset Password Page
  @Functionality
  Scenario: Verify if the user is able to navigate to SignupPage from Login
    Given As a user I want to open the NG Application
    When User clicks on Signup on the Login Page
    Then User is able to see the Progress bar, Next/Back buttons etc.