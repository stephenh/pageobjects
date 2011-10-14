package com.bizo.pageobjects;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CheckBoxObject extends AbstractElementObject {

  private final List<ExpectedCondition<?>> afterCheckWaitFor = newArrayList();

  public CheckBoxObject(final PageObject p, final String id) {
    super(p, id);
  }

  public CheckBoxObject(final PageObject p, final By by) {
    super(p, by);
  }

  /** Adds {@code conditions} as something to wait for after checking. */
  public CheckBoxObject afterCheckWaitFor(final ExpectedCondition<?>... conditions) {
    for (ExpectedCondition<?> condition : conditions) {
      afterCheckWaitFor.add(condition);
    }
    return this;
  }

  public void uncheck() {
    if (!isChecked()) {
      throw new RuntimeException("Already unchecked " + by);
    }
    getElement().click();
    p.waitFor(afterCheckWaitFor);
  }

  public void check() {
    if (isChecked()) {
      throw new RuntimeException("Already checked " + by);
    }
    getElement().click();
    p.waitFor(afterCheckWaitFor);
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
