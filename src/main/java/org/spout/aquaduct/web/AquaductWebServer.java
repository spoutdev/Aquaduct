/*
 * This file is part of Aquaduct.
 *
 * Copyright (c) 2012 Spout LLC <http://www.spout.org/>
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

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.narrowtux.blueberry.Address;
import com.narrowtux.blueberry.BlueberryWebServer;

import org.spout.aquaduct.AquaductConfiguration;

public class AquaductWebServer {
	BlueberryWebServer server;
	AquaductWebSocketHandler webSocketHandler;

	public AquaductWebServer() {
		server = new BlueberryWebServer();
		try {
			server.bind(new Address(InetAddress.getByName(AquaductConfiguration.BIND_ADDRESS.getString(null)), AquaductConfiguration.BIND_PORT.getInt(5000)));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
		server.setApplicationName("Aquaduct");
		server.getHandlers().add(new ResourceFileHandler());
		webSocketHandler = new AquaductWebSocketHandler();
		server.getHandlers().add(webSocketHandler);
		server.start();
	}

	public AquaductWebSocketHandler getWebSocketHandler() {
		return webSocketHandler;
	}

	public void stop() {
		server.stop();
	}
}
