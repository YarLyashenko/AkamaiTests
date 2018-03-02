Feature: Unlogged customer is able to search for a job

  Scenario: Search for a job matching given criteria
    Given Customer is on 'http://akamaijobs.referrals.selectminds.com/'
    When Customer specifies job title : Test
    And Location : Krakow, Poland
    And clicks on 'Search'
    Then Any job offers are found