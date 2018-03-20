package io.github.sidgoyal;

import io.github.sidgoyal.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ConfigManager manages configuration from multiple sources
 * This is modeled similar to classloader heriarchy
 * System Prop > Env Variables (_) > application-{env}.properties > application.peroperties
 * 
 * @author sid
 *
 */
public final class ConfigManager {

  private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

  private PropertyLoader loader;

  private static final String PROFILE_SELECTOR_PROP = "io.github.sidgoyal.config.env";

  ConfigManager(String envKeyReplacer, String envPrefix) {
    PropertyLoader loader = SystemPropertyLoader.getInstance();
    loader = new EnvPropertyLoader(loader, envKeyReplacer, envPrefix);

    if (StringUtils.isEmpty(loader.laodProperty(PROFILE_SELECTOR_PROP))) {
      logger.info("No Config profiles selected");
    } else {
      String fileName = "application-" + loader.laodProperty(PROFILE_SELECTOR_PROP) + ".properties";
      loader = new PropertiesFilePropertyLoader(loader, fileName);
    }
    loader = new PropertiesFilePropertyLoader(loader, "application.properties");

    this.loader = loader;
  }

  /**
   * get the property.
   * 
   * @param key
   *          the porperty key
   * @param defaultValue
   *          the default value
   * @returns the value of the property or the default value
   */
  public String getProperty(String key, String defaultValue) {
    String val = getProperty(key);
    return val == null ? defaultValue : val;
  }

  /**
   * get the property.
   * 
   * @param key
   *          the property key
   * @return the value of the property or null
   */
  public String getProperty(String key) {
    return loader.laodProperty(key);
  }

}
