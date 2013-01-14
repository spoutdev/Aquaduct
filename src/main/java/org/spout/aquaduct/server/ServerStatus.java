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

/**
 * Represents the current status of a server
 */
public enum ServerStatus {
	/**
	 * When the server is completely shut down
	 */
	STOPPED(false),
	/**
	 * When the server is starting up, but not ready for players yet
	 */
	STARTING(true),
	/**
	 * When the server is running and ready for players
	 */
	STARTED(true),
	/**
	 * When the server is currently shutting down
	 */
	STOPPING(true),
	/**
	 * When the server or the JVM have crashed
	 */
	CRASHED(false),
	/**
	 * When the server doesn't react to status polls
	 */
	UNRESPONSIVE(true), ;

	private final boolean isRunning;

	private ServerStatus(boolean running) {
		this.isRunning = running;
	}

	public boolean isRunning() {
		return isRunning;
	}
}
