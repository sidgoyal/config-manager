package io.github.sidgoyal;

import java.io.InputStream;

public abstract class AbstractFilePropetyLoader extends AbstractPropertyLoader {

  public AbstractFilePropetyLoader(PropertyLoader loader) {
    super(loader);
  }

  protected InputStream getResourceAsStream(String fileName) {
    return this.getClass().getClassLoader().getResourceAsStream(fileName);
  }

}
