package io.github.sidgoyal;

public final class SystemPropertyLoader extends AbstractPropertyLoader {

  private static SystemPropertyLoader instance;

  private SystemPropertyLoader() {
  }

  /**
   * 
   * @return the instnace of SystemPropertyLoader singleton
   */
  public static final SystemPropertyLoader getInstance() {
    if (instance == null) {
      instance = new SystemPropertyLoader();
    }
    return instance;
  }

  @Override
  public final String laodProperty(String key) {
    String parentProp = getParentProperty(key);
    return parentProp != null ? parentProp : System.getProperty(key);
  }

}
