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
include_class Java::com.jms.messaging.PublishMessage
include_class Java::com.jms.messaging.Receiver
include_class Java::com.jms.utility.ReadFromFile
include_class Java::com.jms.utility.SearchAndFind
Given /^I have a MQ Client$/ do

 @test=PublishMessage.new
 @Get=Receiver.new
 @File=ReadFromFile.new
 @Search=SearchAndFind.new	
end
When /^I send a (\d+) text message to the remote queue name "([^"]*)" and using the message "([^"]*)"$/ do |number, queue, message|

    messagedata=@File.readFileAsStringUsingByte(message.to_java)
  	@test.Put(queue.to_java ,number.to_i, messagedata.to_java)
    @test.ReturnSendStatus().should().to_java ==true
   puts("The Connection STATUS when putting a message ")
   puts(@test.ReturnSendStatus())
end
Then /^I should see (\d+) text message to the remote queue name "([^"]*)"$/ do  |number, queue|

	   message=@Get.GetMessage(queue.to_java ,number.to_i)
	   status=@Get.GetStatusOfMessage()
	   connection=@Get.GetConnectionStatus()
	   @Get.CheckMessageNotNull(@Get.GetMessageTextData()).should==true
	   puts("Message Text Data")
       puts(@Get.GetMessageTextData())
end
Then /^the message Details Sender must be "([^"]*)"$/ do |Sender|

      senderdetails=@Get.GetReceiverMessageSenderName()
      @Search.ReturnLocationOfSubstringinString(senderdetails,Sender).should > 0
      
end
Then /^Company employee firstname is "([^"]*)"$/ do |firstname|
    status=@Get.GetStatusOfMessage()
   String_temp=@Get.GetMessageTextData()
   i=@Search.ReturnLocationOfSubstringinString(String_temp.to_java,firstname.to_java).should > 0
   end
Then /^Company employee lastname is "([^"]*)"$/ do |company|
	text=@Get.GetMessageTextData()
	status=@Get.GetStatusOfMessage()
   @Search.ReturnLocationOfSubstringinString(text.to_java,company).should > 0
 	
 	end




