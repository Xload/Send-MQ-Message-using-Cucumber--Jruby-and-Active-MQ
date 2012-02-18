package com.jms.messaging;



import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import com.jms.utility.ReadFromFile;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import org.slf4j.impl.Log4jLoggerAdapter;
///import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * A simple polymorphic JMS producer which can work with Queues or Topics which
 * uses JNDI to lookup the JMS connection factory and destination
 * 
 * 
 */


@SuppressWarnings("unused")
public class PublishMessage{

	public Hashtable env = new Hashtable();
	public Context jndiContext = null;
	public ConnectionFactory connectionFactory = null;
	public Connection connection = null;
	public Session session = null;
	public Destination destination = null;
	public MessageProducer producer = null;
	public String destinationName = null;
	public int numMsgs=1;
	public TextMessage message=null;
	String MessageData=null;
	static boolean SEND_STATUS=false;
	boolean MessageStatus=false;
	int SESSIONSTATUS=0;
	

	// private static final Logger LOG =
	// LoggerFactory.getLogger(PublishMessage.class);

	public PublishMessage() {
		
	}
	public String Print ()
	{
	
		String temp="test test ets ";
		return temp;
		
		
	}


	
	// SET PROPE

	/**
	 * @param args
	 *            the destination name to send to and optionally, the number of
	 *            messages to send
	 */

	
	
	
	
	
	public  void Put(String queue,int MessageNumber, String textMessage) {
		
	
		/*
		 * Create a JNDI API InitialContext object
		 */
		numMsgs=MessageNumber;
		destinationName =queue;
		ReadFromFile myfile=new ReadFromFile();
		
		try {
			jndiContext = new InitialContext();
		} catch (NamingException e) {
			// LOG.info("Could not create JNDI API context: " + e.toString());
			System.exit(1);
		}
		
		/*
		 * Look up connection factory and destination.
		 */
		
		try {
			connectionFactory = (ConnectionFactory) jndiContext
					.lookup("ConnectionFactory");
		//	System.out.println(destinationName);
			destination = (Destination) jndiContext.lookup(destinationName);
		} catch (NamingException e) {
			// / LOG.info("JNDI API lookup failed: " + e);
			e.printStackTrace();
			try {
				System.out.println(jndiContext.getEnvironment());
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(1);
		}

	
		
		
		/*
		 * Create connection. Create session from connection; false means
		 * session is not transacted. Create sender and text message. Send
		 * messages, varying text slightly. Send end-of-messages message.
		 * Finally, close connection.
		 */
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(destination);
			message = session.createTextMessage();
			
			for (int i = 0; i < numMsgs; i++) {
			//	message.setText("This is a test message from jad" + (i + 1));
				message.setText(textMessage);
				///System.out.println("This is a test message sent from jad" + textMessage + i );
			//	 LOG.info("Sending message: " + message.getText());
				producer.send(message);
				SEND_STATUS=true;
				MessageStatus=true;
				
			}

			/*
			 * Send a non-text control message indicating end of messages.
			 */
			producer.send(session.createMessage());
		} catch (JMSException e) {
			
			SEND_STATUS=false;
		 ///LOG.info("Exception occurred: " + e);
		} finally {
			if (connection != null) {
				try {
					//System.out.println("connection could not open then i will close" + connection);
					connection.close();
				} catch (JMSException e) {
				}
			}
		}
		////System.out.println("connection return value is : " + connection + " Check if Server is runing");	
	}
	void ShowPublishedMessage(Message message)
	{
		if (MessageStatus)
		System.out.println( message);
		
	}
	//Return the details of the connection 
	Connection GetConnectionValue(){
		
		return connection;
	}
	boolean ReturnSendStatus()
	{
		return SEND_STATUS;
	}

}