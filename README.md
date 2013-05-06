java_activemq_helloworld
========================

A simple project for ActiveMQ.  Has all elements and is ready to go.  No Maven.  

Just import this into Eclipse and it should be ready to go, as they core .jar file is included.


RUNNING THE EXAMPLE
========================

Simulation 3 has a good example.  Here are the steps:

1. Run BrokerStarter .  There's a script to run that from the command; or eclipse is fine - just pin the console.
2. Start the Sim3_Subscriber - start a new console window and pin it
3. Start the Sim3_Publisher - start a new console window. here you will see that messages are published, and then the subscriber console window is updated

In this example we send a text message object and set a header property as well.
