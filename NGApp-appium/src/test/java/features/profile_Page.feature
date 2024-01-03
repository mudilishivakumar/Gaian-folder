Feature: Test Scenarios For Profile Page in NGApp
  Background:
    Given As a user I want to open the NG Application
    When User enters "ksaketh" in Username field
    And User enters "Gaian@123#" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen
    When User clicks on continue button in Towers screen
   # Then User is able to see WalkThrough feature of the App
   # And User clicks on "skip" button in the WalkThrough
    And User clicks on profile menu

  @Regression
  Scenario: Check whether Sub-Profile is getting created
    When User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is on create New Avatar page
    Then User enters "SDET1" as "Username" in create new avatar
    Then User enters "Gaians" as "FirstName" in create new avatar
    Then User enters "Engineer" as "LastName" in create new avatar
    Then User enters "Female" as "Gender" in create new avatar
    Then User enters "Age" as "Age" in create new avatar
    Then User enters "School" as "School" in create new avatar
    Then User enters "3" as "Grade" in create new avatar
    Then User enters "IST" as "Timezone" in create new avatar
    Then User clicks on Save button to save his New Profile Details
    And User clicks on his name in Profile Page
    Then User is "able" to see his "<username>" Profile Created

  @Regression @functionality
  Scenario: Verify if newly created User is getting deleted
    When User clicks on his name in Profile Page
    Then User tries to delete all the created sub-profiles
    And User clicks on his name in Profile Page
    Then User is "unable" to see his "SDET1" Profile Created

  @Regression @functionality
  Scenario: Verify that user is unable to create more than three Sub-Profiles
    When User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is on create New Avatar page
    When User enters "SDET1" as "Username" in create new avatar
    And User clicks on Save button to save his New Profile Details
    When User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is on create New Avatar page
    When User enters "SDET2" as "Username" in create new avatar
    And User clicks on Save button to save his New Profile Details
    When User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is on create New Avatar page
    When User enters "SDET3" as "Username" in create new avatar
    And User clicks on Save button to save his New Profile Details
    And User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is Not on create New Avatar page

  @Regression @functionality
  Scenario: Verify that user is able to delete all the created Sub- Profiles
    When User clicks on his name in Profile Page
    Then User tries to delete all the created sub-profiles
    And User clicks on his name in Profile Page
    Then User is unable to see any created sub profiles

  @Regressions @functionality
  Scenario Outline: Verify that actual profile username and newly created profile username are not same
    And User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is on create New Avatar page
    Then User enters "<username>" as "Username" in create new avatar
    Then User clicks on Save button to save his New Profile Details
    And User is able to see devices, username, logout, version, settings

    Examples:
      | username|
      | ksaketh |

  @sanity @functionality
  Scenario Outline: No two users can be created by using the same user name in sub profile
    And User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is on create New Avatar page
    Then User enters "<newusername>" as "Username" in create new avatar
    Then User clicks on Save button to save his New Profile Details
    And User is able to see devices, username, logout, version, settings
    And User clicks on his name in Profile Page
    Then User is able to see Add new and clicks on it
    And User is on create New Avatar page
    Then User enters "<newusername>" as "Username" in create new avatar
    Then User clicks on Save button to save his New Profile Details
    And User is able to see devices, username, logout, version, settings

    Examples:
      | newusername     |
      |  Android        |
  @smoke @UI
  Scenario: Verify that the settings UI is working properly
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





