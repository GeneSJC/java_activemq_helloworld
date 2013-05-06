package com.util.jms;

import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class Subscriber implements MessageListener {

	Logger jdkLogger = Logger.getLogger("");

		// message property to watch for
	public String watchProperty;
	
	public void setWatchProperty(String property) 
	{
		this.watchProperty = property;
	}

	public void startListening(String brokerUrl, String topicUri) {
		
	    System.out.println("Subscriber.startListening() on broker [" + brokerUrl + "],  for topic [" + topicUri + "]");
	    
	    Connection connection = null;
	    try {
	        ConnectionFactory factory = ConnectionFactoryUtil.getJmsConnectionFactory(brokerUrl);
	        connection = factory.createConnection();
	        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
	        Topic topic = session.createTopic(topicUri);
	        MessageConsumer consumer = session.createConsumer(topic);
	        consumer.setMessageListener(this);

	        connection.start();
	        
	    } catch (JMSException e) {
	        System.out.println("Exception occurred: " + e.toString());
	    }
	}

	/**
	 * Just log a note when a message is received.
	 */
	public void onMessage(Message message) {
		
		// System.out.println("Inside onMessage  with message = " + message);

	    try {
			System.out.println("Inside onMessage  with message property value = " + message.getStringProperty(this.watchProperty));
	
			if ( message instanceof TextMessage )
			{
				TextMessage tm = (TextMessage) message;
				
				System.out.println("Inside onMessage  with message text = " + tm.getText());
			}
	    // 	handleExpectedMessageStructure(message);	    	
	    } 
	    catch (JMSException e) {
	        System.out.println("Exception occurred: " + e.toString());
	    }
	    catch (Exception e) {
	        System.out.println("Exception occurred: " + e.toString());
	    }
	    


		
//	    if (message instanceof TextMessage) {
//	        TextMessage txtMsg = (TextMessage) message;
//	    }
	    
	}
	
	public void stopListening () {
		System.out.println ("Subscriber Not sure how to stop");
	}




}

