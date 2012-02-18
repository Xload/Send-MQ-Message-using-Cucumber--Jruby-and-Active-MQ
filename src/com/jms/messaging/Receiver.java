package com.jms.messaging;

import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.sun.xml.internal.ws.encoding.xml.XMLMessage;

@SuppressWarnings("unused")
public final class Receiver {
	
	public Context jndiContext = null;
	public ConnectionFactory connectionFactory = null;
	public Connection connection = null;
	public Session session = null;
	public Destination destination = null;
	public MessageConsumer consumer = null;
	public String destinationName = null;
	public TextMessage text = null;
	public Message message = null;
	 int numMsgs=0;
	 boolean messagestatus=false;
	 boolean CONNECTIONSTATUS=false;
 
	public Receiver() {
	}

	// SET PROPER

	/**
	 * @param args
	 */
	Message GetMessage (String queuename, int MsgNum) {
		
		
		 numMsgs=MsgNum;	

		/**
		 * if ((args.length < 1) || (args.length > 2)) { // LOG.info(
		 * "Usage: java SimpleProducer <destination-name> [<number-of-messages>]"
		 * ); System.exit(1); }
		 */

		destinationName =queuename;

		/**
		 * if ( ! ( destType.equals("queue") || destType.equals("topic") ) ) {
		 * // System.out.println("Argument must be \Óqueue\Ó or
		 * " + "\Ótopic\Ó"); System.exit(1); }
		 */

		try {
			jndiContext = new InitialContext();
		} catch (NamingException e) {
			// LOG.info("Could not create JNDI API context: " + e.toString());
			System.exit(1);
		}

		/*
		 * Look up connection factory and create session
		 */
		try {

			try {

				connectionFactory = (ConnectionFactory) jndiContext
						.lookup("ConnectionFactory");
				destination = (Destination) jndiContext.lookup(destinationName);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			consumer = session.createConsumer(destination);
		
			for (int i=0;i< numMsgs;i++) {
			message = consumer.receive(2000);
			if (message != null) {
				if (message instanceof TextMessage) {
					 text = (TextMessage) message;
					 text.getText();
					 messagestatus=true;
					CONNECTIONSTATUS=true;
				//	System.out.println("Reading message from the queue : " + text.getText());
				//	text=null;
					//System.out.println("message:" + message.toString());
					//System.out.println("issue with the message " + message.getJMSMessageID());
					//System.out.println( message.getJMSDeliveryMode());
					//System.out.println( message.getClass());
					//System.out.println( message.getJMSReplyTo());
					//System.out.println( message.getJMSExpiration());
					//System.out.println( message.getJMSCorrelationID());
					//System.out.println( message.getJMSType());
					///System.out.println("Issue with the message content is" + message.toString());
				}
				else
				{
					 messagestatus=false;
					/// System.out.println("Reading message from the queue : " + text.getText());
		//			  System.out.println(message.getClass());
			//		System.out.println("issue with the message " + message.getJMSMessageID());
				//	System.out.println( message.getJMSDeliveryMode());
					//System.out.println( message.getClass());
			//		System.out.println( message.getJMSReplyTo());
				//	System.out.println( message.getJMSExpiration());
			//		System.out.println( message.getJMSCorrelationID());
		//			System.out.println( message.getJMSType());
				
				}//end of else
				
				
				if (message instanceof ObjectMessage) {
			        Serializable object = ((ObjectMessage) message).getObject();
			    }
				if (message instanceof BytesMessage) {

			        //System.out.println("ITS A BYTES MESSAGE!!!!!!!!!!!"); 


			        }
				if (message instanceof StreamMessage) {

//			        System.out.println("ITS A StreamMessage!!!!!!!!!!!"); 


			        }
				if (message instanceof MapMessage) {

	//		        System.out.println("ITS A MapMessage!!!!!!!!!!!"); 
			        messagestatus=false;

			        }
			
				
				
				
			 }
			
			 }
				///return message;
		}// Main try

		catch (JMSException e) {
			// TODO Auto-generated catch block
		//	System.out.println("Message Received is null");
			e.printStackTrace();
			CONNECTIONSTATUS=false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				//	System.out.println("Connection Closed");
				} catch (JMSException e) {
				}
			}
		}
		return message;

	}// end of fnction get
	
	void ShowReceivedMessage(Message message) throws JMSException
	{
		if ( messagestatus)
		{
			System.out.println( text.getText());
		}
		else
			System.out.println("Issues receiving the message and therefore we will not display it ");
		
		
	}

String GetReceiverMessageSenderName() throws JMSException{
	
	if (messagestatus)
	{
	return message.getJMSMessageID();
	
	}
	else return null;
}
public String GetMessageTextData() throws JMSException
{

	if (messagestatus)
	{
		
	return text.getText();
	
	}
	else
		return null;
	
	
}
public  boolean GetStatusOfMessage()
{
	return messagestatus;
	
	
}

Connection GetConnectionStatus()
{
	return connection;
}

boolean CheckMessageNotNull(String message){
	
	if (message==null)
	{
	return false;
	}
	else 
		return true;
	}
	
}// end of class
