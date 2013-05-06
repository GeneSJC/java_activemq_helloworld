package com.util.jms;

import java.util.logging.Logger;

import org.apache.activemq.broker.BrokerService;

import com.example.activemq.simulation1.PublishSubscribeSimulation;

public class BrokerStarter {

    public static String BROKER_URL = "tcp://localhost:61616";

    public static Logger jdkLogger = Logger
            .getLogger("com.zcage.jms.ExamplePublishAndSubscribe");

    /**
     * Create a JMS Publisher and Subscriber. Of course in the real world these
     * would be in separate applications. Start these processes and let them run
     * a while before shutting down. Execution comments will be logged.
     */
    public static void main(String[] args) throws Exception 
    {
        BrokerStarter.startBroker(BROKER_URL); // An embedded JMS Broker
    }
        
	/**
	 * Create an Embedded JMS Broker for this example. Requires JDK1.5.
	 */
	public static void startBroker(String url) throws Exception {
	    PublishSubscribeSimulation.jdkLogger.info("Starting Broker");
	    BrokerService broker = new BrokerService();
	    broker.setUseJmx(true);
	    broker.addConnector(url);
	    broker.start();
	    jdkLogger.info("Broker started on url : " + url);
	}

}
