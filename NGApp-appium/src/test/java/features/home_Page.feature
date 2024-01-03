Feature: NGApp Home Page Automation

  @Regression @HomePage
  Scenario Outline: Verifying the Channel Functionalities in Home Page
    Given As a user I want to open the NG Application
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen
    When User clicks on continue button in Towers screen
    Then User selects his profile after tower screen
    Then User is able to see WalkThrough feature of the App
    And User clicks on "skip" button in the WalkThrough
    Then User is able to see the List of videos available
    Then User opens "MAGENTISM" video in the homepage
    Then User clicks system back button
    Then User opens "MATTER" video in the homepage
    Then User clicks system back button
    And User opens "LIQUID FORCES" video in the homepage
    Then User clicks system back button
    And User opens "ROCKETS" video in the homepage
    Then User clicks system back button
    And User opens "KINETIC ENERGY" video in the homepage
    Then User clicks system back button
    Then User is able to see Home, NextGenApps, Live Channels, VOD, Account

    Examples:
      | username       | password    |
      | ksaketh        | Gaian@123# |