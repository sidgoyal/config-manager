package io.github.sidgoyal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPropertyLoader implements PropertyLoader {

  protected static final Logger logger = LoggerFactory.getLogger(PropertyLoader.class);

  private PropertyLoader parent;

  AbstractPropertyLoader(PropertyLoader parent) {
    this.parent = parent;
  }

  AbstractPropertyLoader() {
    this(null);
  }

  protected String getParentProperty(String key) {
    return parent != null ? parent.laodProperty(key) : null;
  }

  protected final PropertyLoader setParent(PropertyLoader parent) {
    this.parent = parent;
    return this;
  }

}
