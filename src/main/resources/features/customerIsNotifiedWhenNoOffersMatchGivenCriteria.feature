Feature: Customer is notified when no offers match given criteria

  Scenario: Search for a job matching given criteria
    Given Customer is on 'http://akamaijobs.referrals.selectminds.com/'
    When Customer specifies job title : XXX
    And clicks on 'Search'
    Then Notification about no offers found is displayed