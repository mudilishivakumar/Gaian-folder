@Regression
Feature: NGApp Signup Page Automation
  Background:
    Given As a user I want to open the NG Application
    When User clicks on Signup on the Login Page
  @UI
  Scenario: verify the UI in signup page
    Then user checks the user interface in "signup page"
  @sanity
  Scenario Outline: Verify the Signup Page Functionality by giving already exisiting E-Mail ID
    When User enters "<email>" in email field of Signup page
    And User clicks on next button in Signup page
    Then User is able to see the Progress bar, Next/Back buttons etc.
    Examples:
      | email     |
      | FROM DB   |
  @sanity @functionality
  Scenario: Verify whether user is been creating by giving valid password and invalid confirm password
    When User enters "RANDOM" in email field of Signup page
    And User clicks on next button in Signup page
    Then User enters "100000" in the otp field of "Signup" Page
    And User clicks on next button in Signup page
    Then User enters "RANDOM" as "Username" on screen three of Signup Page
    Then User enters "RANDOM" as "Password" on screen three of Signup Page
    Then User enters "Gaian@123#" as "Confirm Password" on screen three of Signup Page
    And User clicks on next button in Signup page
    Then User receive a toast message
  @sanity @functionality
  Scenario Outline: Verify that user is unable to create his account using invalid credit card no
   When User enters "RANDOM" in email field of Signup page
   And User clicks on next button in Signup page
   Then User enters "100000" in the otp field of "Signup" Page
   And User clicks on next button in Signup page
   Then User enters "RANDOM" as "Username" on screen three of Signup Page
   Then User enters "RANDOM" as "Password" on screen three of Signup Page
   Then User enters "RANDOM" as "Confirm Password" on screen three of Signup Page
   And User clicks on next button in Signup page
   And User clicks on "CreditCard" in the payment page
   Then User enters "<cardno>" in "Card Number" field
   Then User enters "RANDOM" in "Expiry" field
   Then User enters "RANDOM" in "Card Holders Name" field
   Then User enters "RANDOM" in "CVV" field
   And User clicks on pay now button
   Then User is able to see the payment options like creditcard, phonepay, payu etc., in payment page
   Examples:
   |  cardno  |
   |  1111    |
  @sanity @functionality
  Scenario Outline: Verify that user is unable to create his account using invalid expiry date
    Then User enters "RANDOM" in email field of Signup page
    When User clicks on next button in Signup page
    Then User enters "100000" in the otp field of "Signup" Page
    When User clicks on next button in Signup page
    And User enters "RANDOM" as "Username" on screen three of Signup Page
    Then User enters "RANDOM" as "Password" on screen three of Signup Page
    Then User enters "RANDOM" as "Confirm Password" on screen three of Signup Page
    When User clicks on next button in Signup page
    Then User clicks on "CreditCard" in the payment page
    Then User enters "RANDOM" in "Card Number" field
    Then User enters "<expiryDate>" in "Expiry" field
    Then User enters "RANDOM" in "Card Holders Name" field
    Then User enters "RANDOM" in "CVV" field
    When User clicks on pay now button
    And User is able to see the payment options like creditcard, phonepay, payu etc., in payment page
    Examples:
      |  expiryDate  |
      |  55/12       |
