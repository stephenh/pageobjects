package com.bizo.pageobjects;

import org.openqa.selenium.By;

public class ImageObject extends AbstractElementObject {

  public ImageObject(final PageObject p, final String id) {
    super(p, id);
  }

  public ImageObject(final PageObject p, final By by) {
    super(p, by);
  }

  public String getSource() {
    return element().getAttribute("src");
  }

}
