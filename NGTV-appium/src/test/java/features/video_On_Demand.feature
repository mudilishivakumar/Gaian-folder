Feature: Verify the functionalities of Video On Demand
  @test
  Scenario Outline: Verify the functionalities of VOD
    Given As a user I want to open the NGTV
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Home Page
    When User clicks on profile menu
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on VOD
    Then User is able to see videos under Continue to watch
    And User clicks on "Channel No-1" Channel
    And Resume button is displayed to play the video and Click
    When User clicks on device back button
    And User able to see the Resume button in channel page
    Then User able to see Seasons text
    And User clicks on "Season-1" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-2" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-3" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-4" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-5" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-6" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-7" in VoD page
    And User able see the videos updated or not
    When User clicks on device back button
    Then User is able to see videos under Continue to watch
    And User clicks on "Channel No-2" Channel
    And Resume button is displayed to play the video and Click
    When User clicks on device back button
    And User able to see the Resume button in channel page
    Then User able to see Seasons text
    And User clicks on "Season-1" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-2" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-3" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-4" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-5" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-6" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-7" in VoD page
    And User able see the videos updated or not
    When User clicks on device back button
    Then User is able to see videos under Continue to watch
    And User clicks on "Channel No-3" Channel
    And Resume button is displayed to play the video and Click
    When User clicks on device back button
    And User able to see the Resume button in channel page
    Then User able to see Seasons text
    And User clicks on "Season-1" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-2" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-3" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-4" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-5" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-6" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-7" in VoD page
    And User able see the videos updated or not
    When User clicks on device back button
    Then User is able to see videos under Continue to watch
    And User clicks on "Channel No-4" Channel
    And Resume button is displayed to play the video and Click
    When User clicks on device back button
    And User able to see the Resume button in channel page
    Then User able to see Seasons text
    And User clicks on "Season-1" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-2" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-3" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-4" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-5" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-6" in VoD page
    And User able see the videos updated or not
    And User clicks on "Season-7" in VoD page
    And User able see the videos updated or not
    When User clicks on device back button
    Then user able to see Top rated Text in VOD Page
    When User clicks on device back button
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account

    Examples:
      | username               | password  | type  |
      | coe@gaiansolutions.com | Gaian@123 | email |

