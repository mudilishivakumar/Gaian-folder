#December 2021
#Author : Saketh Sai kocherla
Feature: NGApp Build Verification Test
  @BVT @Regression
  Scenario Outline: Regression TestCases for NGApp
    Given As a user I want to open the NG Application
    When User clicks on Signup on the Login Page
    Then User is able to see the Progress bar, Next/Back buttons etc.
    Then User enters "<email>" in email field of Signup page
    When User clicks on next button in Signup page
    Then User is able to see the Progress bar, Next/Back buttons etc.
    Then User enters "<otp>" in the otp field of "Signup" Page
    When User clicks on next button in Signup page
    When User enters "<username>" as "Username" on screen three of Signup Page
    When User enters "<password>" as "Password" on screen three of Signup Page
    When User enters "<confirmPassword>" as "Confirm Password" on screen three of Signup Page
    When User clicks on next button in Signup page
    When User is able to see the payment options like creditcard, phonepay, payu etc., in payment page
    Then User clicks on "CreditCard" in the payment page
    Then User enters "<cardno>" in "Card Number" field
    Then User enters "<expiry>" in "Expiry" field
    Then User enters "<name>" in "Card Holders Name" field
    Then User enters "<cvv>" in "CVV" field
    Then User clicks on pay now button
    Then Verify whether user signed up successfully
    When User enters "<username>" in Username field
    And User enters "<newpassword>" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen
    Then User is able to search bar, favorites, go button on tower screen
    When User clicks on continue button in Towers screen
    Then User selects his profile after tower screen
    Then User is able to see WalkThrough feature of the App
    And User clicks on "skip" button in the WalkThrough
    Then User is able to see Home, NextGenApps, Live Channels, VOD, Account
    And User is able to see the List of videos available
    Then User opens "MATTER" video in the homepage
    Then Check whether the Play, Pause etc. are visible
    Then User is able to see the video in "HomePage" page
    Then User clicks system back button
    When User clicks on LiveChannels
    Then User is able to see available channels
    And User is able to see today date
    #And User is able to see search button
    When User clicks on a video on Livechannel
    Then Check whether the Play, Pause etc. are visible
    Then User is able to see the video in "LiveChannel" page
    Then User clicks system back button
    When User clicks on NextGenApps
    Then User is able to see Trending Apps, Curious Crew sections
    When User clicks on VOD page
    Then User is able to see videos under Releases in 2019 , Top rated
    And User is able to swipe the "TopRated" section
    And User is able to swipe the "Releasesin2019" section
    When User clicks on a video on vod
    Then User taps on "NGS" in VOD Page
    Then User taps on "Episodes" in VOD Page
    Then User is able to see the seasons DropDown and click on it
    And User selects Season name as "Curious Crew Season2"
    Then User is able to see "Bridges" video thumbnail
    Then User taps on "About" in VOD Page
    And User clicks system back button
    When User clicks on a video on vod
    Then User clicks on Write A Review
    And User gives rating of "5" to the episode
    Then User writes his review as "<userReview>" on User Review Page
    And User clicks on submit button on User Review Page
    And User taps on star rating
    Then User is able to see entered "<userReview>" Review
    Then User clicks system back button
    And User clicks on resume/play Button on the selected video
    Then Check whether the Play, Pause etc. are visible
    Then User is able to see the video in "VOD" page
    Then User clicks system back button
    Then User clicks system back button
    And User clicks on profile menu
    And User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is on create New Avatar page
    Then User enters "<newusername>" as "Username" in create new avatar
    Then User clicks on Save button to save his New Profile Details
    And User clicks on his name in Profile Page
    Then User is "able" to see his "<newusername>" Profile Created
    Then User tries to delete all the created sub-profiles
    And User clicks on his name in Profile Page
    Then User is "unable" to see his "<newusername>" Profile Created
    And User clicks on his name in Profile Page
    And User is able to see devices, username, logout, version, settings
    Then User clicks on ProfileSection to edit profile details
    And User updates "<firstname>", "<lastname>", "<age>", "<address>" etc.
    And User is able to see devices, username, logout, version, settings
    And   User clicks on settings
    Then  User is in settings page
    Then  User able to identify the "Notifications" in settings page
    And   User able to enable and disable the "notifications" toggle button
    Then  User able to identify the "StreamingQuality" in settings page
    Then  User able to identify the "Quality" in settings page
    Then  User clicks on "Auto" streaming quality
    Then  User able to identify the "Quality" in settings page
    Then  User clicks on "Medium" streaming quality
    Then  User able to identify the "Quality" in settings page
    Then  User clicks on "Low" streaming quality
    Then  User able to identify the "Quality" in settings page
    Then  User clicks on "High" streaming quality
    Then  User able to identify the "Language" in settings page
    Then  User able to identify the "LanguageList" in settings page
    Then  User able to identify the "Remainder" in settings page
    And  User able to enable and disable the "Email" toggle button
    And  User able to enable and disable the "SMS" toggle button
    Then User clicks on save button
    And User is able to see devices, username, logout, version, settings
    And User clicks on Logout Button
    And user clicks on Yes on confirmation popup
    Then Verify user is logged out
    When User clicks on the Forgot Password Link on the Login Page
    Then User is successfully redirected to "Screen1" of Reset Password Page
    And User enters "<email>" in the Reset Password Page
    When User clicks on "Submit" button in the Reset Password Page
    Then User is successfully redirected to "Screen2" of Reset Password Page
    Then User enters "<otp>" in the otp field of "Reset Password" Page
    And User enters "<newpassword>" as password in the Reset Password Page
    Then User clicks on "Verify" button in the Reset Password Page
    Then User is successfully redirected to Login Page
    When User enters "<username>" in Username field
    And User enters "<newpassword>" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen

    Examples:
    |email  |otp   |username|password  |confirmPassword |cardno |cvv   |name    |expiry| newpassword| newusername|userReview       |firstname |lastname |age  |address|
    |RANDOM |100000|RANDOM  |RANDOM    |RANDOM          |RANDOM|RANDOM |RANDOM  |RANDOM| RANDOM     |RANDOM      |AUTOMATED REVIEW |RANDOM    |RANDOM   |25   |India  |