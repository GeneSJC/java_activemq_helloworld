package com.example.activemq.simulation1;

import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.util.jms.BrokerStarter;



/**
 * This is a simple example to show some basics of JMS for a
 * publish - subscribe scenario.
 */
public class PublishSubscribeSimulation {
    public static final String TOPIC1 = "Simple.Test.Topic1";
    public static final String PROP_VAL1 = "horse";
    public static final String PROP_VAL2 = "train";
    
    public static String BROKER_URL = "tcp://localhost:61616";

    public static Logger jdkLogger = Logger
            .getLogger("com.zcage.jms.ExamplePublishAndSubscribe");

    /**
     * Create a JMS Publisher and Subscriber. Of course in the real world these
     * would be in separate applications. Start these processes and let them run
     * a while before shutting down. Execution comments will be logged.
     */
    public static void main(String[] args) throws Exception {
        BrokerStarter.startBroker(BROKER_URL); // An embedded JMS Broker

//        Thread.sleep(3000);

        Sim1_Subscriber subscriber = new Sim1_Subscriber(PROP_VAL1);
        subscriber.startListening();

        Sim1_Subscriber subscriber2 = new Sim1_Subscriber(PROP_VAL2);
        subscriber2.startListening();

        Sim1_Publisher publisher = new Sim1_Publisher();
        publisher.start(); // Runs as a separate thread

        // Let the system run for a bit then shut it down nicely
        Thread.sleep(10000);
        publisher.stopPublishing();
        subscriber.stopListening();

        jdkLogger.info("Exiting");
        System.exit(0); // Force exit
    }

    /**
     * Use the ActiveMQConnectionFactory to get a JMS ConnectionFactory. In an
     * enterprise application this would normally be accessed through JNDI.
     */
    public static ConnectionFactory getJmsConnectionFactory()
            throws JMSException {
        String user = ActiveMQConnection.DEFAULT_USER;
        String password = ActiveMQConnection.DEFAULT_PASSWORD;
        String url = ActiveMQConnection.DEFAULT_BROKER_URL;

        return new ActiveMQConnectionFactory(user, password, url);
    }
}