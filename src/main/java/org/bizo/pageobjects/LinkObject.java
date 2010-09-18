package org.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LinkObject extends AbstractElementObject {
  public LinkObject(final PageObject p, final String id) {
    super(p, id);
  }

  public void click() {
    element().click();
  }

  public String getTitle() {
    return element().getAttribute("title");
  }

  public String getText() {
    return element().getText();
  }

  public String getHref() {
    return element().getAttribute("href");
  }

  public void assertText(final String expected) {
    assertThat(getText(), is(expected));
  }
}
