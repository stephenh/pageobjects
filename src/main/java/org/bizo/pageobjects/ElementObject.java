package org.bizo.pageobjects;

import org.openqa.selenium.By;

public class ElementObject extends AbstractElementObject {

  public ElementObject(final PageObject p, final String id) {
    super(p, id);
  }

  public ElementObject(final PageObject p, final By by) {
    super(p, by);
  }

}
