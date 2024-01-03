Feature: NgTv Forgot Password page Automation

  Scenario Outline: Validation of Forgot password using <type>
    Given As a user I want to open the NGTV
    When User clicks on Forgot Password Button
    Then User sees the prompt to enter email
    When User enters "<email>" as Reset Password email
    And User clicks on Submit in Reset Password page
    Then User gets a toast message in "Forgot password"
    Examples:
      | email             |
      | abc.xyz@gmail.com |