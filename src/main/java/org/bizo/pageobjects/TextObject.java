package org.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;

public class TextObject extends AbstractElementObject {

  private For waitFor;

  public TextObject(final PageObject p, final By by) {
    super(p, by);
  }

  public TextObject(final PageObject p, final String id) {
    super(p, id);
  }

  public void click() {
    element().click();
  }

  public String get() {
    if (waitFor != null) {
      p.wait(waitFor);
    }
    return element().getText();
  }

  public TextObject wait(final For waitFor) {
    this.waitFor = waitFor;
    return this;
  }

  public void assertText(final String text) {
    assertThat(get(), is(text));
  }

}