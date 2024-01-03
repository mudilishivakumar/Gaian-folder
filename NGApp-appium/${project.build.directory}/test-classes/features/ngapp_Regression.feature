 #June 2021
 #Author : Mohan Mirpati
#
#
#Feature: Regression testing suite 
#
  #Scenario Outline: Home page navigation
    #Given As a user I want to open the NG Application
    #When User enters "<username>" in Username field
    #And User enters "<password>" in Password field
    #And Clicks on Login button
    #Then User is navigated to Towers screen
    #When User clicks on continue button in Towers screen
    #Then User is able to view profile
    #When user click on first video listed on the page
    #Then Video should be launched
    #And User have option to forward the movie by 10 seconds
    #And user should have the option to move forward and backward
    #When Video is streamed for more than 10 seconds
    #Then user should get backward option by 10 seconds
#
    #Examples: 
      #| username               | password  |
      #| coe@gaiansolutions.com | Gaian@123 |
      #| coe.gaian              | Gaian@123 |
  #
  #
  #Scenario Outline: Delete existing Profile
    #Given As a user I want to open the NG Application
    #When User enters "<username>" in Username field
    #And User enters "<password>" in Password field
    #And Clicks on Login button
    #Then User is navigated to Towers screen
    #When User clicks on Profile button
    #Then Profile screen is displayed
    #When user clicks on Profile icon
    #Then Edit, Delete and Add New options are displayed
    #When user clicks on Delete icon
    #Then Profile is deleted
#
    #Examples: 
      #| username               | password  |
      #| coe@gaiansolutions.com | Gaian@123 |
      #| coe.gaian              | Gaian@123 |
  #
  #Scenario Outline: Update profile details
    #Given As a user I want to open the NG Application
    #When User enters "<username>" in Username field
    #And User enters "<password>" in Password field
    #And Clicks on Login button
    #Then User is navigated to Towers screen
    #When User clicks on Profile button
    #Then Profile screen is displayed
    #When user clicks on Profile icon
    #When user click on edit profile icon
    #Then Profile details are opened in edit mode
    #When user updates the details
    #And clicks on Save button
    #Then profile are updated
#
    #Examples: 
      #| username               | password  |
      #| coe@gaiansolutions.com | Gaian@123 |
      #| coe.gaian              | Gaian@123 | 
