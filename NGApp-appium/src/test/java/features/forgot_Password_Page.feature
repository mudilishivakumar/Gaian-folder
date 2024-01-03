@ForgotPasswordPassword @Regression
Feature:NG App forgot password page Automation
  Scenario Outline: Verify the functionality of Forgot Password page when the given email is Invalid
    Given As a user I want to open the NG Application
    When User clicks on the Forgot Password Link on the Login Page
    Then User is successfully redirected to "Screen1" of Reset Password Page
    And User enters "<email>" in the Reset Password Page
    When User clicks on "Submit" button in the Reset Password Page
    Then User is successfully redirected to "Screen1" of Reset Password Page

    Examples:
      | email            |
      | sonyss@gmail.com |