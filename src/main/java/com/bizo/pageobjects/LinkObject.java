package com.bizo.pageobjects;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.openqa.selenium.By;

/** A helper object to click on links. */
public class LinkObject extends AbstractElementObject {

  private final List<Condition> afterClickWaitFor = newArrayList();

  public LinkObject(final PageObject p, final String id) {
    super(p, id);
  }

  public LinkObject(final PageObject p, final By by) {
    super(p, by);
  }

  /** Adds {@code condition} as something to wait for after clicking. */
  public LinkObject afterClickWaitFor(final Condition... conditions) {
    for (Condition condition : conditions) {
      afterClickWaitFor.add(condition);
    }
    return this;
  }

  /** Clicks on the link and optionally waits for any after click conditions. */
  public void click() {
    getElement().click();
    for (final Condition condition : afterClickWaitFor) {
      p.waitFor(condition);
    }
  }

  public String getTitle() {
    return getElement().getAttribute("title");
  }

  public String getText() {
    return getElement().getText();
  }

  public String getHref() {
    return getElement().getAttribute("href");
  }

  public void assertText(final String expected) {
    assertThat(getText(), is(expected));
  }
}
