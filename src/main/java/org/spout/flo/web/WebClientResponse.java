package org.spout.flo.web;

public class WebClientResponse extends WebClientFrame {
	private int requestId;

	public WebClientResponse(int request, Object data) {
		super("pull");
		this.requestId = request;
		this.data = data;
	}
	
	public int getRequest() {
		return requestId;
	}
}
