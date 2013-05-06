package com.example.activemq.simulation3;

import com.util.jms.Subscriber;

public class Sim3_Subscriber 
{
	public static String TOPIC1 = "simple.topic1";
	public static String WATCH_PROPERTY = "prop1";
	
	public static void main (String [] args)
	{
		test1(args);
	}
	
	static void test1(String [] args)
	{
		args = new String [] { "tcp://localhost:61616" , Sim3_Subscriber.TOPIC1};
		
		String url = args[0];
		String listenTopic = args[1];
		
		Subscriber	subscriber = new Subscriber();
		
		subscriber.setWatchProperty(WATCH_PROPERTY);
		
		subscriber.startListening(url, listenTopic);	
	}
}
