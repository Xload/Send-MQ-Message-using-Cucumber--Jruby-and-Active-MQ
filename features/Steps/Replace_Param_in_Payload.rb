require 'rspec'
require 'java'
require "/usr/local/Cellar/activemq/5.5.1/libexec/activemq-all-5.5.1.jar"
require "/usr/local/Cellar/activemq/5.5.1/libexec/lib/optional/slf4j-log4j12-1.5.11.jar"
require "/usr/local/Cellar/activemq/5.5.1/libexec/lib/optional/log4j-1.2.14.jar"
require "/poi-3.7/poi-scratchpad-3.7-20101029.jar"
require "/poi-3.7/poi-3.7-20101029.jar"
require "/poi-3.7/poi-ooxml-schemas-3.7-20101029.jar"
require "/poi-3.7/poi-examples-3.7-20101029.jar"
require "/poi-3.7/poi-ooxml-3.7-20101029.jar"
$CLASSPATH << File.expand_path('.') + "/bin/"
include_class Java::com.jms.utility.ReadFromFile
include_class Java::com.jms.utility.SearchAndFind
include_class Java::com.jms.utility.ReadDataFromDataSheet

Given /^I have my payload "([^"]*)" and datasheet "([^"]*)"$/ do |Payload, datasheet|

@sheet=ReadDataFromDataSheet.new
@File=ReadFromFile.new
@Search=SearchAndFind.new
@sheet.Set_Workbook(datasheet)
@File.readFileAsStringUsingByte(Payload)
 end
When /^I Select Replace row number (\d+)$/ do |row|
 TEMPXML=@File.readFileAsStringUsingByte(Payload)
DATASHEET=@sheet.ReadSpecificRow(row.to_i)
RES=@Search.Parametrize_Data_From_File(TEMPXML, DATASHEET)
end
Then /^I should see  the payload before been Parametrized and after been replaced with param from row (\d+)$/ do |row|
puts("before")
puts(TEMPXML)
puts("After")
puts(RES)
end




