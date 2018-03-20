package io.github.sidgoyal;

import io.github.sidgoyal.utils.StringUtils;

public final class EnvPropertyLoader extends AbstractPropertyLoader {

  private String envKeyReplacer;
  private String envPrefix;

  /**
   * 
   * @param loader
   * @param envKeyReplacer
   * @param envPrefix
   */
  public EnvPropertyLoader(PropertyLoader loader, String envKeyReplacer, String envPrefix) {
    super(loader);
    this.envKeyReplacer = envKeyReplacer;
    this.envPrefix = envPrefix;

  }

  @Override
  public final String laodProperty(String key) {
    String parentProp = getParentProperty(key);
    return parentProp != null ? parentProp : System.getenv(envEffectiveKey(key));
  }

  private final String envEffectiveKey(String key) {
    if (!StringUtils.isEmpty(envPrefix)) {
      return envPrefix + envKeyReplacer + key.replaceAll("\\.", envKeyReplacer);
    } else {
      return key.replaceAll("\\.", envKeyReplacer);
    }
  }

}
