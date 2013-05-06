package com.util.jms;

/**
 * Bean for tracking:
 * 	- broker url a subscriber is interested in
 * - topic to subscribe for
 *  
 * @author Gene
 *
 */
public class JmsBean {

	private String brokerName;
	private String brokerUrl;
	private String listenTopic;
	private String publishTopic;

	public JmsBean() {
		
	}
	
	public JmsBean(
			String brokerName,
			String brokerUrl, 
			String listenTopic,
			String publishTopic) {
		super();
		this.brokerName = brokerName;
		this.brokerUrl = brokerUrl;
		this.listenTopic = listenTopic;
		this.publishTopic = publishTopic;
	}

	public String getBrokerUrl() {
		return brokerUrl;
	}

	public void setBrokerUrl(String brokerUrl) {
		this.brokerUrl = brokerUrl;
	}

	public String getListenTopic() {
		return listenTopic;
	}

	public void setListenTopic(String listenTopic) {
		this.listenTopic = listenTopic;
	}

	public String getPublishTopic() {
		return publishTopic;
	}

	public void setPublishTopic(String publishTopic) {
		this.publishTopic = publishTopic;
	}

	public void setJmsFields (JmsBean bean) {
		
		this.setBrokerUrl(bean.getBrokerUrl());
		this.setListenTopic(bean.getListenTopic());
		this.setPublishTopic(bean.getPublishTopic());
	}


	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	@Override
	public String toString() {
		return "PublishSubscribeBean [brokerName=" + brokerName
				+ ", brokerUrl=" + brokerUrl + ", listenTopic=" + listenTopic
				+ ", publishTopic=" + publishTopic + "]";
	}
	

}
