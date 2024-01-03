Feature: NgTv Home page Automation

  Scenario Outline:Verify the functionality of channels under Mobius Recommends
    Given As a user I want to open the NGTV
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Home Page
    And User is able to see the List of videos available
    When User clicks on the "WKAR NewsTalk" channel under Mobius Recommends
    Then Video player opens and video is not playing
    When User clicks on device back button
    Then User is navigated to Home Page
    When User clicks on the "CURIOUS CREW" channel under Mobius Recommends
    Then Video player opens and video is not playing
    When User clicks on device back button
    Then User is navigated to Home Page
    When User clicks on the "WKAR" channel under Mobius Recommends
    Then Video player opens and video is not playing
    When User clicks on device back button
    Then User is navigated to Home Page
    When User clicks on the "WKAR FM" channel under Mobius Recommends
    Then Video player opens and video is not playing
    When User clicks on device back button
    Then User is navigated to Home Page
    When User clicks on the "WKAR Classical" channel under Mobius Recommends
    Then Video player opens and video is not playing
    When User clicks on device back button
    Then User is navigated to Home Page
    When User clicks on the "WKAR NewsTalk1" channel under Mobius Recommends
    Then Video player opens and video is not playing
    When User clicks on device back button
    Then User is navigated to Home Page
    When User clicks on the "WKAR JAZZ" channel under Mobius Recommends
    Then Video player opens and video is not playing
    When User clicks on device back button
    Then User is navigated to Home Page
    When User clicks on the "WKAR Radio Reading Service" channel under Mobius Recommends
    Then Video player opens and video is not playing
    When User clicks on device back button
    Then User is navigated to Home Page

    Examples:
      | username               | password  | type  |
      | coe@gaiansolutions.com | Gaian@123 | email |
