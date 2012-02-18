package com.jms.messaging;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;
public class MessageTopicSubscriber implements javax.jms.MessageListener{
	@SuppressWarnings("unused")
    private TopicSession subSession;
    private TopicPublisher publisher;
    private TopicConnection connection;
    @SuppressWarnings("unused")
	private String username=null;
    @SuppressWarnings("unused")
	private int duation=0;
    @SuppressWarnings("unused")
	private String password=null;
    
    /* Constructor. Establish JMS publisher and subscriber */
    public MessageTopicSubscriber(String topicName, String username, String password)
    throws Exception {
        // Obtain a JNDI connection
        Properties env = new Properties( );
        // ... specify the JNDI properties specific to the vendor

        InitialContext jndi = new InitialContext(env);

        // Look up a JMS connection factory
        TopicConnectionFactory conFactory =
        (TopicConnectionFactory)jndi.lookup("TopicConnectionFactory");

        // Create a JMS connection
        TopicConnection connection =
        conFactory.createTopicConnection(username,password);
/*
        // Create two JMS session objects
        TopicSession pubSession =
        connection.createTopicSession(false,
                                      Session.AUTO_ACKNOWLEDGE);
                                      
                                      **/
        
        TopicSession subSession =
        connection.createTopicSession(false,
                                      Session.AUTO_ACKNOWLEDGE);

        // Look up a JMS topic
        Topic Topic = (Topic)jndi.lookup(topicName);

        // Create a JMS publisher and subscriber
        /*
        TopicPublisher publisher = 
            pubSession.createPublisher(Topic);
        */
        
        
        TopicSubscriber subscriber = 
            subSession.createSubscriber(Topic);

        // Set a JMS message listener
        subscriber.setMessageListener((MessageListener) this);

        // Intialize  application
        set(connection, subSession, publisher, username);

        // Start the JMS connection; allows messages to be delivered
        connection.start( );

    }
    
    /* Initialize the instance variables */
    public void set(TopicConnection con,
                    TopicSession subSess, TopicPublisher pub, 
                    String username) {
        this.connection = con;
        this.subSession = subSess;
       // this.publisher = pub;
        this.username = username;
    }
	
    /* Receive message from topic subscriber */
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText( );
            System.out.println(text);
        } catch (JMSException jmse){ jmse.printStackTrace( ); }
    }
    /* Close the JMS connection */
    public void close( ) throws JMSException {
        connection.close( );
    }
   
   //How many time it should loop 
    public void set_duration(int dur){
    	
    	duation=dur;
     }
    
  
    
    

}
