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
package org.spout.aquaduct;

import java.io.File;

import org.spout.cereal.config.ConfigurationException;
import org.spout.cereal.config.ConfigurationHolder;
import org.spout.cereal.config.ConfigurationHolderConfiguration;
import org.spout.cereal.config.yaml.YamlConfiguration;

public class AquaductConfiguration extends ConfigurationHolderConfiguration {
	public static ConfigurationHolder BIND_ADDRESS = new ConfigurationHolder((Object) null, "bind.address");
	public static ConfigurationHolder BIND_PORT = new ConfigurationHolder(80, "bind.port");
	public static ConfigurationHolder BASE_SERVER_FOLDER = new ConfigurationHolder("servers/", "servers.basefolder");

	public AquaductConfiguration() {
		super(new YamlConfiguration(new File("config.yml")));
	}

	@Override
	public void load() throws ConfigurationException {
		super.load();
		super.save();
	}
}
