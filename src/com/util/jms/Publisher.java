package com.util.jms;

import java.util.Date;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class Publisher 
{

	Logger jdkLogger = Logger.getLogger("");

	public String url;
	public String topic;
	public String propertyName;
	public String propertyValue;

	public void run() 
	{
		Connection connection = null;

		try 
		{
			ConnectionFactory factory = ConnectionFactoryUtil.getJmsConnectionFactory(this.url);
			connection = factory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
			Topic topic = session.createTopic(this.topic);

			MessageProducer producer = session.createProducer(topic);
			producer.setTimeToLive(10000);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage message = session
					.createTextMessage("Here is a message at: " + new Date());

			jdkLogger.info(">>>> SENDING MESSAGE  :: " + message.getText());

			message.setStringProperty(propertyName, propertyValue);
			
			producer.send(message);

			connection.close(); // In a real world application, you may want
			// to keep
			connection = null; // the connection open for performance.
		}
		catch (Exception e) 
		{
			jdkLogger.info("Exception occurred: " + e.toString());
		} 
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
				}
			}
		}

	}
	
}
