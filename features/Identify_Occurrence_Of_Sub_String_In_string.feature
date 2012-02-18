Feature:identify The total Number of SubString within a Any Giving String 
 
  I want to know how many time a sub string could be repeated within a string
 
  Scenario: Identify the Occurence of sub string
    Given I have a Long File of data
  	When I Search for the word "helo" within the phrase "helo world and helo people"
    Then I should see the phrase  "helo" was found  2 