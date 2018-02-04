package com.ipukr.elephant.architecture.configuration;

import org.apache.commons.configuration.ConfigurationException;

/**
 * Created by wmw on 16/10/19.
 */
public class PropertiesConfiguration extends org.apache.commons.configuration.PropertiesConfiguration implements Configuration {

    public PropertiesConfiguration(String location) throws ConfigurationException {
        super(location);
    }

}
