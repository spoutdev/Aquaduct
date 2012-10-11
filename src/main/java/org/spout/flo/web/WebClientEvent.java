package org.spout.flo.web;

public class WebClientEvent extends WebClientFrame {
	private String eventType;
	
	public WebClientEvent(String eventType, Object data) {
		super("push");
		this.eventType = eventType;
		this.data = data;
	}

	public String getEventType() {
		return eventType;
	}
}
