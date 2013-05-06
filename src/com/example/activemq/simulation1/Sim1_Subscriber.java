package com.example.activemq.simulation1;

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


public class Sim1_Subscriber implements MessageListener {

	Logger jdkLogger = Logger.getLogger("");

	String propValToListenFor = null;
	
	public Sim1_Subscriber (String propValToListenFor) {
		this.propValToListenFor = propValToListenFor;
	}
	
	public void startListening() {
	    jdkLogger.info("Subscriber.startListening()");
	    Connection connection = null;
	    try {
	        ConnectionFactory factory = PublishSubscribeSimulation
	                .getJmsConnectionFactory();
	        connection = factory.createConnection();
	        Session session = connection.createSession(false,
	                Session.AUTO_ACKNOWLEDGE);
	        Topic topic = session
	                .createTopic(PublishSubscribeSimulation.TOPIC1);
	        MessageConsumer consumer = session.createConsumer(topic, "abc_property = \'" + propValToListenFor + "\' ");
	        consumer.setMessageListener(this);

	        connection.start();
	    } catch (JMSException e) {
	        jdkLogger.info("Exception occurred: " + e.toString());
	    }
	}

	/**
	 * Just log a note when a message is received.
	 */
	public void onMessage(Message message) {
	    if (message instanceof TextMessage) {
	        TextMessage txtMsg = (TextMessage) message;
	        try {
	            jdkLogger.info("<<<<< SUBSCRIBED TO ["  + propValToListenFor 
	            		+ "] GOT MESSAGE " + txtMsg.getText());
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void stopListening () {
		System.out.println ("Subscriber Not sure how to stop");
	}
	
}
