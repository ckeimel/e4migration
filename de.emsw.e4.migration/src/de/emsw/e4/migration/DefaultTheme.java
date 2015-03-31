package de.emsw.e4.migration;

import org.eclipse.fx.ui.theme.AbstractTheme;

public class DefaultTheme extends AbstractTheme {

  public DefaultTheme() {
    super("default", "Default Theme", DefaultTheme.class.getClassLoader().getResource("css/default.css"));
  }
  
}

