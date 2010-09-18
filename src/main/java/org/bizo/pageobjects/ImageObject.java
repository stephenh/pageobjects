package org.bizo.pageobjects;

public class ImageObject extends AbstractElementObject {

  public ImageObject(final PageObject p, final String id) {
    super(p, id);
  }

  public String getSource() {
    return element().getAttribute("src");
  }

}
