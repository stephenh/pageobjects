package org.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TextBoxObject extends AbstractElementObject {

  private static final String MAC_COMMAND_KEY = "\uE03D";

  public TextBoxObject(final PageObject p, final String id) {
    super(p, id);
  }

  public TextBoxObject(final PageObject p, final By by) {
    super(p, by);
  }

  public void sendKeys(final String value) {
    element().sendKeys(value);
  }

  public void type(final String value) {
    // To best simulate typing, send select-all + delete + value + tab out
    // Without the select-all+delete, a separate WebElement.clear() is needed to
    // remove any existing text, but it will result in an extra onchange event.
    // Without the tab, an onchange event will not fire until the next element
    // is selected
    final CharSequence ctrl = usingMac() ? MAC_COMMAND_KEY : Keys.CONTROL;
    element().sendKeys(Keys.chord(ctrl, "a", Keys.NULL, Keys.DELETE, value, Keys.NULL, Keys.SHIFT, Keys.TAB));
    getWebDriver().findElement(By.id("dummy-click-div")).click();
  }

  private boolean usingMac() {
    return System.getProperty("mrj.version") != null;
  }

  public String get() {
    return element().getValue();
  }

  public void assertText(final String text) {
    assertThat(get(), is(text));
  }
}
