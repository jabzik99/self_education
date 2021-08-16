Feature: Level1

  Scenario: TC - 1. Basic Authorization
    When I navigate to the Basic Authorization page
    Then Main page is opened
    When I navigate to the site by modifying URL address
    Then 'Congratulations! You must have the proper credentials.' message is displayed on the page

#  Scenario: TC - 2.1. Alerts
#    When I navigate to the main page
#    Then Main page is opened
#    When I click 'Click for JS Alert' alert button
#    Then Alert with next text is displayed
#    When I click 'OK' button
#    And  Alert is closed
#    Then Next message is displayed in 'Result' section
#    When I click 'Click for JS Confirm' button
#    Then Alert with next text is displayed
#         |I am a JS Confirm|
#    When I click 'OK' button
#    And Alert is closed
#    Then Next message is displayed in 'Result' section
#         |You clicked: Ok|
#    When I click 'Click for JS Prompt' button
#    Then Alert with next text is displayed
#         |I am a JS prompt|
#    When I fill field in alert with 'random' value
#    And  I click 'OK' button
#    And  Alert is closed

