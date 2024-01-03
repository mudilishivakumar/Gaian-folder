Feature: NextGen app Automation
  @Test
  Scenario Outline:  Verify the usability and user interface of Next Gen Apps
    Given As a user I want to open the NGTV
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Home Page
    When User clicks on profile menu
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
#    When User clicks on NextGenApps
#    Then User is able to see list of videos under Live Now, Trending
#    And User able to see Live now Text
#    When user clicks on "Quiz time" under Live Now
#    Then user clicks on "Responder" under Live Now
#    Then user clicks on "Emoticons" under Live Now
#    Then user clicks on "MB" under Live Now
#    And User clicks on device back button
    When User clicks on NextGenApps
    Then User is able to see list of videos under Live Now, Trending
    And user able to see Trending Text
    When User clicks on "Quiz time" under Trending
#    Then User navigates to particular page and clicks on launch button
#    And User clicks on device back button
    And User able to see the launch button
    And User clicks on device back button
    And user able to see Trending Text
    Then User clicks on "Responder" under Trending
#    Then User navigates to particular page and clicks on launch button
#    And User clicks on device back button
    And User able to see the launch button
    And User clicks on device back button
    And user able to see Trending Text
    Then User clicks on "Emoticons" under Trending
#    Then User navigates to particular page and clicks on launch button
#    And User clicks on device back button
    And User able to see the launch button
    And User clicks on device back button
    And user able to see Trending Text
    Then User clicks on "MB" under Trending
#    Then User navigates to particular page and clicks on launch button
#    And User clicks on device back button
    And User able to see the launch button
    And User clicks on device back button

    Examples:
      | username               | password  |
      | coe@gaiansolutions.com | Gaian@123 |