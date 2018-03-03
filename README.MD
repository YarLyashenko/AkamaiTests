# Test project for Akamai

### How to run tests: 

Run tests in Chrome on LINUX with installed chromedriver:
`mvn install allure:serve`

To run on different browsers (example for Chrome):
`mvn install allure:serve -Dwebdriver.chrome.driver=<link to chromedriver executable> -DbrowserName=chrome`

````
Feature: Customer is notified when no offers match given criteria

  Scenario: Search for a job matching given criteria
    Given Customer is on 'http://akamaijobs.referrals.selectminds.com/'
    When Customer specifies job title : XXX
    And clicks on 'Search'
    Then Notification about no offers found is displayed

Feature: Unlogged customer is able to search for a job

  Scenario: Search for a job matching given criteria
    Given Customer is on 'http://akamaijobs.referrals.selectminds.com/'
    When Customer specifies job title : Test
    And Location : Krakow, Poland
    And clicks on 'Search'
    Then Any job offers are found
