package org.bizo.pageobjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CheckBoxObject extends AbstractElementObject {
  public CheckBoxObject(final PageObject p, final String id) {
    super(p, id);
  }

  public void uncheck() {
    if (!isChecked()) {
      throw new RuntimeException("Already unchecked " + by);
    }
    element().click();
  }

  public void check() {
    if (isChecked()) {
      throw new RuntimeException("Already checked " + by);
    }
    element().click();
  }

  public void assertChecked() {
    assertThat(element().isSelected(), is(true));
  }

  public void assertNotChecked() {
    assertThat(element().isSelected(), is(false));
  }

  public void assertEnabled() {
    assertThat(element().isEnabled(), is(true));
  }

  public void assertDisabled() {
    assertThat(element().isEnabled(), is(false));
  }

  public boolean isChecked() {
    return element().isSelected();
  }

  public boolean isEnabled() {
    return element().isEnabled();
  }
}
