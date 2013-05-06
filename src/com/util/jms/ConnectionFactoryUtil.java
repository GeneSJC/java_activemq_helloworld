package com.util.jms;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;



/**
 * This is a simple example to show some basics of JMS for a
 * publish - subscribe scenario.
 */
public class ConnectionFactoryUtil {

    private static Logger jdkLogger = Logger
            .getLogger("com.zcage.jms.ExamplePublishAndSubscribe");

    /**
     * Use the ActiveMQConnectionFactory to get a JMS ConnectionFactory. In an
     * enterprise application this would normally be accessed through JNDI.
     */
    public static ConnectionFactory getJmsConnectionFactory()
            throws JMSException {

    	String url = ActiveMQConnection.DEFAULT_BROKER_URL;

        return ConnectionFactoryUtil.getJmsConnectionFactory(url);
    }

    public static ConnectionFactory getJmsConnectionFactory(String url)
    throws JMSException {
    	String user = ActiveMQConnection.DEFAULT_USER;
    	String password = ActiveMQConnection.DEFAULT_PASSWORD;
    	
    	jdkLogger.info("Listening to broker Url = "+ url);

    	return new ActiveMQConnectionFactory(user, password, url);
    }    
}