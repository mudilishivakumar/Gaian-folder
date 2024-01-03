#June 2021
##Author : Suraj Krishnamoorthy
@Test @Sanity
Feature: NgTV Android Application Build Sanity Suite

#    Scenario Outline: Trigger BA request using <type>
#      Given As a user I want to open the NGTV
#      When User enters "<username>" in Username field
#      And User enters "<password>" in Password field
#      And Clicks on login button
#      Then User is navigated to Home Page
#      When User triggers a BA call
#      Then API call succeeds
#
#    Examples:
#      | username               | password  | type     |
#      | coe@gaiansolutions.com | Gaian@123 | email    |
#      | coe.gaian              | Gaian@123 | username |
#      | FROM DB                | FROM DB   | username |

#  Scenario Outline: Trigger API request using <type>
#    Given As a user I want to open the NGTV
#    When User enters "<username>" in Username field
#    And User enters "<password>" in Password field
#    And Clicks on login button
#    Then User is navigated to Home Page
#    When User triggers an API call
#    Then API call succeeds
#
#    Examples:
#      | username               | password  | type     |
#      | coe@gaiansolutions.com | Gaian@123 | email    |
#      | coe.gaian              | Gaian@123 | username |

#  Scenario Outline: Validation of Login using <type>
#    Given As a user I want to open the NGTV
#    When User enters "<username>" in Username field
#    And User enters "<password>" in Password field
#    And Clicks on login button
#    Then User is navigated to Home Page after "Login"
#    When User clicks on profile menu
#    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
#    When User clicks on Account
#    Then User is navigated to profile screen
#    When User clicks on Logout Button
#    And User clicks on Yes on confirmation popup
#    Then Verify user is logged out
#
#    Examples:
#      | username               | password  | type     |
#      | coe@gaiansolutions.com | Gaian@123 | email    |
#      | FROM DB                | FROM DB   | username |

#  Scenario Outline: Checking whether any video that is not playing is being detected or not using <type>
#
#    Given As a user I want to open the NGTV
#    When User enters "<username>" in Username field
#    And User enters "<password>" in Password field
#    And Clicks on login button
#    Then User is navigated to Home Page after "Login"
#    When User clicks on profile menu
#    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
#    When User clicks on VOD
#    Then User is able to see videos under Releases in 2019, Top rated
#    And User is able to see search button
#    When User clicks on the third video thumbnail from Top Rated section on VoD Page
#    Then Video Description Page is displayed
#    And Play button is displayed to play the video
#    When User clicks play button
#    Then Video player opens up in "VoD" and video is not playing
#    When User clicks on device back button
#    Then Video Description Page is displayed
#    When User clicks on device back button
#    Then User is able to see videos under Releases in 2019, Top rated
#    When User clicks on device back button
#    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
#    When User clicks on Account
#    Then User is navigated to profile screen
#    And User is able to see Paired Devices, Location, My List, Settings, Profile, Download, Logout
#    When User clicks on Logout Button
#    And User clicks on Yes on confirmation popup
#    Then Verify user is logged out
#
#    Examples:
#      | username               | password  | type     |
#      | coe@gaiansolutions.com | Gaian@123 | email    |
#      | coe.gaian              | Gaian@123 | username |

  Scenario Outline: General App Navigation, Logout Functionality using <type>

    Given As a user I want to open the NGTV
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Home Page
    And User is able to see the List of videos available
    When User clicks on LiveChannel Menu
    Then User is able to see available channels
    When User clicks on profile menu
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on NextGenApps
    Then User is able to see list of videos under Live Now, Trending
    When User clicks on device back button
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on Live TV Icon
    Then User is able to see available channels
    When User clicks on profile menu
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on VOD
    Then User is able to see videos under Releases in 2019, Top rated
    When User clicks on device back button
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on Account
    Then User is navigated to profile screen
    When User clicks on Logout Button
    And User clicks on Yes on confirmation popup
    Then Verify user is logged out

    Examples:
      | username | password | type     |
      | FROM DB  | FROM DB  | username |

  Scenario Outline: Validation of LiveChannels, VOD, NextGenApps using <type>

    Given As a user I want to open the NGTV
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Home Page
    When User clicks on profile menu
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on NextGenApps
    Then User is able to see list of videos under Live Now, Trending
    When User clicks on device back button
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on Live TV Icon
    Then User is able to see available channels
