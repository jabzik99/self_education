Feature: Level1

#  Scenario: TC - 1. Basic Authorization
#    When I navigate to the Basic Authorization page
#    Then Basic Authorization page is opened
#    When I navigate to the site by modifying URL address
#    Then 'Congratulations! You must have the proper credentials.' message is displayed on the page


#  Scenario: TC - 2.1 - 2.2 Alerts + JS
#    When I navigate to the JavaScript Alerts page
#    Then Alerts page is opened
#    When I click 'Click for JS Alert' button with JS functions
#    Then Alert with 'I am a JS Alert' text is displayed
#    When I accept alert
#    Then 'You successfully clicked an alert' message is displayed in 'Result' section
#    When I click 'Click for JS Confirm' button with JS functions
#    Then Alert with 'I am a JS Confirm' text is displayed
#    When I accept alert
#    Then 'You clicked: Ok' message is displayed in 'Result' section
#    When I click 'Click for JS Prompt' button with JS functions
#    Then Alert with 'I am a JS prompt' text is displayed
#    When I handle promt alert with random value and save it as 'random_value'
#    Then 'You entered:' message with 'random_value' is displayed in 'Result' section

#  Scenario: TC - 3. Actions
#    When I navigate to Horizontal Slider page
#    Then Horizontal Slider page is opened
#    When I set random value for slider except boundary values
#    Then Valid values is displayed near by slider

  Scenario: TC - 4. Hovers
    When I navigate to Hovers page
    Then Hovers page is opened
    When I hover the pointer for 'user1' user
      And User name for 'user1' user is displayed
    Then Profile link for 'user1' user is displayed
    When I click profile link for 'user1'
    Then Page for 'user1' is opened
    When I navigate to previous page
    Then Page with users is displayed
    When I hover the pointer for 'user2' user
      And User name for 'user2' user is displayed
    Then Profile link for 'user2' user is displayed
    When I click profile link for 'user2'
    Then Page for 'user2' is opened
    When I navigate to previous page
    Then Page with users is displayed
    When I hover the pointer for 'user3' user
      And User name for 'user3' user is displayed
    Then Profile link for 'user3' user is displayed
    When I click profile link for 'user3'
    Then Page for 'user3' is opened
    When I navigate to previous page
    Then Page with users is displayed







