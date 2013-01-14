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
package org.spout.aquaduct.server;

import org.spout.api.signal.Signal;
import org.spout.api.signal.SignalSubscriberObject;

public abstract class Server extends SignalSubscriberObject {
	/**
	 * Emitted when the servers status changes.
	 * @sarg org.spout.aquaduct.server.ServerStatus the new status of the server
	 */
	public static final Signal SIGNAL_STATUS_CHANGE = new Signal("STATUS_CHANGE", ServerStatus.class);

	/**
	 * Emitted when the servers console printed a new message
	 * @sarg java.lang.String the message
	 */
	public static final Signal SIGNAL_CONSOLE_MESSAGE = new Signal("CONSOLE_MESSAGE", String.class);

	/**
	 * Emitted when an error occurs that should be sent to watching web frontends
	 * @sarg java.lang.String the message
	 */
	public static final Signal SIGNAL_ERROR = new Signal("ERROR", String.class);

	private String name;

	public Server() {
		registerSignal(SIGNAL_CONSOLE_MESSAGE);
		registerSignal(SIGNAL_STATUS_CHANGE);
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return "0";
	}

	public abstract PluginManager getPluginManager();

	public abstract ServerStatus getStatus();

	public abstract void start();

	public abstract void stop();
}
