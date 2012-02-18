Feature:Send  message to a queue
  In order to add not have to use my head
  I want to send a text message to a queue
 
  Scenario: Send A message to a queue
    Given I have a MQ Client
  	When I send a 1 text message to the remote queue name "foo.bar" and using the message "/Users/jadsalfiti/TestFiles/test.xml"
    Then I should see 1 text message to the remote queue name "foo.bar"
    And the message Details Sender must be "The Name"
    And Company employee firstname is "<firstname>Tom</firstname>"
    And Company employee lastname is "Enderson"  
    