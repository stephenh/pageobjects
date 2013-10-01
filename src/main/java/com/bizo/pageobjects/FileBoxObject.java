package com.bizo.pageobjects;

import java.io.File;

public class FileBoxObject extends AbstractElementObject {

  private final String id;

  /** Only take an id for our firefox change hack. */
  public FileBoxObject(final PageObject p, final String id) {
    super(p, id);
    this.id = id;
  }

  public void set(File file) {
    type(file.getAbsolutePath());
  }

  public void type(final String value) {
    getElement().sendKeys(value);
    // We used to fake a change event for FF, but it seems to be working now
    // (as of October 2013-ish). Not sure when exactly it changed.
    if (DummyClickDiv.isEnabled()) {
      DummyClickDiv.click(getWebDriver());
    } else {
      // not sure what to do here--the tab char got corrupted by FF
    }
  }

}
