package org.spout.flo.web;

import com.google.gson.JsonElement;

public interface WebClientRequestHandler {
	/**
	 * Gets the response data for the requested data
	 * @param requestType the request type the request had
	 * @param args the arguments for the request
	 * @return the answer to the given request
	 */
	public Object getResponse(String requestType, JsonElement args);
}
