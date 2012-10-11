package org.spout.flo;

import java.io.File;

import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

public class FloConfiguration extends ConfigurationHolderConfiguration {
	
	public static ConfigurationHolder BIND_ADDRESS = new ConfigurationHolder((Object) null, "bind.address");
	public static ConfigurationHolder BIND_PORT = new ConfigurationHolder(80, "bind.port");
	public static ConfigurationHolder BASE_SERVER_FOLDER = new ConfigurationHolder("servers/", "servers.basefolder");

	public FloConfiguration() {
		super(new YamlConfiguration(new File("config.yml")));
	}

	@Override
	public void load() throws ConfigurationException {
		super.load();
		super.save();
	}
}
