@VODPage @Regression
Feature: NGApp Features for VOD Page
  Background:
    Given As a user I want to open the NG Application
    When User enters "ksaketh" in Username field
    And User enters "Gaian@123#" in Password field
    And Clicks on login button
    Then User is navigated to Towers screen
    When User clicks on continue button in Towers screen
    Then User selects his profile after tower screen
#    Then User is able to see WalkThrough feature of the App
#    And User clicks on "skip" button in the WalkThrough
    And User clicks on VOD page

  @sanity @UI
  Scenario: Verify if the user can swipe the corousel
    When User is able to see videos under Releases in 2019 , Top rated
    And User is able to swipe the "Releasesin2019" section
    And User is able to swipe the "ContinueToWatch" section
    And User is able to swipe the "TopRated" section

#  @sanity @functionality
#  Scenario: Verify if the user is able to bookmark a video
#    When User clicks on a video in "ReleasesIn2019" in VOD Page
#    Then User marks the video as Bookmark
#    Then User clicks on VOD back button
#    And User clicks on profile menu
#    Then User clicks on "MyList" in profile page
#    And User is able to see the video added in the bookmark