set BIN=..\bin
set ACTIVE_MQ_LIB=..\lib\activemq-all-5.8.0.jar
set CP1=%CLASSPATH%;%BIN%;%ACTIVE_MQ_LIB%
set MAIN=com.util.jms.BrokerStarter
 
java -cp "%CP1%" %MAIN%


