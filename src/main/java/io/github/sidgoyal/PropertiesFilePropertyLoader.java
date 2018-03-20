package io.github.sidgoyal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.sidgoyal.utils.StringUtils;

public class PropertiesFilePropertyLoader extends AbstractFilePropetyLoader {

  private final Properties props = new Properties();

  public PropertiesFilePropertyLoader(PropertyLoader loader, String fileName) {
    super(loader);
    if (StringUtils.isEmpty(fileName)) {
      throw new IllegalArgumentException("PropertyFileName cannot be empty");
    }
    logger.debug("Reading {} file", fileName);
    try (InputStream in = this.getResourceAsStream(fileName)) {
      props.load(in);

    } catch (IOException e) {
      logger.warn("Could not load {} propeties file, will ignore it", fileName);
    }

  }

  @Override
  public final String laodProperty(String key) {
    String parentProp = getParentProperty(key);
    return parentProp != null ? parentProp : props.getProperty(key);
  }

}
