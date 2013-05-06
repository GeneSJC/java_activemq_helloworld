package com.example.activemq.simulation1;

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


public class Sim1_Publisher extends Thread {

	Logger jdkLogger = Logger.getLogger("");
	boolean bRunning;
	boolean bStopped;
	int msgCount = 0;
	long SEND_INTERVAL_MSEC = 1500;

	public static void main (String [] args)
	{
		new Sim1_Publisher().run();
	}
	
	public void run() {
		Connection connection = null;
		bRunning = true;
		
		String [] vals = { 
				PublishSubscribeSimulation.PROP_VAL1, 
				PublishSubscribeSimulation.PROP_VAL2 };
		
		while (bRunning) {
			try {
				++msgCount;
				jdkLogger.info("Publish.run() - message: " + msgCount);

				ConnectionFactory factory = PublishSubscribeSimulation
				.getJmsConnectionFactory();
				connection = factory.createConnection();
				connection.start();

				Session session = connection.createSession(false,
						Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
				Topic topic = session
				.createTopic(PublishSubscribeSimulation.TOPIC1);

				MessageProducer producer = session.createProducer(topic);
				producer.setTimeToLive(10000);
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

				String val = vals[msgCount % 2];
				TextMessage message = session
				.createTextMessage("Here is a message [" + msgCount
						+ "] with this in the header [" +  val + "] at: " + new Date());

				jdkLogger.info(">>>> SENDING MESSAGE  :: " + message.getText() );
				
				message.setStringProperty( "abc_property", val);
				
				producer.send(message);

				connection.close(); // In a real world application, you may want
				// to keep
				connection = null; // the connection open for performance.

				if (bRunning) {
					Thread.sleep((int) (Math.random() * 2 * SEND_INTERVAL_MSEC));
				}
			}

			catch (Exception e) {
				jdkLogger.info("Exception occurred: " + e.toString());
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (JMSException e) {
					}
				}
			}
		} // End while

		bStopped = true;
		synchronized (this) {
			notifyAll();
		}
		jdkLogger.info("Publisher.run(). Stopped.");
	}

	public void stopPublishing () {
		System.out.println ("Publisher Not sure how to stop");
	}
}

