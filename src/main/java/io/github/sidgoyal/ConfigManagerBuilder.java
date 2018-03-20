package io.github.sidgoyal;

public class ConfigManagerBuilder {

  String envSeparator = "_";
  String envPrefix = "";

  public ConfigManagerBuilder setEnvSeparator(String envSeparator) {
    this.envSeparator = envSeparator;
    return this;
  }

  public ConfigManagerBuilder setEnvPrefix(String envPrefix) {
    this.envPrefix = envPrefix;
    return this;
  }

  public ConfigManager build() {
    return new ConfigManager(envSeparator, envPrefix);
  }

}
