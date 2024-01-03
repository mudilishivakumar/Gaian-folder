#April 2021
#Author : Rahul Upadhyay
Feature: Next Gen Android Application Build Sanity Suite

  Scenario Outline: Validation of Login using "<type>"
    Given As a user I want to open the NG Application
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen
    When User clicks on continue button in Towers screen
    #Then User is able to view profile
    #When User clicks on profile
    And User clicks on profile menu
    And User clicks on Logout Button
    And user clicks on Yes on confirmation popup
    Then Verify user is logged out

    Examples: 
      | username               | password  | type     |  |
      | coe@gaiansolutions.com | Gaian@123 | email    |  |
      | coe.gaian              | Gaian@123 | username |  |

  Scenario Outline: General App Navigation, Logout Functionality
    Given As a user I want to open the NG Application
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen
    When User clicks on continue button in Towers screen
    #Then User is able to view profile
    #When User clicks on profile
    And User is able to see the List of videos available
    When User clicks on a video on home page
    And User click on back button on player screen
    Then User is able to see the List of videos available
    Then User is able to see Home, NextGenApps, Live Channels, VOD, Account
    When User clicks on LiveChannels
    Then User is able to see available channels
    And User is able to see today date
    And User is able to see search button
    And User clicks on profile menu
    And User is able to see devices, username, logout, version, settings
    And User clicks on Logout Button
    And user clicks on Yes on confirmation popup
    Then Verify user is logged out
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen
    When User clicks on continue button in Towers screen
    #Then User is able to view profile
    #When User clicks on profile
    When User clicks on NextGenApps
    Then User is able to see Trending Apps, Curious Crew sections
    And User is able to open any Trending App

    Examples: 
      | username               | password  |
      | coe@gaiansolutions.com | Gaian@123 |
      | coe.gaian              | Gaian@123 |

  Scenario Outline: Validation of LiveChannels, VOD, NextGenApps
    Given As a user I want to open the NG Application
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Given User is navigated to Towers screen
    When User clicks on continue button in Towers screen
    #Then User is able to view profile
    #When User clicks on profile
    And User clicks on profile menu
    When User clicks on LiveChannels
    Then User is able to see available channels
    And User is able to see today date
    And User is able to see search button
     When User clicks on a video on Livechannel
    Then Back button is displayed on the player screen in live channel
    When User clicks on VOD
    Then User is able to see videos under Releases in 2019 , Top rated
    When User clicks on a video
    Then Play button is displayed to play the video
    And Back button is displayed
    When user clicks on a video from NextGenApps screen
    Then Back button is displayed on the player screen in NextGenApps
    

    Examples: 
      | username               | password  |
      | coe@gaiansolutions.com | Gaian@123 |
