package com.jms.messaging;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.jms.messaging.PublishMessage;
import com.jms.messaging.Receiver;
import com.jms.utility.*;


@SuppressWarnings("unused")
public class PublishMessagetest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Properties env = new Properties( );
		env.put(Context.SECURITY_PRINCIPAL, "guest");  
		env.put(Context.SECURITY_CREDENTIALS, "guest");
	//	env.put(Context.INITIAL_CONTEXT_FACTORY,
		//    "<kbd class=userinput>com.sun.jndi.ldap.LdapCtxFactory</kbd>");
	//	env.put(Context.PROVIDER_URL,
		//     "<kbd class=userinput>ldap://localhost:389/o=acme.com</kbd>");
		
		env.put(Context.PROVIDER_URL,
			     "tcp://localhost:61616");
		 
		InitialContext jndi = new InitialContext(env);
		TopicConnectionFactory conFactory = (TopicConnectionFactory)jndi.lookup("TopicConnectionFactory");
		MessageTopicSubscriber  MessageSubscriber = new MessageTopicSubscriber ("ActiveMQ.Advisory.Consumer.Queue.foo.bar","jadsalfiti","password");
		Message message = null;
		
		for (int i=0;i<10;i++)
		{
		 MessageSubscriber.onMessage(message);
		}
		
		
		//MessageSubscriber.set(conFactory, subSess, pub, username)
		
		
		//MessageSubscriber.set(con, subSess, pub, username)
		
		// ReadDataFromDataSheet sheettest = new ReadDataFromDataSheet();
	//	 ReadFromFile file=new ReadFromFile();
		 
	//	 String tempfile= file.readFileAsStringUsingByte("/Users/jadsalfiti/TestFiles/test.xml");
	//	 sheettest.Clear_vector();
	//	 sheettest.Set_Workbook("/Users/jadsalfiti/TestFiles/testjava.xls");
		 
		// System.out.println( tempfile);
	
	//for (int i=1;i<5;i++){
		 
	//HashMap temp=sheettest.ReadSpecificRow(1);
	
//	System.out.println(temp);
	
	///}
	
//	 sheettest.ReadLargeRowofData(0, 5);
		// HashMap temp=sheettest.ReadAPoolOfRaws(1,5);
///	sheettest.printCellDataToConsole(sheettest.ReadAPoolOfRaws(1,1));
	 
	// String test1111=tempfile.replace("${TEST1}", "33");
	 //System.out.println( test1111);
	
	/// str=temp.toArray().toString();
	 
///	System.out.println(temp.toString());
	 
	 
	 
	// SearchAndFind Find= new  SearchAndFind();
	 
	/// System.out.println(Find.RapBraketsAndDolorSign("TEST1"));
	 
	 
	// System.out.println(Find. Parametrize_Data_From_File(tempfile,temp));
	 
	 
	 
	 	
		
			
		
			
	 
	 
	 
	 
		 SearchAndFind find=new SearchAndFind();
		
		System.out.println( find.FindOccurrenceSubStringinString("helo world","helo"));
		System.out.println(  find.FindOccurrenceSubStringinString("helo world","jad"));
		 
		 
	//	sheettest.printCellDataToConsole(sheettest.ReadSpecificRow(0));
		 
		/// ReadDataFromDataSheet.printCellData(ReadDataFromDataSheet.ReadAllSpreadShseet("/Users/jadsalfiti/TestFiles/testjava.xls", 1, 0));
		 
	///	 ReadDataFromDataSheet.printCellData(ReadDataFromDataSheet.ReturnSpecRowdata(3,"/Users/jadsalfiti/TestFiles/testjava.xls"));
		
		
		
	PublishMessage mymessage=new PublishMessage() ;
	/// mymessage.Put(args[0],10,test);	
	Receiver receive=new Receiver();
	ReadFromFile file1=new ReadFromFile();
	
//	SearchAndFind find=new SearchAndFind();	
	//String test;
	//test=file1.ReadAFileToStringUsingChar("/Users/jadsalfiti/TestFiles/test.xml");
	//System.out.println(find.ReturnLocationOfSubinString(test,"Tom"));
	//receive.GetMessage(args[0], 1);
	//System.out.println("----------------");
	//System.out.println(receive.GetMessageTextData());
	
	
	//file1.GetFileType("/Users/jadsalfiti/TestFiles/test.csv");
	String test=null;
	test=file1.ReadAFileToStringUsingChar("/Users/jadsalfiti/TestFiles/test.xml");
	
	  System.out.println ("test char " + test);
	  test=file1.readFileAsStringUsingByte("/Users/jadsalfiti/TestFiles/test.xml");
	  System.out.println ("test byte " + test);
	
	 mymessage.Put(args[0],1,test);
	 System.out.println(mymessage.ReturnSendStatus());
	//  boolean status=mymessage.GetConnectionStatus();
	  
	//	receive.GetMessage(args[0], 1);
		
		System.out.println(receive.GetMessageTextData());
		receive.GetReceiverMessageSenderName();
		
		//boolean stat=receive.GetConnectionStatus();
		receive.GetStatusOfMessage();
		String SenderName=receive.GetReceiverMessageSenderName();
		
		
		int location=find.ReturnLocationOfSubstringinString(SenderName, "jadsalfiti");
		
		System.out.println(find.FindSaveValue("", "", "jadsalfitimoin"));
		
		
	
		

		
		
		
	

	}

	
	
	
	
}//End of Class
