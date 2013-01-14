/*
 * This file is part of Aquaduct.
 *
 * Copyright (c) 2012-2013, Spout LLC <http://www.spout.org/>
 * Aquaduct is licensed under the GNU Affero General Public License.
 *
 * Aquaduct is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Aquaduct is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spout.aquaduct.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import com.narrowtux.blueberry.websockets.Frame;
import com.narrowtux.blueberry.websockets.TextFrame;
import com.narrowtux.blueberry.websockets.WebSocketExchange;
import com.narrowtux.blueberry.websockets.WebSocketRequestHandler;

public class AquaductWebSocketHandler extends WebSocketRequestHandler {
	private HashSet<WebSocketExchange> connectedClients = new HashSet<WebSocketExchange>();
	private static final Gson GSON = new Gson();
	private HashMap<String, WebClientRequestHandler> requestHandlers = new HashMap<String, WebClientRequestHandler>();

	private int countup = 1;
	private Timer timer = new Timer();

	public AquaductWebSocketHandler() {
		super();
		setFilter("/websocket/");

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				countup ++;
				WebClientFrame event  = new WebClientEvent("counter", countup);
				sendEvent(event);
			}
		}, 100, 1000);
	}

	public void sendEvent(WebClientFrame event) {
		String data = GSON.toJson(event);
		sendToClients(new TextFrame(data));
	}

	private void sendToClients(Frame frame) {
		for (WebSocketExchange ex:connectedClients) {
			try {
				ex.sendFrame(frame);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onConnect(WebSocketExchange exchange) throws IOException {
		sendToClients(new TextFrame("Client with ip "+ exchange.getHttpExchange().getRequestingAddress().toString()+" connected!"));
		connectedClients.add(exchange);
		System.out.println("Client connected!");
	}

	@Override
	public void onFrameReceived(WebSocketExchange exchange, Frame frame) throws IOException {
		if (frame instanceof TextFrame) {
			String text = ((TextFrame) frame).getText();
			JsonObject element = GSON.fromJson(text, JsonObject.class);
			String requestType = element.get("requestType").getAsString();
			JsonElement data = element.get("data");
			int requestId = element.get("requestId").getAsInt();

			WebClientRequestHandler handler = requestHandlers.get(requestType);
			WebClientResponse response;
			if (handler == null) {
				JsonObject object =  new JsonObject();
				object.add("error", new JsonPrimitive("No request handler found for "+requestType));
				response = new WebClientResponse(requestId, object);
			} else {
				Object replyData = handler.getResponse(requestType, data);
				response = new WebClientResponse(requestId, replyData);
			}

			exchange.sendFrame(new TextFrame(GSON.toJson(response)));
		}
	}

	@Override
	public void onClose(WebSocketExchange exchange) throws IOException {
		connectedClients.remove(exchange);
		sendToClients(new TextFrame("Client with ip "+ exchange.getHttpExchange().getRequestingAddress().toString()+" disconnected!"));
		System.out.println("Client disconnected!");
	}

	public void registerWebRequestHandler(String requestType, WebClientRequestHandler handler) {
		requestHandlers.put(requestType, handler);
	}
}
