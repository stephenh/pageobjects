package com.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;

public class CheckBoxObject extends AbstractElementObject {

  public CheckBoxObject(final PageObject p, final String id) {
    super(p, id);
  }

  public CheckBoxObject(final PageObject p, final By by) {
    super(p, by);
  }

  public void uncheck() {
    if (!isChecked()) {
      throw new RuntimeException("Already unchecked " + by);
    }
    getElement().click();
  }

  public void check() {
    if (isChecked()) {
      throw new RuntimeException("Already checked " + by);
    }
    getElement().click();
  }

  public void assertChecked() {
    assertThat(getElement().isSelected(), is(true));
  }

  public void assertNotChecked() {
    assertThat(getElement().isSelected(), is(false));
  }

  public void assertEnabled() {
    assertThat(getElement().isEnabled(), is(true));
  }

  public void assertDisabled() {
    assertThat(getElement().isEnabled(), is(false));
  }

  public boolean isChecked() {
    return getElement().isSelected();
  }

  public boolean isEnabled() {
    return getElement().isEnabled();
  }
}
