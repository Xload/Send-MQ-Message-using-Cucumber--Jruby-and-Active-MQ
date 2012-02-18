Feature:Replace data in any payload with dynamic value from xls datasheet
 I want to replace static variables in payloads with dynamic values from datasheet  
 
 Scenario:Replace variable in payload with values from datasheet
 Given I have my payload "test.xml" and datasheet "testjava.xls"
 When I Select Replace row number 3     
 Then I should see  the payload before been Parametrized and after been replaced with param from row 3
 
   