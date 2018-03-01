#Test project for Akamai

###How to run tests: 
`mvn install allure:serve`
 
 -----------------------------------------------------------------
**Feature: Unlogged customer is able to search for a job**

Scenario: _Search for a job matching given criteria_
Given _Customer is on ' http://akamaijobs.referrals.selectminds.com/_ 
When _Customer specifies job title : Test_
And _Location : Krakow, Poland_
And _clicks on 'Search'_
Then _Any job offers are found_
 
 -----------------------------------------------------------------
**Feature: Customer is notified when no offers match given criteria** 

Scenario: _Search for a job matching given criteria_
Given _Customer is on ' http://akamaijobs.referrals.selectminds.com/ '_ 
When _Customer specifies job title : XXX_
And _clicks on 'Search'_
Then _Notification about no offers found is displayed_