#    When User clicks on a channel thumbnail on LiveChannels
#    Then Video player opens up
#    And Video is playing
#    When User clicks on device back button
#    Then User is able to see available channels
    When User clicks on profile menu
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on VOD
    Then User is able to see videos under Releases in 2019, Top rated
    And User is able to see search button
    When User clicks on a video thumbnail from VoD Page
    Then Video Description Page is displayed
    And Play button is displayed to play the video
    When User clicks play button
    Then Video player opens up in "VoD" and video is playing
    When User clicks on device back button
    Then Video Description Page is displayed
    When User clicks on device back button
    Then User is able to see videos under Releases in 2019, Top rated
    When User clicks on device back button
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on Account
    Then User is navigated to profile screen
    And User is able to see Paired Devices, Location, My List, Settings, Profile, Download, Logout
    When User clicks on Logout Button
    And User clicks on Yes on confirmation popup
    Then Verify user is logged out

    Examples:
      | username | password | type     |
      | FROM DB  | FROM DB  | username |

  Scenario Outline: Checking user profile details can be updated using <type>

    Given As a user I want to open the NGTV
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Home Page
    When User clicks on profile menu
    Then User is able to see Search, NextGenApps, Live TV, VOD, Account
    When User clicks on Account
    Then User is navigated to profile screen
    And User is able to see Paired Devices, Location, My List, Settings, Profile, Download, Logout
    When User clicks on Profile Button
    Then User is navigated to profile details screen
    When User enters "<firstName>" in First Name field
    And User enters "<lastName>" in Last Name field
    And User enters "<Age>" in Age field
    And User clicks on Save Button
    Then User is navigated to profile screen
    When User clicks on Profile Button
    Then User is navigated to profile details screen
    And User sees the value updated to "<firstName>" in "First Name" field
    And User sees the value updated to "<lastName>" in "Last Name" field
    And User sees the value updated to "<Age>" in "Age" field
    When User clicks on Save Button
    Then User is navigated to profile screen
    When User clicks on Logout Button
    And User clicks on Yes on confirmation popup
    Then Verify user is logged out

    Examples:
      | username | password | firstName | lastName | Age    | type     |
      | FROM DB  | FROM DB  | RANDOM    | RANDOM   | RANDOM | username |

  Scenario Outline: Verify Sign up process
    Given As a user I want to open the NGTV
    When User clicks on Sign up Button
    Then User is navigated to Email Page
    When User enters "<email>" as Sign up email
    And User clicks on Next button in the Email Page
    Then User is navigated to Verify OTP page
    When User enters "<otpValue>" as OTP in Sign Up
    And User clicks on Next button in the Verify OTP page
    Then User is navigated to Sign Up Details Page
    When User enters "<username>" as "Username" in Details Page
    And User enters "<password>" as "Password" in Details Page
    And User enters "<confirmPassword>" as "Confirm Password" in Details Page
    And User clicks on Sign Up button in the Sign Up Details page
    Then User is navigated to Payments Page
    When User clicks on "Credit Card" in Payments Page
    And User enters "<cardNumber>" as "Card Number" in Payments Page
    And User enters "<CardHolder>" as "Card Holder" in Payments Page
    And User enters "<expiry>" as "Expiry Date" in Payments Page
    And User enters "<cvv>" as "CVV" in Payments Page
    And User clicks on Pay Now button in the Payments Page
    Then User is navigated to Login Page after "Sign Up"
    When User enters "<username>" in Username field
    And User enters "<password>" in Password field
    And Clicks on login button
    Then User is navigated to Home Page

    Examples:
      | email  | otpValue | username | password | confirmPassword | cardNumber | CardHolder | expiry | cvv    |
      | RANDOM | 100000   | RANDOM   | RANDOM   | RANDOM          | RANDOM     | RANDOM     | RANDOM | RANDOM |

    @focus
  Scenario Outline: Verify Forgot Password process
    Given As a user I want to open the NGTV
    When User clicks on Forgot Password Button
    Then User sees the prompt to enter email
    When User enters "<email>" as Reset Password email
    And User clicks on Submit in Reset Password page
    Then User sees the prompt for OTP and New Password
    When User enters "<otpValue>" as OTP in Reset Password page
    And User enters "<newPassword>" in Reset Password page
    And User clicks on Verify Button in the Reset Password page
    Then User is navigated to Login Page after "Forgot Password"
    When User enters "<username>" in Username field
    And User enters "<newPassword>" in Password field
    And Clicks on login button
    Then User is navigated to Home Page

    Examples:
      | email   | otpValue | newPassword | username |
      | FROM DB | 100000   | RANDOM      | FROM DB  |