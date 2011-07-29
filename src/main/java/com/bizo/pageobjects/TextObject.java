package com.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;

import com.google.common.base.Supplier;

public class TextObject extends AbstractElementObject {

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
    return element().getText();
  }

  public void assertText(final String text) {
    assertThat(get(), is(text));
  }

  public Supplier<Boolean> nowHasText(final String text) {
    return new Supplier<Boolean>() {
      public Boolean get() {
        return text.equals(TextObject.this.get());
      }
    };
  }

}
