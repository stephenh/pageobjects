package com.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TextBoxObject extends AbstractElementObject {

  public TextBoxObject(final PageObject p, final String id) {
    super(p, id);
  }

  public TextBoxObject(final PageObject p, final By by) {
    super(p, by);
  }

  public void sendKeys(final String value) {
    getElement().sendKeys(value);
  }

  public void type(final String value) {
    // remove existing text
    getElement().clear();
    getElement().sendKeys(value);
    // onchange event will not fire until a different element is selected
    if (DummyClickDiv.isEnabled()) {
      DummyClickDiv.click(getWebDriver());
    } else {
      getElement().sendKeys(Keys.chord(Keys.SHIFT, Keys.TAB));
    }
  }

  public String get() {
    return getElement().getAttribute("value");
  }

  public void assertText(final String text) {
    assertThat(get(), is(text));
  }
}
