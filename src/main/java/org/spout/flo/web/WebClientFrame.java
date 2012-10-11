package org.spout.flo.web;

public class WebClientFrame {
	private String frameType;
	protected Object data;
	
	public WebClientFrame(String frameType) {
		this.frameType = frameType;
	}

	public String getFrameType() {
		return frameType;
	}

	public Object getData() {
		return data;
	}
}
