package com.example.activemq.simulation3;

import com.util.jms.Publisher;

public class Sim3_Publisher {

	public static void main (String [] args)
	{
		test1(args);
	}
	
	static void test1(String [] args)
	{
		args = new String [] { "tcp://localhost:61616" , Sim3_Subscriber.TOPIC1};
		
		Publisher publisher = new Publisher();
		
		publisher.url = args[0];
		publisher.topic = args[1];
		
		publisher.propertyName = Sim3_Subscriber.WATCH_PROPERTY;
		publisher.propertyValue = "10 of Aces";
		
		publisher.run();
	}
}
